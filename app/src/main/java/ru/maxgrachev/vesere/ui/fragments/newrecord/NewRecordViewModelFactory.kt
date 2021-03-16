package ru.maxgrachev.vesere.ui.fragments.newrecord

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maxgrachev.vesere.repository.CategoryRepository
import ru.maxgrachev.vesere.repository.ParameterRepository

class NewRecordViewModelFactory(
    private val parameterRepository: ParameterRepository,
    private val categoryRepository: CategoryRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewRecordViewModel::class.java)) {
            return NewRecordViewModel(parameterRepository, categoryRepository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}