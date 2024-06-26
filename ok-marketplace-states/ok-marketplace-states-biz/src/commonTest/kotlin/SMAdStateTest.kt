package ru.otus.otuskotlin.marketplace.biz.statemachine

import ru.otus.otuskotlin.marketplace.biz.statemachine.resolver.SMAdStateResolverDefault
import ru.otus.otuskotlin.marketplace.states.common.statemachine.SMAdSignal
import ru.otus.otuskotlin.marketplace.states.common.statemachine.SMAdStates
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days

class SMAdStateTest {

    @Test
    fun new2actual() {
        val machine = SMAdStateResolverDefault()
        val signal = SMAdSignal(
            state = SMAdStates.NEW,
            duration = 4.days,
            views = 20,
        )
        val transition = machine.resolve(signal)
        assertEquals(SMAdStates.ACTUAL, transition.state)
        assertContains(transition.description, "актуальное", ignoreCase = true)
    }

    @Test
    fun new2hit() {
        val machine = SMAdStateResolverDefault()
        val signal = SMAdSignal(
            state = SMAdStates.NEW,
            duration = 2.days,
            views = 101,
        )
        val transition = machine.resolve(signal)
        assertEquals(SMAdStates.HIT, transition.state)
        assertContains(transition.description, "Очень", ignoreCase = true)
    }
}
