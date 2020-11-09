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

    @Query("SELECT * FROM events_table WHERE event_name=\"Antifreeze change\" ORDER BY eventID DESC")
    fun getAllAntifreezeChange(): LiveData<List<Event>>

    @Query("SELECT * FROM events_table WHERE event_name=\"Maintenance\" ORDER BY eventID DESC")
    fun getAllMaintenance(): LiveData<List<Event>>

    @Query("SELECT * FROM events_table WHERE event_name=\"Computer diagnostics\" ORDER BY eventID DESC")
    fun getAllComputerDiagnostics(): LiveData<List<Event>>

    @Query("SELECT * FROM events_table WHERE event_name=\"Brake repair\" ORDER BY eventID DESC")
    fun getAllBreakRepair(): LiveData<List<Event>>

    @Query("SELECT * FROM events_table WHERE event_name=\"Engine work\" ORDER BY eventID DESC")
    fun getAllEngineWork(): LiveData<List<Event>>

    @Query("SELECT * FROM events_table WHERE event_name=\"Electrical Systems\" ORDER BY eventID DESC")
    fun getAllElectricalSystems(): LiveData<List<Event>>

    @Query("SELECT * FROM events_table WHERE event_name = \"Other\" OR (event_name<>\"Oil change\" AND event_name<>\"Antifreeze change\" AND event_name<>\"Maintenance\" AND event_name<>\"Computer diagnostics\" AND event_name<>\"Brake repair\" AND event_name<>\"Engine work\" AND event_name<>\"Electrical Systems\" AND event_name<>\"Transmission\") ORDER BY eventID DESC")
    fun getAllOther(): LiveData<List<Event>>

    @Query("SELECT * FROM events_table WHERE event_name=\"Transmission\" ORDER BY eventID DESC")
    fun getAllTransmission(): LiveData<List<Event>>

    @Delete
    suspend fun delete(event: Event)

    @Query("SELECT * FROM events_table ORDER BY eventID DESC LIMIT 1")
    suspend fun getLastEvent(): Event?

    @Query("DELETE FROM events_table")
    suspend fun clear()
}