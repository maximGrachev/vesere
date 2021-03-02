package ru.maxgrachev.vesere.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.maxgrachev.vesere.data.local.entity.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategory(category:List<Category>)

    @Query("SELECT * FROM category ORDER BY id DESC")
    fun getAllCategory(): LiveData<List<Category>>
}