package ru.maxgrachev.vesere.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.Parameter

@Dao
interface ParameterDao {
    @Insert
    suspend fun insert(parameter: Parameter)

    @Insert
    fun insertAll(vararg parameters: Parameter)

    @Query("DELETE FROM parameter")
    fun deleteAll()

    @Delete
    fun deleteParameter(parameter: Parameter)

    @get:Query("SELECT * from parameter ORDER BY name ASC")
    val getAllParameter: LiveData<List<Parameter>>

//    @Query("SELECT * from word_table ORDER BY word ASC")
//    fun getAllWords(): LiveData<List<Category?>?>?
}