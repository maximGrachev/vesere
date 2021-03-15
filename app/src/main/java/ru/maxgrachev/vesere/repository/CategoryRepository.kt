package ru.maxgrachev.vesere.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.maxgrachev.vesere.data.local.dao.CategoryDao
import ru.maxgrachev.vesere.data.local.database.AppRoomDatabase
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.network.VesereApi.retrofitService

class CategoryRepository(private val categoryDao: CategoryDao) {
//get data from service
//    suspend fun refreshCategory(){
//        withContext(Dispatchers.IO){
//            val categoryList = retrofitService.getAllCategories()
//            database.categoryDao.insertAllCategory(categoryList)
//        }
//    }

    val categoryList: LiveData<List<Category>> = categoryDao.AllCategories

    suspend fun insert(category: Category) {
        withContext(Dispatchers.IO) {
           categoryDao.insert(category)
        }
    }

    suspend fun delete(category: Category) {
        withContext(Dispatchers.IO) {
            categoryDao.delete(category)
        }
    }
}