package com.example.tpn5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CitiesActivity : AppCompatActivity() {
    private lateinit var selectedCityTextView: TextView
    private lateinit var citiesListView: ListView
    private lateinit var btnGoToGrid: Button
    private lateinit var btnGoToWorld: Button
    
    // List of cities
    private val cities = listOf("Sousse", "Monastir", "Sfax", "Bizerte", "Nabeul", "Tozeur", "Djerba")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
        
        // Initialize views
        selectedCityTextView = findViewById(R.id.selectedCityTextView)
        citiesListView = findViewById(R.id.citiesListView)
        btnGoToGrid = findViewById(R.id.btnGoToGrid)
        btnGoToWorld = findViewById(R.id.btnGoToWorld)
        
        // Set default selected city
        var selectedCity = "Sousse"
        selectedCityTextView.text = selectedCity
        
        // Create adapter for the ListView using Android's built-in layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cities)
        citiesListView.adapter = adapter
        
        // Set item click listener
        citiesListView.setOnItemClickListener { _, _, position, _ ->
            selectedCity = cities[position]
            selectedCityTextView.text = selectedCity
            
            // Launch Google search for the selected city
            val searchUrl = "http://www.google.fr/search?q=$selectedCity"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl))
            startActivity(intent)
        }
        
        // Set button click listener to navigate to GridActivity
        btnGoToGrid.setOnClickListener {
            val intent = Intent(this, GridActivity::class.java)
            startActivity(intent)
        }
        
        // Set button click listener to navigate to WorldActivity
        btnGoToWorld.setOnClickListener {
            val intent = Intent(this, WorldActivity::class.java)
            startActivity(intent)
        }
    }
}