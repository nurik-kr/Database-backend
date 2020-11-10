package kg.nurik.databasefirebase.data.model.api

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kg.nurik.databasefirebase.data.model.model.NewsItem

interface FirebaseApi {
    fun loadData(): MutableLiveData<ArrayList<NewsItem>>
}

class FirebaseApiImpl : FirebaseApi { // отвечает толлько за запросы в интернет

    override fun loadData(): MutableLiveData<ArrayList<NewsItem>> {
        val data = MutableLiveData<ArrayList<NewsItem>>()// cлушатель
        val database = Firebase.database
        val ref = database.getReferenceFromUrl("https://database-firebase-da190.firebaseio.com/")
        val list: ArrayList<NewsItem>? = arrayListOf() // пустой список

        ref.addValueEventListener(object : ValueEventListener { // своего рода Ретрофит,файрбейса
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {// каждого ребенка записываем в поинт
                    val point: NewsItem? = postSnapshot.getValue(NewsItem::class.java)
                    point?.let { list?.add(it) }// список заполняем
                }
                data.postValue(list) // передаем список
//            adapter.update(data)
            }

            override fun onCancelled(error: DatabaseError) {
                data.postValue(null)
            }
        })
        return data
    }
}