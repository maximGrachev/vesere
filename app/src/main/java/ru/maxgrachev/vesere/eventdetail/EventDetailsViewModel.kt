package ru.maxgrachev.vesere.eventdetail;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.maxgrachev.vesere.database.allrecords.Event
import ru.maxgrachev.vesere.database.allrecords.EventDatabaseDao

class EventDetailsViewModel(
    private val eventKey: Long = 0L,
    dataSource: EventDatabaseDao
) : ViewModel() {

    val database = dataSource

    private val event = MediatorLiveData<Event>()
    fun getEvent() = event

    init {
        event.addSource(database.getEventWithId(eventKey), event::setValue)
    }

    private val _navigateToAllRecords = MutableLiveData<Boolean?>()
    val navigateToAllRecords: LiveData<Boolean?>
        get() = _navigateToAllRecords

    fun doneNavigating() {
        _navigateToAllRecords.value = null
    }

    fun onClose() {
        _navigateToAllRecords.value = true
    }

    fun giveEventType(): String? {
        return database.getEventWithId(eventKey).value?.eventName
    }

}
