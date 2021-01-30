package ru.godsonpeya.vesereapi.model

import ru.godsonpeya.vesereapi.entity.Category

data class ParameterDto(
    var parameterId: Int = 0,
    var parameterCategoryId: Int? = null,
    var parameterName: String? = null,
    var parameterValue: String? = null,
    var parameterCategory: Category? = null
)
