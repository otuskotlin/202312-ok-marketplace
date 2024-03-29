package ru.otus.otuskotlin.marketplace.app.ktor.plugins

import io.ktor.server.application.*
import ru.otus.otuskotlin.marketplace.logging.common.MpLoggerProvider
import ru.otus.otuskotlin.marketplace.logging.jvm.mpLoggerLogback
import ru.otus.otuskotlin.marketplace.logging.kermit.mpLoggerKermit

actual fun Application.getLoggerProviderConf(): MpLoggerProvider =
    when (val mode = environment.config.propertyOrNull("ktor.logger")?.getString()) {
        "kmp" -> MpLoggerProvider { mpLoggerKermit(it) }
        "socket", "sock" -> getSocketLoggerProvider()
        "logback", null -> MpLoggerProvider { mpLoggerLogback(it) }
        else -> throw Exception("Logger $mode is not allowed. Additted values are kmp, socket and logback (default)")
}
