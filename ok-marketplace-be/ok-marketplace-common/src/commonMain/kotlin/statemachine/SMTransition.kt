package ru.otus.otuskotlin.marketplace.common.statemachine

data class SMTransition(
    val state: SMAdStates,
    val description: String,
) {
    companion object {
        val ERROR = SMTransition(SMAdStates.ERROR, "Unprovided transition occurred")
    }
}
