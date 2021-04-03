package ru.maxgrachev.vesere.ui.fragments.eventdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maxgrachev.vesere.repository.CategoryRepository

class EventDetailsViewModelFactory(
    private val eventKey: Int,
    private val dataSource: CategoryRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventDetailsViewModel::class.java)) {
            return EventDetailsViewModel(eventKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
