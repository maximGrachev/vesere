package ru.maxgrachev.vesere.ui.fragments.allrecords

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.repository.CategoryRepository
import ru.maxgrachev.vesere.utils.formatEvents

class AllRecordsViewModel(
    private val categoryRepository: CategoryRepository,
    application: Application,
    arguments: AllRecordsFragmentArgs
) :
    AndroidViewModel(
        Application()
    ) {

    lateinit var records: LiveData<List<Category>>
    private var arg: String = arguments.eventTypeKeyWord

    private val _navigateToEventDetail = MutableLiveData<Int>()
    val navigateToEventDetail
        get() = _navigateToEventDetail

    private val _navigateToEditEvent = MutableLiveData<Int>()
    val navigateToEditEvent: LiveData<Int>
        get() = _navigateToEditEvent

    init {
        records = takeEventData(arg)
    }

    val showingRecordsString = Transformations.map(records) { records ->
        formatEvents(records, application.resources)
    }

    private fun takeEventData(arg: String): LiveData<List<Category>> {
//        viewModelScope.launch{
//            records = categoryRepository.getCategoryToShow(arg)
//        }
        return when (arg) {
            "Oil change" -> categoryRepository.OilChangeCategory
            "Antifreeze change" -> categoryRepository.AntifreezeChangeCategory
            else -> categoryRepository.categoryList
//        }
        }
    }

    fun onEventClicked(id: Int) {
        _navigateToEventDetail.value = id
    }

    fun onEventDetailsNavigated() {
        _navigateToEventDetail.value = null
    }

    fun onEditEventClicked(id: Int) {
        _navigateToEditEvent.value = id
    }

    fun onEditEventNavigated() {
        _navigateToEditEvent.value = null
    }

    fun deleteEvent(event: Category) {
        viewModelScope.launch {
            categoryRepository.delete(event)
        }
    }
}


