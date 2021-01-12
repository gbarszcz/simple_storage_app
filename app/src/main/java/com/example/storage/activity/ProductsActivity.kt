package com.example.storage.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.R
import com.example.storage.adapter.ProductListAdapter
import com.example.storage.database.StorageApplication
import com.example.storage.database.viewmodel.ProductViewModel
import com.example.storage.database.viewmodel.ProductViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductsActivity : AppCompatActivity() {
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory((application as StorageApplication).productRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        initUIElements()
    }

    private fun initUIElements() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ProductListAdapter(productViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        productViewModel.allProducts.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }

        val addNewProductButton = findViewById<FloatingActionButton>(R.id.addNewProductButton)
        addNewProductButton.setOnClickListener {
            val intent = Intent(this@ProductsActivity, NewProductActivity::class.java)
            startActivity(intent)
        }

        val backHomeButton = findViewById<FloatingActionButton>(R.id.backHomeButton)
        backHomeButton.setOnClickListener {
            val intent = Intent(this@ProductsActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}