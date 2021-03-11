package ru.maxgrachev.vesere.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.relations.CategoryWithParameters

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Query("DELETE FROM category")
    fun deleteAll()

    @Delete
    fun deleteWord(category: Category)

    @get:Query("SELECT * from category ORDER BY name ASC")
    val allCategories: LiveData<List<Category>>

    @Transaction
    @Query("SELECT * from category ORDER BY name ASC")
    fun allCategoriesWithParamaters(): LiveData<List<CategoryWithParameters>>

//    @Query("SELECT * from word_table ORDER BY word ASC")
//    fun getAllWords(): LiveData<List<Category?>?>?
}