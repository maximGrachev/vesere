package ru.maxgrachev.vesere.ui.fragments.newrecord

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maxgrachev.vesere.data.local.entity.Parameter
import ru.maxgrachev.vesere.repository.ParameterRepository
import ru.maxgrachev.vesere.utils.convertLongToDateString

class NewRecordViewModel
    (private val parameterRepository: ParameterRepository,
     application: Application) :
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
        var categoryId: Int = 1 //TODO chose categoryId using maintenanceTask value
        var parameterList = mutableListOf<Parameter>()

        viewModelScope.launch {
            if (maintenanceTask.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Maintenance task",
                        value = maintenanceTask,
                        categoryId = categoryId
                    )
                )
            }

            if (serviceLife.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Service Life",
                        value = serviceLife,
                        categoryId = categoryId)
                )
            }

            if (carMileage.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Car Mileage",
                        value = carMileage,
                        categoryId = categoryId
                    )
                )
            }

            if (price.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Price",
                        value = price,
                        categoryId = categoryId)
                )
            }

            if (serviceName.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Service Station Name",
                        value = serviceName,
                        categoryId = categoryId
                    )
                )
            }

            if (comment.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Comment",
                        value = comment,
                        categoryId = categoryId
                    )
                )
            }

//            newRecord.serviceRating = rating
//            newRecord.dateMilli = date
        }
    }
}