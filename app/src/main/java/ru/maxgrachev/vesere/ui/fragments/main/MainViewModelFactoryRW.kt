package ru.maxgrachev.vesere.ui.fragments.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maxgrachev.vesere.data.local.dao.CategoryDao

class MainViewModelFactoryRW(
    private val dataSource: CategoryDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModelRW::class.java)) {
            return MainViewModelRW(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
