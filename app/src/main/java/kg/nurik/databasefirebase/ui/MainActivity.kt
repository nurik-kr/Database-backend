package kg.nurik.databasefirebase.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import kg.nurik.databasefirebase.R
import kg.nurik.databasefirebase.RvAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val adapter = RvAdapter()

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
}

