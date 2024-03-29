package ru.otus.otuskotlin.markeplace.app.spring.base

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.springframework.web.reactive.socket.WebSocketSession
import ru.otus.otuskotlin.marketplace.api.v2.models.IResponse
import ru.otus.otuskotlin.marketplace.common.ws.IMkplWsSession

data class SpringWsSessionV2(
    private val session: WebSocketSession,
) : IMkplWsSession, ISpringMkplWsSession<IResponse> {
    private val _output: MutableSharedFlow<IResponse> = MutableSharedFlow()
    override val output: SharedFlow<IResponse> = _output.asSharedFlow()

    override suspend fun <T> send(obj: T) {
        require(obj is IResponse)
        _output.emit(obj)
//        val message = apiV2ResponseSerialize(obj)
//        println("SENDING to WsV1: $message")
//        session.send(Mono.just(session.textMessage(message)))
    }
}
