package ru.maxgrachev.vesere

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.maxgrachev.vesere.data.local.database.AppRoomDatabase
import ru.maxgrachev.vesere.repository.CategoryRepository
import ru.maxgrachev.vesere.repository.ParameterRepository

class VesereApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy{ AppRoomDatabase.getInstance(this, applicationScope)}
    val categoryRepository by lazy{CategoryRepository(database.categoryDao)}
    val parameterRepository by lazy{ParameterRepository(database.parameterDao)}
}