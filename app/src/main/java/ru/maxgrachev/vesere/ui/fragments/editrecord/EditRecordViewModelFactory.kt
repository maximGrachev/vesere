package ru.maxgrachev.vesere.ui.fragments.editrecord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maxgrachev.vesere.data.local.dao.CategoryDao
import ru.maxgrachev.vesere.data.local.dao.EventDatabaseDao

class EditRecordViewModelFactory(
    private val eventKey: Int,
    private val dataSource: CategoryDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditRecordViewModel::class.java)) {
            return EditRecordViewModel(eventKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}