package ru.maxgrachev.vesere.eventdetail

import ru.maxgrachev.vesere.database.allrecords.EventDatabaseDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EventDetailsViewModelFactory(
        private val eventKey: Long,
        private val dataSource: EventDatabaseDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventDetailsViewModel::class.java)) {
            return EventDetailsViewModel(eventKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
