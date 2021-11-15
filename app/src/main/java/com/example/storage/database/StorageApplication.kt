package com.example.storage.database

import android.app.Application
import com.example.storage.database.repository.ProductRepository

class StorageApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { StorageDatabase.getDatabase(this) }
    val productRepository by lazy { ProductRepository(database.productDao()) }
}
