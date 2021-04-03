package ru.maxgrachev.vesere.ui.fragments.eventdetail;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.maxgrachev.vesere.data.local.entity.relations.CategoryWithParameters
import ru.maxgrachev.vesere.repository.CategoryRepository

class EventDetailsViewModel(
    eventKey: Int,
    categoryRepository: CategoryRepository
) : ViewModel() {

    var event: LiveData<CategoryWithParameters> = categoryRepository.getCategoryWithParameterByID(eventKey)

    private val _navigateToAllRecords = MutableLiveData<Boolean?>()
    val navigateToAllRecords: LiveData<Boolean?>
        get() = _navigateToAllRecords

    fun doneNavigating() {
        _navigateToAllRecords.value = null
    }

    fun onClose() {
        _navigateToAllRecords.value = true
    }
}
