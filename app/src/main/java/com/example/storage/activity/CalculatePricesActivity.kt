package com.example.storage.activity

import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.storage.R
import com.example.storage.database.StorageApplication
import com.example.storage.database.viewmodel.ProductViewModel
import com.example.storage.database.viewmodel.ProductViewModelFactory


class CalculatePricesActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)

        val productRepository = (application as StorageApplication).productRepository
        val spinner = findViewById<Spinner>(R.id.products_spinner)
        val adapter = SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                productRepository.getCursorAll(), arrayOf("name"), intArrayOf(android.R.id.text1), 0)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val itemAtPosition = parent?.getItemAtPosition(position) as Cursor
        val calculateResult = itemAtPosition.getDouble(2) * itemAtPosition.getDouble(3)
        val calculateTextView = findViewById<TextView>(R.id.calculationResultView)
        calculateTextView.text = calculateResult.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) { }

}