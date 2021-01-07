package com.example.storage.database.repository

import android.database.Cursor
import com.example.storage.database.dao.ProductDao
import com.example.storage.database.entity.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {

    val allProducts: Flow<List<Product>> = productDao.getAll()

    fun insert(product: Product) {
        productDao.insert(product)
    }

    fun update(product: Product) {
        productDao.update(product)
    }
    
    fun delete(product: Product) {
        productDao.delete(product)
    }

    fun getAll(): Flow<List<Product>> {
        return productDao.getAll()
    }

    fun getCursorAll(): Cursor {
        return productDao.getCursorAll()
    }

    fun getById(id: Int): Product {
        return productDao.findById(id)
    }
}
