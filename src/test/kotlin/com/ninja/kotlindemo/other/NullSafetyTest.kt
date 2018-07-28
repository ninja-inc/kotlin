package com.ninja.kotlindemo.other

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.ninja.kotlindemo.pojo.Item
import com.ninja.kotlindemo.pojo.Option
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

internal class NullSafetyTest {
    private val mapper = ObjectMapper()
            .registerModule(KotlinModule())

    @Test
    fun mapper_deserialize_success() {
        val item = mapper.readValue("""
            {
                "options": []
            }
        """.trimIndent(), Item::class.java)

        val expectedItem = Item(
                options = Collections.emptyList(),
                comment = null,
                specialPrice = null)

        // Compile error! we could not add an element to List
        //expectedItem.options.add(Option(comment = null))

        // Compile error! we could not remove an element from List
        //expectedItem.options.remove(Option(comment = null))

        // Compile error! we could not update any `val` fields
        //expectedItem.options = Collections.emptyList()

        assertThat(item)
                .isEqualTo(expectedItem)

        assertThat(item.specialPrice?.priceAmount)
                .isNull()

        assertThat(item.specialPrice?.priceAmount ?: BigDecimal.ZERO)
                .isEqualTo(BigDecimal.ZERO)
    }

    @Test
    fun mapper_deserialize_failure() {
        assertThatThrownBy{ mapper.readValue("""
            {
                "options": null
            }
        """.trimIndent(), Item::class.java) }
                .isInstanceOf(MissingKotlinParameterException::class.java)
    }
}