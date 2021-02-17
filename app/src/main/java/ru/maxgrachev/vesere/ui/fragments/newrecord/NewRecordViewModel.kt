package ru.maxgrachev.vesere.ui.fragments.newrecord

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maxgrachev.vesere.data.local.dao.EventDatabaseDao
import ru.maxgrachev.vesere.data.local.entity.Event
import ru.maxgrachev.vesere.utils.convertLongToDateString

class NewRecordViewModel(val database: EventDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    val dateNow = convertLongToDateString(System.currentTimeMillis())

    fun createNewRecord(
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
            val newRecord = Event()

            if (maintenanceTask.isNotEmpty()) {
                newRecord.eventName = maintenanceTask
            }

            if (serviceLife.isNotEmpty()) {
                newRecord.serviceLife = serviceLife.toInt()
            }

            if (carMileage.isNotEmpty()) {
                newRecord.carMileage = carMileage.toInt()
            }

            if (price.isNotEmpty()) {
                newRecord.price = price.toInt()
            }

            if (serviceName.isNotEmpty()) {
                newRecord.serviceStationName = serviceName
            }

            if (comment.isNotEmpty()) {
                newRecord.comment = comment
            }

            newRecord.serviceRating = rating

            newRecord.dateMilli = date

            insert(newRecord)
        }
    }

    private suspend fun insert(record: Event) {
        database.insert(record)
    }


}