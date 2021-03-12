//package ru.maxgrachev.vesere.data.local.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import ru.maxgrachev.vesere.data.local.dao.EventDatabaseDao
//import ru.maxgrachev.vesere.data.local.entity.Event
//
//@Database(entities = [Event::class], version = 1, exportSchema = false)
//abstract class EventDatabase : RoomDatabase() {
//
//    abstract val eventDatabaseDao: EventDatabaseDao
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: EventDatabase? = null
//
//        fun getInstance(context: Context): EventDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        EventDatabase::class.java,
//                        "events_history_database"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
//}