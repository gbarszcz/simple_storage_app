package com.example.storage.database.viewmodel

import androidx.lifecycle.*
import com.example.storage.database.entity.Product
import com.example.storage.database.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    // Using LiveData and caching what allProducts returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allProducts: LiveData<List<Product>> = repository.allProducts.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }

    fun delete(product: Product) = viewModelScope.launch {
        repository.delete(product)
    }
}

class ProductViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
