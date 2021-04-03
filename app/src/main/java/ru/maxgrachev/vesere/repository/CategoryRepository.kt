package ru.maxgrachev.vesere.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.maxgrachev.vesere.data.local.dao.CategoryDao
import ru.maxgrachev.vesere.data.local.database.AppRoomDatabase
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.relations.CategoryWithParameters
import ru.maxgrachev.vesere.network.VesereApi.retrofitService

class CategoryRepository(private val categoryDao: CategoryDao) {
//get data from service
//    suspend fun refreshCategory(){
//        withContext(Dispatchers.IO){
//            val categoryList = retrofitService.getAllCategories()
//            database.categoryDao.insertAllCategory(categoryList)
//        }
//    }

    var categoryList: LiveData<List<Category>> = categoryDao.AllCategories()
    lateinit var categoryWithParametersList: LiveData<CategoryWithParameters>
    var OilChangeCategory: LiveData<List<Category>> = categoryDao.getAllOilChange()
    var AntifreezeChangeCategory: LiveData<List<Category>> = categoryDao.getAllAntifreezeChange()
    var lastAddedCategoryID: Int = 0;

    suspend fun insert(category: Category) {
        withContext(Dispatchers.IO) {
           categoryDao.insert(category)
        }
    }

    suspend fun delete(categoryWithParameters: CategoryWithParameters) {
        withContext(Dispatchers.IO) {
            categoryDao.delete(categoryWithParameters.category)
        }
    }

    fun getAllCategoryWithParameter(): LiveData<List<CategoryWithParameters>>{
        return categoryDao.getAllCategoryWithParamater()
    }

    fun getCategoryWithParameterByID(categoryID: Int): LiveData<CategoryWithParameters>{
        return categoryDao.getCategoryWithParamater(categoryID)
    }

    suspend fun updateCategory(category: Category) {
        withContext(Dispatchers.IO) {
            categoryDao.updateCategory(category)
        }
    }


//    suspend fun getCategoryToShow(categoryName: String): LiveData<List<Category>>{
//        val categoryToShow: LiveData<List<Category>>
//        withContext(Dispatchers.IO){
//            categoryToShow = categoryDao.getAllCategoryWithName(categoryName)
//        }
//        return categoryToShow
//    }
}