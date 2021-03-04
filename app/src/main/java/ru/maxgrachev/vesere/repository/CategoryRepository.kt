package ru.maxgrachev.vesere.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.maxgrachev.vesere.data.local.database.AppRoomDatabase
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.network.VesereApi.retrofitService

//class CategoryRepository(private val database: AppRoomDatabase) {
//    suspend fun refreshCategory(){
//        withContext(Dispatchers.IO){
//            val categoryList = retrofitService.getAllCategories()
//            database.categoryDao.insertAllCategory(categoryList)
//        }
//    }
//
//    val category: LiveData<List<Category>> = database.categoryDao.getAllCategory()
//}