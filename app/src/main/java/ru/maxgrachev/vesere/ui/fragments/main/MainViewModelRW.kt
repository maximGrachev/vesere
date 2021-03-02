package ru.maxgrachev.vesere.ui.fragments.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.maxgrachev.vesere.data.local.dao.CategoryDao

class MainViewModelRW(
    val database: CategoryDao,
    application: Application
) : AndroidViewModel(Application()) {
}