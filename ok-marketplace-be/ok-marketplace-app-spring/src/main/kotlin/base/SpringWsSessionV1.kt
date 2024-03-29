package ru.otus.otuskotlin.markeplace.app.spring.base

import kotlinx.coroutines.flow.*
import org.springframework.web.reactive.socket.WebSocketSession
import ru.otus.otuskotlin.marketplace.api.v1.models.IResponse
import ru.otus.otuskotlin.marketplace.common.ws.IMkplWsSession

data class SpringWsSessionV1(
    private val session: WebSocketSession,
) : IMkplWsSession, ISpringMkplWsSession<IResponse> {
    private val _output: MutableSharedFlow<IResponse> = MutableSharedFlow()
    override val output: SharedFlow<IResponse> = _output.asSharedFlow()

    override suspend fun <T> send(obj: T) {
        /*
        * Здесь реактивная логика. В обычном случае
        * */
        require(obj is IResponse)
        _output.emit(obj)
    }
}
