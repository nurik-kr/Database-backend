package kg.nurik.databasefirebase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kg.nurik.databasefirebase.R
import kg.nurik.databasefirebase.RvAdapter
import kg.nurik.databasefirebase.model.NewsItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val data = arrayListOf<NewsItem>()
    private val adapter = RvAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Recyclerview.adapter = adapter
        setupView()

    }
    private fun setupView() {
        val database = Firebase.database
        val ref = database.getReferenceFromUrl("https://database-firebase-da190.firebaseio.com/")

        ref.addValueEventListener(object : ValueEventListener {// своего рода Ретрофит,файрбейса
        override fun onDataChange(snapshot: DataSnapshot) {
            data.clear()
            for (postSnapshot in snapshot.children) {// каждого ребенка записываем в поинт
                val point: NewsItem? = postSnapshot.getValue(NewsItem::class.java)
                point?.let { data.add(it) }
            }
            adapter.update(data)
        }
            override fun onCancelled(error: DatabaseError) {
                Log.d("asdasd", "11")
            }
        })
    }
}

