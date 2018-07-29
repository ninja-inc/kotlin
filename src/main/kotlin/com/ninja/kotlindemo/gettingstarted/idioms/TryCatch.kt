package com.ninja.kotlindemo.gettingstarted.idioms

class TryCatch {
    fun tryCatch(any: Any): Any {
        val hoge = try {
            throwExceptionIfString(any)
        } catch (e: RuntimeException) {
            "ERROR"
        }

        return hoge;
    }

    private fun throwExceptionIfString(any: Any): Any = if (any is String) any else throw RuntimeException("$any is not String")
}