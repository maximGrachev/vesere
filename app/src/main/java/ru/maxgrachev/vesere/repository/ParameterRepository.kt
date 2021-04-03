package ru.maxgrachev.vesere.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.maxgrachev.vesere.data.local.dao.ParameterDao
import ru.maxgrachev.vesere.data.local.entity.Parameter

class ParameterRepository(private val parameterDao: ParameterDao) {

    val parameterList: LiveData<List<Parameter>> = parameterDao.getAllParameter

    suspend fun insert(parameter: Parameter) {
        withContext(Dispatchers.IO) {
            parameterDao.insert(parameter)
        }
    }

    suspend fun updateParameter(parameter: Parameter) {
        withContext(Dispatchers.IO) {
            parameterDao.updateParameter(parameter)
        }
    }

    suspend fun delete(parameter: Parameter) {
        withContext(Dispatchers.IO) {
            parameterDao.delete(parameter)
        }
    }
}
