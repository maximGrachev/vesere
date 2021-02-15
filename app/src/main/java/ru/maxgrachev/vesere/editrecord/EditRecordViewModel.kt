package ru.maxgrachev.vesere.editrecord

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import ru.maxgrachev.vesere.database.allrecords.Event
import ru.maxgrachev.vesere.database.allrecords.EventDatabaseDao

class EditRecordViewModel(
    eventKey: Long,
    dataSource: EventDatabaseDao):ViewModel() {

    val database = dataSource

    private val event = MediatorLiveData<Event>()
    fun getEvent() = event

    init {
        event.addSource(database.getEventWithId(eventKey), event::setValue)
    }
}

