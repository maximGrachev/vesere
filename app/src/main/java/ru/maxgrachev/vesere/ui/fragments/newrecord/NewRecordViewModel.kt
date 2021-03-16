package ru.maxgrachev.vesere.ui.fragments.newrecord

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maxgrachev.vesere.data.local.entity.Category
import ru.maxgrachev.vesere.data.local.entity.Parameter
import ru.maxgrachev.vesere.repository.CategoryRepository
import ru.maxgrachev.vesere.repository.ParameterRepository
import ru.maxgrachev.vesere.utils.convertLongToDateString

class NewRecordViewModel
    (
    private val parameterRepository: ParameterRepository,
    private val categoryRepository: CategoryRepository,
    application: Application
) :
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

        var category = Category(name = maintenanceTask)

        viewModelScope.launch {
            categoryRepository.insert(category)

            if (maintenanceTask.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Maintenance task",
                        value = maintenanceTask,
                        categoryId = category.id
                    )
                )
            }

            if (serviceLife.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Service Life",
                        value = serviceLife,
                        categoryId = category.id
                    )
                )
            }

            if (carMileage.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Car Mileage",
                        value = carMileage,
                        categoryId = category.id
                    )
                )
            }

            if (price.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Price",
                        value = price,
                        categoryId = category.id
                    )
                )
            }

            if (serviceName.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Service Station Name",
                        value = serviceName,
                        categoryId = category.id
                    )
                )
            }

            if (comment.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Comment",
                        value = comment,
                        categoryId = category.id
                    )
                )
            }


            parameterRepository.insert(
                Parameter(
                    name = "serviceRating",
                    value = rating.toString(),
                    categoryId = category.id
                )
            )

            parameterRepository.insert(
                Parameter(
                    name = "dateMilli",
                    value = date.toString(),
                    categoryId = category.id
                )
            )
        }
    }
}