package ru.maxgrachev.vesere.ui.fragments.allrecords

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.relations.CategoryWithParameters
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

    var recordsWithParameter: LiveData<List<CategoryWithParameters>>
    private var arg: String = arguments.eventTypeKeyWord


    private val _navigateToEventDetail = MutableLiveData<Int>()
    val navigateToEventDetail
        get() = _navigateToEventDetail

    private val _navigateToEditEvent = MutableLiveData<Int>()
    val navigateToEditEvent: LiveData<Int>
        get() = _navigateToEditEvent

    init {
        recordsWithParameter = takeRecordsWithParameterData()
    }

    val showingRecordsString = Transformations.map(recordsWithParameter) { recordsWithParameter ->
        formatEvents(recordsWithParameter, application.resources)
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

    private fun takeRecordsWithParameterData(): LiveData<List<CategoryWithParameters>>{
        return categoryRepository.getAllCategoryWithParameter()
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

    fun deleteEvent(category: CategoryWithParameters) {
        viewModelScope.launch {
            categoryRepository.delete(category)
        }
    }
}


