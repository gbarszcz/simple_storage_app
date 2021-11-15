package com.example.storage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
        @PrimaryKey(autoGenerate = true) val _id: Int = 0,
        @ColumnInfo(name = "name") var name: String?,
        @ColumnInfo(name = "quantity") var quantity: Double?,
        @ColumnInfo(name = "price") var price: Double?
)