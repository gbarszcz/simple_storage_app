package com.example.storage.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.storage.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUIElements()
    }

    private fun initUIElements() {
        val showButton = findViewById<Button>(R.id.show_all_products)
        showButton.setOnClickListener {
            run {
                val intent = Intent(this, ProductsActivity::class.java)
                startActivity(intent)
            }
        }
        val addButton = findViewById<Button>(R.id.add_product)
        addButton.setOnClickListener {
            run {
                val intent = Intent(this, NewProductActivity::class.java)
                startActivity(intent)
            }
        }
        val calculateButton = findViewById<Button>(R.id.calculatePricesButton)
        calculateButton.setOnClickListener {
            run {
                val intent = Intent(this, CalculatePricesActivity::class.java)
                startActivity(intent)
            }
        }

    }

}