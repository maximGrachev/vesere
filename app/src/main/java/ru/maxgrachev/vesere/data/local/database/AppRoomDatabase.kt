package ru.maxgrachev.vesere.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.maxgrachev.vesere.data.local.dao.CategoryDao
import ru.maxgrachev.vesere.data.local.dao.ParameterDao
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.Parameter


@Database(entities = [Category::class, Parameter::class], version = 2, exportSchema = false)

abstract class AppRoomDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val parameterDao: ParameterDao

//    private class WordDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    val categoryDao = database.categoryDao
//                    // Delete all content here.
//                    categoryDao.deleteAll()
//                    // Add sample categories.
//                    var category = Category(id = 1, name = "All")
//                    categoryDao.insert(category)
//                    category = Category(id = 2, parentId = 2, name = "Oil change")
//                    categoryDao.insert(category)
//                    category = Category(id = 3, parentId = 2, name = "Transmission")
//                    categoryDao.insert(category)
//                    category = Category(id = 4, name = "Antifreeze change")
//                    categoryDao.insert(category)
//                    category = Category(id = 5, parentId = 4, name = "Maintenance")
//                    categoryDao.insert(category)
//                    category = Category(id = 6, parentId = 4, name = "Computer diagnostics")
//                    categoryDao.insert(category)
//                    category = Category(id = 7, name = "Brake repair")
//                    categoryDao.insert(category)
//                    category = Category(id = 8, name = "Engine work")
//                    categoryDao.insert(category)
//                    category = Category(id = 9, name = "Electrical systems")
//                    categoryDao.insert(category)
//                    category = Category(id = 10, name = "Other")
//                    categoryDao.insert(category)
//                }
//            }
//        }
//    }

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): AppRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java,
                        "events_history_database"
                    )
                        .fallbackToDestructiveMigration()
//                        .addCallback(WordDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}