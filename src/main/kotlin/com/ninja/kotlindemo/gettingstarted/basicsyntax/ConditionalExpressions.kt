package com.ninja.kotlindemo.gettingstarted.basicsyntax

import org.slf4j.LoggerFactory

class ConditionalExpressions {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun basicIfElse(x: Int, y: Int): Int {
        return if (x > y) x else y
    }

    fun simpleBasicIfElse(x: Int, y: Int): Int = if (x > y) x else y

    fun lastValue(x: Int, y: Int): Int = if (x > y) {
        log.info("x is chosen")
        x // means `return x`
    } else {
        log.info("y is chosen")
        y
    }

    fun whenBehave(x: Any): WhenBehave = when (x) {
        is String -> WhenBehave.STRING
        in 1..10 -> WhenBehave.INT_ONE_TO_TEN
        else -> WhenBehave.ELSE

    }

    enum class WhenBehave {
        STRING, INT_ONE_TO_TEN, ELSE
    }
}
