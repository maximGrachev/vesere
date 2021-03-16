package ru.maxgrachev.vesere.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.relations.CategoryWithParameters

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Insert
    suspend fun insertAll(categoryList: List<Category>)

    @Delete
    suspend fun delete(category: Category)

    @Query("DELETE FROM category")
    suspend fun deleteAll()

    @get:Query("SELECT * from category ORDER BY id DESC")
    val AllCategories: LiveData<List<Category>>

    @Transaction
    @Query("SELECT * from category ORDER BY name ASC")
    fun allCategoriesWithParamaters(): LiveData<List<CategoryWithParameters>>

//    @Query("SELECT * from word_table ORDER BY word ASC")
//    fun getAllWords(): LiveData<List<Category?>?>?
}