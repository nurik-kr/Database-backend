package kg.nurik.databasefirebase.data.model.repository

import androidx.lifecycle.MutableLiveData
import kg.nurik.databasefirebase.data.model.api.FirebaseApiImpl
import kg.nurik.databasefirebase.data.model.model.NewsItem

interface FirebaseRepository {
    fun loadData(): MutableLiveData<ArrayList<NewsItem>>
}

class FirebaseRepositoryImpl : FirebaseRepository { // репозиторий отвечает только за сохранение в бд

    val network = FirebaseApiImpl()

    override fun loadData(): MutableLiveData<ArrayList<NewsItem>> {
        val data = network.loadData()

        /*
        save in room, sharedPreference
        save in room, sharedPreference
        save in room, sharedPreference
        save in room, sharedPreference
        save in room, sharedPreference
       */

        return data
    }
}