package kg.nurik.databasefirebase.ui.main

import androidx.lifecycle.ViewModel
import kg.nurik.databasefirebase.data.model.repository.FirebaseRepository
import kg.nurik.databasefirebase.data.model.repository.FirebaseRepositoryImpl

class MainViewModel( private val repository: FirebaseRepository): ViewModel() { // отвечает только за логику

    fun showItem() = repository.loadData()
}