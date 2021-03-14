package ru.maxgrachev.vesere.ui.fragments.newrecord

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maxgrachev.vesere.data.local.dao.ParameterDao
import ru.maxgrachev.vesere.data.local.entity.Event
import ru.maxgrachev.vesere.data.local.entity.Parameter
import ru.maxgrachev.vesere.utils.MaintenanceTask
import ru.maxgrachev.vesere.utils.convertLongToDateString

class NewRecordViewModel(private val databaseParameterDao: ParameterDao, application: Application) :
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
                parameterList.add(
                    0,
                    Parameter(
                        name = "Maintenance task",
                        value = maintenanceTask,
                        categoryId = categoryId
                    )
                )
            }

            if (serviceLife.isNotEmpty()) {
                parameterList.add(
                    1,
                    Parameter(name = "Service Life", value = serviceLife, categoryId = categoryId)
                )
            }

            if (carMileage.isNotEmpty()) {
                parameterList.add(
                    2,
                    Parameter(name = "Car Mileage", value = carMileage, categoryId = categoryId)
                )
            }

            if (price.isNotEmpty()) {
                parameterList.add(
                    3,
                    Parameter(name = "Price", value = price, categoryId = categoryId)
                )
            }

            if (serviceName.isNotEmpty()) {
                parameterList.add(
                    4,
                    Parameter(
                        name = "Service Station Name",
                        value = serviceName,
                        categoryId = categoryId
                    )
                )
            }

            if (comment.isNotEmpty()) {
                parameterList.add(
                    5,
                    Parameter(name = "Comment", value = comment, categoryId = categoryId)
                )
            }

//            newRecord.serviceRating = rating
//            newRecord.dateMilli = date
            Log.v("parList", "name = ${parameterList[0].name}, value = ${parameterList[0].value.toString()}")
            Log.v("parList", "name = ${parameterList[1].name}, value = ${parameterList[1].value.toString()}")
            Log.v("parList", "name = ${parameterList[2].name}, value = ${parameterList[2].value.toString()}")
            Log.v("parList", "name = ${parameterList[3].name}, value = ${parameterList[3].value.toString()}")
            Log.v("parList", "name = ${parameterList[4].name}, value = ${parameterList[4].value.toString()}")
            Log.v("parList", "name = ${parameterList[5].name}, value = ${parameterList[5].value.toString()}")
//
                    databaseParameterDao.insertAll(parameterList)
        }
    }
}