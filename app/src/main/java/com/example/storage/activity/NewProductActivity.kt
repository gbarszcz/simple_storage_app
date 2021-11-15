package com.example.storage.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.storage.R
import com.example.storage.database.StorageApplication
import com.example.storage.database.entity.Product
import com.example.storage.database.repository.ProductRepository

class NewProductActivity : AppCompatActivity() {

    lateinit var productRepository: ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        productRepository = (application as StorageApplication).productRepository

        initUIElements()
    }

    private fun initUIElements() {
        val editProductName = findViewById<EditText>(R.id.editProductName)
        val editProductQuantity = findViewById<EditText>(R.id.editProductQuantity)
        val editProductPrice = findViewById<EditText>(R.id.editProductPrice)
        val saveDataButton = findViewById<Button>(R.id.saveData)
        saveDataButton.setOnClickListener {
            run {
                if (editProductName.text.toString().isBlank()
                        || editProductQuantity.toString().isBlank()
                        || editProductPrice.toString().isBlank()) {
                    Toast.makeText(this@NewProductActivity,
                            getString(R.string.error_adding_product),
                            Toast.LENGTH_SHORT)
                            .show()
                } else {
                    val product = Product(
                            name = editProductName.text.toString(),
                            quantity = editProductQuantity.text.toString().toDouble(),
                            price = editProductPrice.text.toString().toDouble())
                    productRepository.insert(product)

                    Toast.makeText(this@NewProductActivity,
                            getString(R.string.product_added, product.name, product.quantity, product.price),
                            Toast.LENGTH_SHORT)
                            .show()

                    val intent = Intent(this@NewProductActivity, ProductsActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}