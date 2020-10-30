package ru.maxgrachev.vesere.allrecords

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maxgrachev.vesere.database.allrecords.EventDatabaseDao

class AllRecordsViewModelFactory(
    private val dataSource: EventDatabaseDao,
    private val application: Application,
    private val arguments: AllRecordsFragmentArgs
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllRecordsViewModel::class.java)) {
            return AllRecordsViewModel(dataSource, application, arguments) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}