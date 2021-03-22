package ru.maxgrachev.vesere.ui.fragments.newrecord

import android.app.Application
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

        viewModelScope.launch {
            categoryRepository.lastAddedCategoryID++
            val category = Category(id = categoryRepository.lastAddedCategoryID, name = maintenanceTask)
            categoryRepository.insert(category)
            val addedCategoryID = categoryRepository.lastAddedCategoryID //TODO how to get an id????

            if (serviceLife.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Service Life",
                        value = serviceLife,
                        categoryId = addedCategoryID
                    )
                )
            }

            if (carMileage.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Car Mileage",
                        value = carMileage,
                        categoryId = addedCategoryID
                    )
                )
            }

            if (price.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Price",
                        value = price,
                        categoryId = addedCategoryID
                    )
                )
            }

            if (serviceName.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Service Station Name",
                        value = serviceName,
                        categoryId = addedCategoryID
                    )
                )
            }

            if (comment.isNotEmpty()) {
                parameterRepository.insert(
                    Parameter(
                        name = "Comment",
                        value = comment,
                        categoryId = addedCategoryID
                    )
                )
            }

            parameterRepository.insert(
                Parameter(
                    name = "serviceRating",
                    value = rating.toString(),
                    categoryId = addedCategoryID
                )
            )

            parameterRepository.insert(
                Parameter(
                    name = "dateMilli",
                    value = date.toString(),
                    categoryId = addedCategoryID
                )
            )
        }
    }
}