package ru.otus.otuskotlin.marketplace.cor

import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.marketplace.cor.handlers.CorChain
import ru.otus.otuskotlin.marketplace.cor.handlers.CorWorker
import ru.otus.otuskotlin.marketplace.cor.handlers.executeSequential
import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertEquals

class CorChainTest {
    @Test
    @JsName("chain_should_execute_workers")
    fun `chain should execute workers`() = runTest {
        val createWorker = { title: String ->
            CorWorker<TestContext>(
                title = title,
                blockOn = { status == TestContext.CorStatuses.NONE },
                blockHandle = { history += "$title; " }
            )
        }
        val chain = CorChain<TestContext>(
            execs = listOf(createWorker("w1"), createWorker("w2")),
            title = "chain",
            handler = ::executeSequential
        )
        val ctx = TestContext()
        chain.exec(ctx)
        assertEquals("w1; w2; ", ctx.history)
    }
}
