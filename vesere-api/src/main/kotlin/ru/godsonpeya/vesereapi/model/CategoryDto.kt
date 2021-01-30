package ru.godsonpeya.vesereapi.model

import ru.godsonpeya.vesereapi.entity.Parameter

data class CategoryDto(
    var categoryId: Int = 0,
    var categoryName: String? = null,
    var categoryParameters: MutableList<Parameter?>? = null
)
