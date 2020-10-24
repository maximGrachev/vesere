package ru.maxgrachev.vesere.database.allrecords

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EventDatabaseDao {

    @Insert
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Query("SELECT * FROM events_table ORDER BY eventID DESC")
    fun getAllEvents(): LiveData<List<Event>>

    @Query("SELECT * FROM events_table WHERE event_name=\"Oil change\" ORDER BY eventID DESC")
    fun getAllOilChange(): LiveData<List<Event>>

    @Delete
    suspend fun delete(event: Event)

    @Query("SELECT * FROM events_table ORDER BY eventID DESC LIMIT 1")
    suspend fun getLastEvent(): Event?

//    @Query("SELECT * FROM EVENTS_TABLE WHERE event_name="Other" ")
//    suspend fun getAllOilChangeEvents():LiveData<List<Event>>

}