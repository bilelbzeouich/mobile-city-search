package com.example.tpn5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WorldActivity : AppCompatActivity() {
    
    private lateinit var spinnerContinents: Spinner
    private lateinit var spinnerCountries: Spinner
    private lateinit var textViewSelection: TextView
    private lateinit var buttonWikipedia: Button
    
    private var selectedContinent: String = ""
    private var selectedCountry: String = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_world)
        
        // Initialize views
        spinnerContinents = findViewById(R.id.spinnerContinents)
        spinnerCountries = findViewById(R.id.spinnerCountries)
        textViewSelection = findViewById(R.id.textViewSelection)
        buttonWikipedia = findViewById(R.id.buttonWikipedia)
        
        // Set up continents spinner
        val continentsAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.continents,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        
        spinnerContinents.adapter = continentsAdapter
        
        // Set up continents spinner listener
        spinnerContinents.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedContinent = parent?.getItemAtPosition(position).toString()
                updateCountriesSpinner(selectedContinent)
                
                // Update the class variable
                this@WorldActivity.selectedContinent = selectedContinent
                
                // Update the display if a country is already selected
                if (selectedCountry.isNotEmpty()) {
                    updateSelectionDisplay()
                }
            }
            
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        
        // Set up countries spinner listener
        spinnerCountries.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCountry = parent?.getItemAtPosition(position).toString()
                updateSelectionDisplay()
                
                // Enable Wikipedia button when a country is selected
                buttonWikipedia.isEnabled = selectedCountry.isNotEmpty()
            }
            
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        
        // Set up Wikipedia button listener
        buttonWikipedia.setOnClickListener {
            openWikipedia(selectedCountry)
        }
        
        // Button is initially disabled until a country is selected
        buttonWikipedia.isEnabled = false
    }
    
    private fun updateCountriesSpinner(continent: String) {
        val countriesArrayId = when (continent) {
            "Afrique" -> R.array.pays_afr
            "Europe" -> R.array.pays_eur
            "Asie" -> R.array.pays_asie
            "Océanie" -> R.array.pays_oc
            "Amérique" -> R.array.pays_am
            else -> R.array.pays_afr // Default to Africa as fallback
        }
        
        val countriesAdapter = ArrayAdapter.createFromResource(
            this,
            countriesArrayId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        
        spinnerCountries.adapter = countriesAdapter
    }
    
    private fun updateSelectionDisplay() {
        textViewSelection.text = "Continent: $selectedContinent; Pays: $selectedCountry"
    }
    
    private fun openWikipedia(country: String) {
        // Convert the country name to lowercase for the Wikipedia URL
        val countryForUrl = country.lowercase()
        val url = "https://fr.wikipedia.org/wiki/$countryForUrl"
        
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
} 