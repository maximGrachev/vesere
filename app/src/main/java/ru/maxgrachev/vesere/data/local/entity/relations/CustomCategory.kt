package ru.maxgrachev.vesere.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.Parameter


class CustomCategory {
    @Embedded
    var category: Category = Category()

    @Relation(parentColumn = "id", entityColumn = "parentId")
    var childreen: List<Category> = mutableListOf()

    @Relation(parentColumn = "id", entityColumn = "categoryId")
    var parameters: List<Parameter> = mutableListOf()
}