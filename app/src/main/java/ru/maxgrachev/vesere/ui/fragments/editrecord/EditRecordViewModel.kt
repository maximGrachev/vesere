package ru.maxgrachev.vesere.ui.fragments.editrecord

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maxgrachev.vesere.data.local.dao.CategoryDao
import ru.maxgrachev.vesere.data.local.dao.EventDatabaseDao
import ru.maxgrachev.vesere.data.local.entity.Event

class EditRecordViewModel(
    eventKey: Int,
    dataSource: CategoryDao
) : ViewModel() {

    val database = dataSource

    private val event = MediatorLiveData<Event>()
    fun getEvent() = event

    init {
//        event.addSource(database.getEventWithId(eventKey), event::setValue)
    }

    fun updateRecord(
        eventKey: Long,
        maintenanceTask: String,
        date: Long,
        serviceLife: String,
        carMileage: String,
        price: String,
        serviceName: String,
        comment: String,
        rating: Boolean
    ) {
        viewModelScope.launch {
            val updatedRecord = Event()

            if (maintenanceTask.isNotEmpty()) {
                updatedRecord.eventName = maintenanceTask
            }

            if (serviceLife.isNotEmpty()) {
                updatedRecord.serviceLife = serviceLife.toInt()
            }

            if (carMileage.isNotEmpty()) {
                updatedRecord.carMileage = carMileage.toInt()
            }

            if (price.isNotEmpty()) {
                updatedRecord.price = price.toInt()
            }

            if (serviceName.isNotEmpty()) {
                updatedRecord.serviceStationName = serviceName
            }

            if (comment.isNotEmpty()) {
                updatedRecord.comment = comment
            }

            updatedRecord.serviceRating = rating
            updatedRecord.dateMilli = date
            updatedRecord.eventID = eventKey

            update(updatedRecord)
        }
    }

    private suspend fun update(record: Event) {
//        database.update(record)
    }
}

