package com.ninja.kotlindemo.other

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.ninja.kotlindemo.pojo.Item
import com.ninja.kotlindemo.pojo.ItemClass
import com.ninja.kotlindemo.pojo.Option
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*
import javax.validation.Validation

internal class NullSafetyTest {
    private val mapper = ObjectMapper()
            .registerModule(KotlinModule())

    @Test
    fun mapper_deserialize_dataclass_success() {
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
    fun mapper_deserialize_dataclass_success_with_validation() {
        val item = mapper.readValue("""
            {
                "options": [
                    {
                        "id": "1"
                    }
                ],
                "comment": "123456"
            }
        """.trimIndent(), Item::class.java)

        val expectedItem = Item(
                options = Arrays.asList(
                        Option(id = "1", comment = null)
                ),
                comment = "123456",
                specialPrice = null)

        assertThat(item)
                .isEqualTo(expectedItem)

        assertThat(item.findOptionBy(id = "1"))
                .isEqualTo(Option(id = "1", comment = null))

        val validator = Validation.buildDefaultValidatorFactory().validator
        assertThat(validator.validate(item))
                .hasSize(1)
    }

    @Test
    fun mapper_deserialize_dataclass_failure() {
        assertThatThrownBy{ mapper.readValue("""
            {
                "options": null
            }
        """.trimIndent(), Item::class.java) }
                .isInstanceOf(MissingKotlinParameterException::class.java)
    }



    //@Test
    fun mapper_deserialize_class_success() {
        val item = mapper.readValue("""
            {
                "options": []
            }
        """.trimIndent(), ItemClass::class.java)

        val expectedItem = ItemClass(
                options = Collections.emptyList(),
                comment = null,
                specialPrice = null)

        assertThat(item)
                .isEqualTo(expectedItem)

        assertThat(item.specialPrice?.priceAmount)
                .isNull()

        assertThat(item.specialPrice?.priceAmount ?: BigDecimal.ZERO)
                .isEqualTo(BigDecimal.ZERO)
    }
}