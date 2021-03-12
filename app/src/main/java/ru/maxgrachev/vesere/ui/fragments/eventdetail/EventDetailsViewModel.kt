package ru.maxgrachev.vesere.ui.fragments.eventdetail;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.maxgrachev.vesere.data.local.dao.CategoryDao
import ru.maxgrachev.vesere.data.local.entity.Event

class EventDetailsViewModel(
    private val eventKey: Int = 1,
    dataSource: CategoryDao
) : ViewModel() {

    val database = dataSource

    private val event = MediatorLiveData<Event>()
    fun getEvent() = event

    init {
//        event.addSource(database.getEventWithId(eventKey), event::setValue)
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
//        return database.getEventWithId(eventKey).value?.eventName
        return null
    }

}
