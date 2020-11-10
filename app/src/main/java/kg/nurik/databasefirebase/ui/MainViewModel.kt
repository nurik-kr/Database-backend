package kg.nurik.databasefirebase.ui

import androidx.lifecycle.ViewModel
import kg.nurik.databasefirebase.data.model.repository.FirebaseRepositoryImpl

class MainViewModel : ViewModel() { // отвечает только за логику

    private val repository = FirebaseRepositoryImpl()

    fun showItem() = repository.loadData()

}