package ru.maxgrachev.vesere.setting

import android.app.AlertDialog
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.maxgrachev.vesere.MainActivity
import ru.maxgrachev.vesere.allrecords.AllRecordsFragmentArgs
import ru.maxgrachev.vesere.database.allrecords.EventDatabaseDao

class SettingViewModel(val database: EventDatabaseDao, application: Application) : AndroidViewModel(application) {

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
            database.clear()
        }
    }
}