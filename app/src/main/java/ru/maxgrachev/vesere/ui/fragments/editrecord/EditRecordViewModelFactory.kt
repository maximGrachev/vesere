package ru.maxgrachev.vesere.ui.fragments.editrecord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maxgrachev.vesere.repository.CategoryRepository
import ru.maxgrachev.vesere.repository.ParameterRepository

class EditRecordViewModelFactory(
    private val eventKey: Int,
    private val parameterRepository: ParameterRepository,
    private val categoryRepository: CategoryRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditRecordViewModel::class.java)) {
            return EditRecordViewModel(eventKey, parameterRepository, categoryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}