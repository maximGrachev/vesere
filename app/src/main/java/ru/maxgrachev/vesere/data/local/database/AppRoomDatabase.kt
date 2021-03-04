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
import ru.maxgrachev.vesere.data.local.entity.relations.CategoryWithChildren
import ru.maxgrachev.vesere.data.local.entity.relations.CategoryWithParameters
import ru.maxgrachev.vesere.data.local.entity.relations.CustomCategory

@Database(
    entities = [Category::class,
        Parameter::class], version = 1, exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun parameterDao(): ParameterDao


    private class CategoryDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val categoryDao = database.categoryDao()
                    // Delete all content here.
//                    categoryDao.deleteAll()
                    // Add sample words.
                    var category = Category(id = 1, name = "Блок управления ABS")
                    categoryDao.InsertCategory(category)

                    val parameterDao = database.parameterDao()

                    parameterDao.insertAllParameter(
                        listOf(
                        Parameter(1,"price","1500",1),
                        Parameter(2,"data","20.03.2021",1),
                        Parameter(3,"price","1500",1))
                    )
                }
            }
        }
    }






    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java,
                        "app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}