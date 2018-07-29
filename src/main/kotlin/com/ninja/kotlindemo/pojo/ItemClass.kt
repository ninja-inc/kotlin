package com.ninja.kotlindemo.pojo

import javax.validation.constraints.Size

class ItemClass (
        val options: List<Option>,
        val specialPrice: SpecialPrice?,
        val comment: String?
)
