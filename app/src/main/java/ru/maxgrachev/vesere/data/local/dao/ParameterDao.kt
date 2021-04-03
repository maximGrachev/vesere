package ru.maxgrachev.vesere.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.maxgrachev.vesere.data.local.entity.Parameter

@Dao
interface ParameterDao {
    @Insert
    suspend fun insert(parameter: Parameter)

    @Update
    suspend fun updateParameter(parameter: Parameter)

    @Insert
    suspend fun insertAll(parameterList: List<Parameter>)

    @Delete
    suspend fun delete(parameter: Parameter)

    @Query("DELETE FROM parameter")
    suspend fun deleteAll()

    @get:Query("SELECT * from parameter ORDER BY name ASC")
    val getAllParameter: LiveData<List<Parameter>>
}