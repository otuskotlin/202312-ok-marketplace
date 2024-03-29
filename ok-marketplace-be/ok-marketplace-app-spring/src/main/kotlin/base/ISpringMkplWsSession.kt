package ru.otus.otuskotlin.markeplace.app.spring.base

import kotlinx.coroutines.flow.SharedFlow
import ru.otus.otuskotlin.marketplace.common.ws.IMkplWsSession

interface ISpringMkplWsSession<T>: IMkplWsSession {
    val output: SharedFlow<T>
}
