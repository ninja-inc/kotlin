package com.ninja.kotlindemo.gettingstarted.basicsyntax

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ConditionalExpressionsTest {
    private val test = ConditionalExpressions()

    @Test
    fun test01() {
        assertThat(test.basicIfElse(1, 2))
                .isEqualTo(2)
    }

    @Test
    fun test02() {
        assertThat(test.simpleBasicIfElse(3, 4))
                .isEqualTo(4)
    }

    @Test
    fun test03() {
        assertThat(test.lastValue(2, 5))
                .isEqualTo(5)
    }

    @Test
    fun test04() {
        assertThat(test.whenBehave("hoge"))
                .isEqualTo(ConditionalExpressions.WhenBehave.STRING)
    }

    @Test
    fun test05() {
        assertThat(test.whenBehave(3))
                .isEqualTo(ConditionalExpressions.WhenBehave.INT_ONE_TO_TEN)
    }

    @Test
    fun test06() {
        assertThat(test.whenBehave(99))
                .isEqualTo(ConditionalExpressions.WhenBehave.ELSE)
    }
}