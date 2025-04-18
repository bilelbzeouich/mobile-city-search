package com.example.tpn5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GridActivity : AppCompatActivity() {
    private lateinit var selectedCityTextView: TextView
    private lateinit var citiesGridView: GridView
    
    // List of cities
    private val cities = listOf("Sousse", "Monastir", "Sfax", "Bizerte", "Nabeul", "Tozeur", "Djerba")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        
        // Initialize views
        selectedCityTextView = findViewById(R.id.selectedCityTextView)
        citiesGridView = findViewById(R.id.citiesGridView)
        
        // Set default selected city
        var selectedCity = "Sousse"
        selectedCityTextView.text = selectedCity
        
        // Create adapter for the GridView using Android's built-in layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cities)
        citiesGridView.adapter = adapter
        
        // Configure GridView to have 3 columns
        citiesGridView.numColumns = 3
        
        // Set item click listener
        citiesGridView.setOnItemClickListener { _, _, position, _ ->
            selectedCity = cities[position]
            selectedCityTextView.text = selectedCity
            
            // Launch Google search for the selected city
            val searchUrl = "http://www.google.fr/search?q=$selectedCity"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl))
            startActivity(intent)
        }
    }
} 