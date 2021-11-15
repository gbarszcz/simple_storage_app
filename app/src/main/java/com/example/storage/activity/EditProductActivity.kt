package com.example.storage.activity

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

class EditProductActivity : AppCompatActivity()  {

    lateinit var productRepository : ProductRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        productRepository = (application as StorageApplication).productRepository

        val id = intent.getIntExtra("productId", -1)
        val product = productRepository.getById(id)

        initUIElements(product)
    }

    private fun initUIElements(product: Product) {
        val editProductName = findViewById<EditText>(R.id.editProductName)
        val editProductQuantity = findViewById<EditText>(R.id.editProductQuantity)
        val editProductPrice = findViewById<EditText>(R.id.editProductPrice)
        val saveDataButton = findViewById<Button>(R.id.saveData)
        editProductName.setText(product.name)
        editProductQuantity.setText(product.quantity.toString())
        editProductPrice.setText(product.price.toString())

        saveDataButton.setOnClickListener {
            run {
                product.name = editProductName.text.toString()
                product.quantity = editProductQuantity.text.toString().toDoubleOrNull()
                product.price = editProductPrice.text.toString().toDoubleOrNull()
                productRepository.update(product)

                Toast.makeText(this@EditProductActivity, getString(R.string.product_updated), Toast.LENGTH_SHORT).show()

                val intent = Intent(this@EditProductActivity, ProductsActivity::class.java)
                startActivity(intent)
            }
        }

    }

}