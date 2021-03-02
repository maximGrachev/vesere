package ru.maxgrachev.vesere.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.maxgrachev.vesere.data.local.dao.CategoryDao
import ru.maxgrachev.vesere.data.local.dao.EventDatabaseDao
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.Event

@Database(entities = [Category::class], version = 1, exportSchema = false)
abstract class CategoryDatabase : RoomDatabase() {

    abstract val categoryDao: CategoryDao

    companion object {

        @Volatile
        private var INSTANCE: CategoryDatabase? = null

        fun getInstance(context: Context): CategoryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CategoryDatabase::class.java,
                        "category_database"
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