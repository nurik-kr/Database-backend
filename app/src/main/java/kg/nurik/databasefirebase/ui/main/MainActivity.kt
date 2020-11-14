package kg.nurik.databasefirebase.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import kg.nurik.databasefirebase.R
import kg.nurik.databasefirebase.data.model.model.NewsItem
import kg.nurik.databasefirebase.ui.auth.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), itemClick {

    private val viewModel by viewModel<MainViewModel>()
    private val adapter = RvAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Recyclerview.adapter = adapter
        setupView()
    }

    private fun setupView() {
        viewModel.showItem().observe(this) {
            adapter.update(it)
        }
    }

    override fun clickListeners(point: NewsItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("data", point)
        startActivity(intent)
    }
}

