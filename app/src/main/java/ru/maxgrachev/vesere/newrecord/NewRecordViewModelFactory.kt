package ru.maxgrachev.vesere.newrecord

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maxgrachev.vesere.database.allrecords.EventDatabaseDao

class NewRecordViewModelFactory(
    private val dataSource: EventDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewRecordViewModel::class.java)) {
            return NewRecordViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}