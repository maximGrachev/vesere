package ru.maxgrachev.vesere.ui.fragments.editrecord

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.maxgrachev.vesere.data.local.entity.Parameter
import ru.maxgrachev.vesere.data.local.entity.relations.CategoryWithParameters
import ru.maxgrachev.vesere.repository.CategoryRepository
import ru.maxgrachev.vesere.repository.ParameterRepository

class EditRecordViewModel(
    eventKey: Int,
    private val parameterRepository: ParameterRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {


    private val event = MediatorLiveData<CategoryWithParameters>()
    fun getEvent() = event

    init {
        event.addSource(categoryRepository.getCategoryWithParameterByID(eventKey), event::setValue)
    }

    fun updateRecord(
        eventKey: Int?,
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
            categoryRepository.updateCategory(event.value!!.category)
            val addedCategoryID = event.value!!.category.id

            //TODO parameter can't be added
            if (serviceLife.isNotEmpty()) {
                parameterRepository.updateParameter(
                    Parameter(
                        name = "Service Life",
                        value = serviceLife,
                        categoryId = addedCategoryID
                    )
                )
            }

            if (carMileage.isNotEmpty()) {
                parameterRepository.updateParameter(
                    Parameter(
                        name = "Car Mileage",
                        value = carMileage,
                        categoryId = addedCategoryID
                    )
                )
            }

            if (price.isNotEmpty()) {
                parameterRepository.updateParameter(
                    Parameter(
                        name = "Price",
                        value = price,
                        categoryId = addedCategoryID
                    )
                )
            }

            if (serviceName.isNotEmpty()) {
                parameterRepository.updateParameter(
                    Parameter(
                        name = "Service Station Name",
                        value = serviceName,
                        categoryId = addedCategoryID
                    )
                )
            }

            if (comment.isNotEmpty()) {
                parameterRepository.updateParameter(
                    Parameter(
                        name = "Comment",
                        value = comment,
                        categoryId = addedCategoryID
                    )
                )
            }

            parameterRepository.updateParameter(
                Parameter(
                    name = "Service Rating",
                    value = rating.toString(),
                    categoryId = addedCategoryID
                )
            )

            parameterRepository.updateParameter(
                Parameter(
                    name = "Date Milli",
                    value = date.toString(),
                    categoryId = addedCategoryID
                )
            )
        }
    }


//    private suspend fun update(record: Event) {
////        database.update(record)
//    }
}