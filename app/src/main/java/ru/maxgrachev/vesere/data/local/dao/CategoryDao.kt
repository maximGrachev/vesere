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

    @Update
    suspend fun updateCategory(category: Category)

//    @Transaction
//    @Delete
//    fun delete(categoryWithParameters: CategoryWithParameters)

    @Query("DELETE FROM category")
    suspend fun deleteAll()

    @Query("SELECT * from category ORDER BY id DESC")
    fun AllCategories(): LiveData<List<Category>>

    @Query("SELECT * from category WHERE name = \"Oil change\" ORDER BY id DESC")
    fun getAllOilChange(): LiveData<List<Category>>

    @Query("SELECT * from category WHERE name = \"Antifreeze change\" ORDER BY id DESC")
    fun getAllAntifreezeChange(): LiveData<List<Category>>

    @get:Query("SELECT * FROM category WHERE id=(SELECT max(id) FROM category)")
    val LastAddedCategory: LiveData<Category>

    @Query("SELECT * from category WHERE name = :categoryName") //TODO getAllCategoryWithName with categoryName
    fun getAllCategoryWithName(categoryName: String?): LiveData<List<Category>>

    @Transaction
    @Query("SELECT * FROM category WHERE id=:categoryID")
    fun getCategoryWithParamater(categoryID: Int): LiveData<CategoryWithParameters>

    @Transaction
    @Query("SELECT * FROM category ORDER BY id DESC")
    fun getAllCategoryWithParamater(): LiveData<List<CategoryWithParameters>>
}