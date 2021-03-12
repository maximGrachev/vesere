package ru.maxgrachev.vesere.ui.fragments.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.maxgrachev.vesere.data.local.dao.CategoryDao
import ru.maxgrachev.vesere.data.local.dao.EventDatabaseDao

class SettingViewModel(val database: CategoryDao, application: Application) :
    AndroidViewModel(application) {

    private val _allRecordsDeleted = MutableLiveData<Boolean>()
    val allRecordsDeleted: LiveData<Boolean>
        get() = _allRecordsDeleted

    init {
        _allRecordsDeleted.value = false
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
        _allRecordsDeleted.value = true
    }

    fun allRecordsDeletedToFalse() {
        _allRecordsDeleted.value = false
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
//            database.clear()
        }
    }
}