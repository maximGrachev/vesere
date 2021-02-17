package ru.maxgrachev.vesere.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.maxgrachev.vesere.data.local.entity.Category

class CategoryWithChildren {
    @Embedded
    var category: Category = Category()

    @Relation(parentColumn = "id", entityColumn = "parentId")
    var childreen: List<Category> = mutableListOf()
}