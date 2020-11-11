package kg.nurik.databasefirebase.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import kg.nurik.databasefirebase.R
import kg.nurik.databasefirebase.RvAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
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

