package com.example.storage.database.dao

import android.database.Cursor
import androidx.room.*
import com.example.storage.database.entity.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<Product>>

    @Query("SELECT * FROM products WHERE _id IN (:productIds)")
    fun loadAllByIds(productIds: IntArray): List<Product>

    @Query("SELECT * FROM products")
    fun getCursorAll(): Cursor

    @Query("SELECT * FROM products WHERE _id LIKE :id LIMIT 1")
    fun findById(id: Int): Product

    @Insert
    fun insertAll(vararg products: Product)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)
}
