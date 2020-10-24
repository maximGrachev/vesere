package ru.maxgrachev.vesere.allrecords

import android.app.Application
import androidx.lifecycle.*
import ru.maxgrachev.vesere.database.allrecords.EventDatabaseDao
import ru.maxgrachev.vesere.formatEvents


class AllRecordsViewModel(val database: EventDatabaseDao, application: Application) :
    AndroidViewModel(
        Application()
    ) {

    private val allRecords = database.getAllEvents()
    private val allOilChangeRecords = database.getAllOilChange()

    val showingRecordsString = Transformations.map(allRecords, { allRecords ->
        formatEvents(allRecords, application.resources)
    })



}