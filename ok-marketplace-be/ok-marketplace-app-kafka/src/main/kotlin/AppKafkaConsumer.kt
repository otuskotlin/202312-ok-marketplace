package ru.otus.otuskotlin.marketplace.app.kafka

import kotlinx.atomicfu.atomic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.errors.WakeupException
import ru.otus.otuskotlin.marketplace.app.common.controllerHelper
import ru.otus.otuskotlin.marketplace.common.MkplContext
import java.time.Duration
import java.util.*

data class InputOutputTopics(val input: String, val output: String)

interface ConsumerStrategy {
    fun topics(config: AppKafkaConfig): InputOutputTopics
    fun serialize(source: MkplContext): String
    fun deserialize(value: String, target: MkplContext)
}

class AppKafkaConsumer(
    private val config: AppKafkaConfig,
    consumerStrategies: List<ConsumerStrategy>,
    private val consumer: Consumer<String, String> = config.createKafkaConsumer(),
    private val producer: Producer<String, String> = config.createKafkaProducer()
) {
    private val log = config.corSettings.loggerProvider.logger(this::class)
    private val process = atomic(true) // пояснить
    private val topicsAndStrategyByInputTopic: Map<String, TopicsAndStrategy> = consumerStrategies.associate {
        val topics = it.topics(config)
        topics.input to TopicsAndStrategy(topics.input, topics.output, it)
    }

    fun start() = runBlocking { startSusp() }

    suspend fun startSusp() {
        process.value = true
        try {
            consumer.subscribe(topicsAndStrategyByInputTopic.keys)
            while (process.value) {
                val records: ConsumerRecords<String, String> = withContext(Dispatchers.IO) {
                    consumer.poll(Duration.ofSeconds(1))
                }
                if (!records.isEmpty)
                    log.info("Receive ${records.count()} messages")

                records.forEach { record: ConsumerRecord<String, String> ->
                    try {
                        val (_, outputTopic, strategy) = topicsAndStrategyByInputTopic[record.topic()]
                            ?: throw RuntimeException("Receive message from unknown topic ${record.topic()}")

                        val resp = config.controllerHelper(
                            { strategy.deserialize(record.value(), this) },
                            { strategy.serialize(this) },
                            KafkaConsumer::class,
                            "kafka-consumer"
                        )
                        sendResponse(resp, outputTopic)
                    } catch (ex: Exception) {
                        log.error("error", e = ex)
                    }
                }
            }
        } catch (ex: WakeupException) {
            // ignore for shutdown
        } catch (ex: RuntimeException) {
            // exception handling
            withContext(NonCancellable) {
                throw ex
            }
        } finally {
            withContext(NonCancellable) {
                consumer.close()
            }
        }
    }

    private suspend fun sendResponse(json: String, outputTopic: String) {
        val resRecord = ProducerRecord(
            outputTopic,
            UUID.randomUUID().toString(),
            json
        )
        log.info("sending ${resRecord.key()} to $outputTopic:\n$json")
        withContext(Dispatchers.IO) {
            producer.send(resRecord)
        }
    }

    fun stop() {
        process.value = false
    }

    private data class TopicsAndStrategy(
        val inputTopic: String,
        val outputTopic: String,
        val strategy: ConsumerStrategy
    )
}
