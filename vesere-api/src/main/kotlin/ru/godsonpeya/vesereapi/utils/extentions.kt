package ru.godsonpeya.vesereapi.utils

import ru.godsonpeya.vesereapi.entity.Category
import ru.godsonpeya.vesereapi.entity.Parameter
import ru.godsonpeya.vesereapi.model.CategoryDto
import ru.godsonpeya.vesereapi.model.ParameterDto

fun Category.fromEntityToDto(): CategoryDto {
    return CategoryDto(
        categoryId = this.id,
        categoryName = this.name,
        categoryParameters = this.parameters
    )
}

fun CategoryDto.fromDtoToEntity(): Category {
    return Category(
        id = this.categoryId,
        name = this.categoryName,
        parameters = this.categoryParameters
    )
}


fun Parameter.fromEntityToDto(): ParameterDto {
    return ParameterDto(
        parameterId = this.id,
        parameterName = this.name,
        parameterCategoryId = this.categoryId,
        parameterValue = this.value
    )
}

fun ParameterDto.fromDtoToEntity(): Parameter {
    return Parameter(
        id = this.parameterId,
        categoryId = this.parameterCategoryId,
        name = this.parameterName,
        value = this.parameterValue
    )
}
