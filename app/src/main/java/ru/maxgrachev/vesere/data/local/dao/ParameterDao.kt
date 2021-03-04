package ru.maxgrachev.vesere.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.Parameter

@Dao
interface ParameterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllParameter(parameterList :List<Parameter>)

    @Insert
    fun insertParameter(parameter: Parameter)

    @Query("SELECT * FROM parameter ORDER BY id DESC")
    fun getAllParameter(): LiveData<List<Parameter>>
}