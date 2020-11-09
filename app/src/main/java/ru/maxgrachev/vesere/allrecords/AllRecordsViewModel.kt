package ru.maxgrachev.vesere.allrecords

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
}

//    private fun initializeRecords(arg: String) {
//        viewModelScope.launch {
//            records.value = takeEventData(arg)
//        }
//    }
//
//    private suspend fun takeEventData(arg: String): List<Event> {
//        var records: List<Event>
//        withContext(Dispatchers.IO) {
//            records = when (arg) {
//                "Oil change" -> database.getAllOilChange()
//                "Antifreeze change" -> database.getAllAntifreezeChange()
//                "Maintenance" -> database.getAllMaintenance()
//                "Computer diagnostics" -> database.getAllComputerDiagnostics()
//                "Brake repair" -> database.getAllBreakRepair()
//                "Engine work" -> database.getAllEngineWork()
//                "Electrical Systems" -> database.getAllElectricalSystems()
//                "Other" -> database.getAllOther()
//                "All" -> database.getAllEvents()
//                "Transmission" -> database.getAllTransmission()
//                else -> database.getAllEvents()
//            }
//        }
//        return records
//    }
//}


