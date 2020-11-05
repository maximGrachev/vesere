package ru.maxgrachev.vesere.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maxgrachev.vesere.database.allrecords.EventDatabaseDao

class SettingViewModelFactory (private val dataSource: EventDatabaseDao) : ViewModelProvider.Factory{
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
                return SettingViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }