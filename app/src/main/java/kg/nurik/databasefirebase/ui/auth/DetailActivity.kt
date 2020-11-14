package kg.nurik.databasefirebase.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kg.nurik.databasefirebase.R
import kg.nurik.databasefirebase.data.model.model.NewsItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val item = intent.getParcelableExtra<NewsItem>("data")
        tvTitleDet.text = item?.title.toString()
        tvDescDet.text = item?.desc.toString()
    }
}