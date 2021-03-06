package ru.maxgrachev.vesere.allrecords

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.maxgrachev.vesere.database.allrecords.Event
import ru.maxgrachev.vesere.database.allrecords.EventDatabaseDao
import ru.maxgrachev.vesere.formatEvents

class AllRecordsViewModel(
    val database: EventDatabaseDao,
    application: Application,
    arguments: AllRecordsFragmentArgs
) :
    AndroidViewModel(
        Application()
    ) {

    var records: LiveData<List<Event>>
    private var arg: String = arguments.eventTypeKeyWord

    private val _navigateToEventDetail = MutableLiveData<Long>()
    val navigateToEventDetail
        get() = _navigateToEventDetail

    private val _navigateToEditEvent = MutableLiveData<Long>()
    val navigateToEditEvent: LiveData<Long>
        get() = _navigateToEditEvent

    init {
        records = takeEventData(arg)
    }

    val showingRecordsString = Transformations.map(records) { records ->
        formatEvents(records, application.resources)
    }

    private fun takeEventData(arg: String): LiveData<List<Event>> {
        return when (arg) {
            "Oil change" -> database.getAllOilChange()
            "Antifreeze change" -> database.getAllAntifreezeChange()
            "Maintenance" -> database.getAllMaintenance()
            "Computer diagnostics" -> database.getAllComputerDiagnostics()
            "Brake repair" -> database.getAllBreakRepair()
            "Engine work" -> database.getAllEngineWork()
            "Electrical Systems" -> database.getAllElectricalSystems()
            "All" -> database.getAllEvents()
            "Transmission" -> database.getAllTransmission()
            else -> database.getAllOther()
        }
    }

    fun onEventClicked(id: Long) {
        _navigateToEventDetail.value = id
    }

    fun onEventDetailsNavigated() {
        _navigateToEventDetail.value = null
    }

    fun onEditEventClicked(id: Long) {
        _navigateToEditEvent.value = id
    }

    fun onEditEventNavigated() {
        _navigateToEditEvent.value = null
    }

    fun deleteEvent(event:Event){
        viewModelScope.launch {
            database.delete(event)
        }
    }
}


