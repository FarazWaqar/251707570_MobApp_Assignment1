package com.example.ridesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.ridesharing.models.Driver
import com.example.ridesharing.models.Rider

class MainActivity : AppCompatActivity() {
    // Lists to store drivers and riders
    private val drivers = mutableListOf<Driver>()
    private val riders = mutableListOf<Rider>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI components for driver input
        val driverNameInput = findViewById<EditText>(R.id.driverNameInput)
        val driverLatInput = findViewById<EditText>(R.id.driverLatInput)
        val driverLonInput = findViewById<EditText>(R.id.driverLonInput)
        val addDriverButton = findViewById<Button>(R.id.addDriverButton)

        // UI components for rider input
        val riderNameInput = findViewById<EditText>(R.id.riderNameInput)
        val riderLatInput = findViewById<EditText>(R.id.riderLatInput)
        val riderLonInput = findViewById<EditText>(R.id.riderLonInput)
        val riderDestLatInput = findViewById<EditText>(R.id.riderDestLatInput)
        val riderDestLonInput = findViewById<EditText>(R.id.riderDestLonInput)
        val addRiderButton = findViewById<Button>(R.id.addRiderButton)

        // UI components for requesting rides
        val requestRideButton = findViewById<Button>(R.id.requestRideButton)
        val outputTextView = findViewById<TextView>(R.id.outputTextView)

        // Add a driver to the list
        addDriverButton.setOnClickListener {
            val name = driverNameInput.text.toString()
            val lat = driverLatInput.text.toString().toDoubleOrNull()
            val lon = driverLonInput.text.toString().toDoubleOrNull()

            // Check for valid inputs
            if (lat != null && lon != null) {
                drivers.add(Driver(name, Pair(lat, lon))) // Add driver to the list
                outputTextView.text = "Driver $name added successfully!"
            } else {
                outputTextView.text = "Invalid driver location input."
            }
        }

        // Add a rider to the list
        addRiderButton.setOnClickListener {
            val name = riderNameInput.text.toString()
            val lat = riderLatInput.text.toString().toDoubleOrNull()
            val lon = riderLonInput.text.toString().toDoubleOrNull()
            val destLat = riderDestLatInput.text.toString().toDoubleOrNull()
            val destLon = riderDestLonInput.text.toString().toDoubleOrNull()

            // Check for valid inputs
            if (lat != null && lon != null && destLat != null && destLon != null) {
                riders.add(Rider(name, Pair(lat, lon), Pair(destLat, destLon))) // Add rider to the list
                outputTextView.text = "Rider $name added successfully!"
            } else {
                outputTextView.text = "Invalid rider or destination location input."
            }
        }

        // Request a ride for the last added rider
        requestRideButton.setOnClickListener {
            if (riders.isEmpty()) {
                outputTextView.text = "No riders available to request a ride."
                return@setOnClickListener
            }

            val rider = riders.last() // Get the last-added rider
            val ride = rider.requestRide(drivers) // Attempt to assign a driver
            if (ride != null) {
                outputTextView.text = "Ride assigned to ${ride.assignedDriver.name}. Estimated time: ${ride.estimatedTime} minutes."
            } else {
                outputTextView.text = "No drivers available for the ride."
            }
        }
    }
}
