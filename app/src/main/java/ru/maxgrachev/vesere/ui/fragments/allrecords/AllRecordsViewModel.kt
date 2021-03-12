package ru.maxgrachev.vesere.ui.fragments.allrecords

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.maxgrachev.vesere.data.local.dao.CategoryDao
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.Event
import ru.maxgrachev.vesere.utils.formatEvents

class AllRecordsViewModel(
    val database: CategoryDao,
    application: Application,
    arguments: AllRecordsFragmentArgs
) :
    AndroidViewModel(
        Application()
    ) {

    var records: LiveData<List<Category>>
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
//        return when (arg) {
//            "Oil change" -> database.getAllOilChange()
//            "Antifreeze change" -> database.getAllAntifreezeChange()
//            "Maintenance" -> database.getAllMaintenance()
//            "Computer diagnostics" -> database.getAllComputerDiagnostics()
//            "Brake repair" -> database.getAllBreakRepair()
//            "Engine work" -> database.getAllEngineWork()
//            "Electrical Systems" -> database.getAllElectricalSystems()
//            "All" -> database.getAllEvents()
//            "Transmission" -> database.getAllTransmission()
//            else -> database.getAllOther()
//        }
        return  database.allCategories
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
//            database.delete(event)
        }
    }
}


