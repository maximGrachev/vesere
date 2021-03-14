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

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val categoryDao = database.categoryDao
                    // Delete all content here.
                    categoryDao.deleteAll()
                    // Add sample words.
                    var category = Category(id = 1, name = "Блок управления ABS")
                    categoryDao.insert(category)

                    val parameterDao = database.parameterDao

                    parameterDao.insertAll(
                        listOf(
                            Parameter(name = "price", value = "1500", categoryId = 1),
                            Parameter(name = "data", value = "20.03.2021", categoryId = 1),
                            Parameter(name = "price", value = "1500", categoryId = 1)
                        )
                    )
                    category = Category(id = 2, parentId = 2, name = "Тормозной суппорт")
                    categoryDao.insert(category)

                    parameterDao.insertAll(
                        listOf(
                            Parameter(name = "price", value = "1500", categoryId = 2),
                            Parameter(name = "data", value = "20.03.2021", categoryId = 2)
                        )
                    )

                    category = Category(id = 3, parentId = 2, name = "Генератор")
                    categoryDao.insert(category)
                    category = Category(id = 4, name = "Шаровая")
                    categoryDao.insert(category)
                    category = Category(id = 5, parentId = 4, name = "Тормозные диски")
                    categoryDao.insert(category)
                    category = Category(id = 6, parentId = 4, name = "Датчик распредвала")
                    categoryDao.insert(category)
                    category = Category(id = 7, name = "Поперечный рычаг")
                    categoryDao.insert(category)
                    category = Category(id = 8, name = "Сальники коленвала")
                    categoryDao.insert(category)
                    category = Category(id = 9, name = "Глушитель")
                    categoryDao.insert(category)
                }
            }
        }
    }

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
                        .addCallback(WordDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}