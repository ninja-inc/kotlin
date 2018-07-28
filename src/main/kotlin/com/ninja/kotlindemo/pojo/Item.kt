package com.ninja.kotlindemo.pojo

data class Item (
        val options: List<Option>,
        val specialPrice: SpecialPrice?,
        val comment: String?
)
