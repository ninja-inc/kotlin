package com.ninja.kotlindemo.pojo

import javax.validation.constraints.Size

data class Item (
        val options: List<Option>,

        val specialPrice: SpecialPrice?,

        @get:Size(min = 0, max = 5)
        val comment: String?)
{

    fun findOptionBy(id: String): Option {
        return options.stream()
                .filter { option -> option.id.equals(id) }
                .findFirst()
                .orElseThrow { RuntimeException("option is not found, id: $id") }
    }
}