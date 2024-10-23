package com.example.ridesharing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.ridesharing.models.Driver
import com.example.ridesharing.models.Rider

class MainActivity : AppCompatActivity() {
    private val drivers = mutableListOf<Driver>()
    private val riders = mutableListOf<Rider>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val driverNameInput = findViewById<EditText>(R.id.driverNameInput)
        val driverLatInput = findViewById<EditText>(R.id.driverLatInput)
        val driverLonInput = findViewById<EditText>(R.id.driverLonInput)
        val addDriverButton = findViewById<Button>(R.id.addDriverButton)

        val riderNameInput = findViewById<EditText>(R.id.riderNameInput)
        val riderLatInput = findViewById<EditText>(R.id.riderLatInput)
        val riderLonInput = findViewById<EditText>(R.id.riderLonInput)
        val riderDestLatInput = findViewById<EditText>(R.id.riderDestLatInput)
        val riderDestLonInput = findViewById<EditText>(R.id.riderDestLonInput)
        val addRiderButton = findViewById<Button>(R.id.addRiderButton)

        val requestRideButton = findViewById<Button>(R.id.requestRideButton)
        val outputTextView = findViewById<TextView>(R.id.outputTextView)

        addDriverButton.setOnClickListener {
            val name = driverNameInput.text.toString()
            val lat = driverLatInput.text.toString().toDoubleOrNull()
            val lon = driverLonInput.text.toString().toDoubleOrNull()

            if (lat != null && lon != null) {
                drivers.add(Driver(name, Pair(lat, lon)))
                outputTextView.text = "Driver $name added successfully!"
            } else {
                outputTextView.text = "Invalid driver location input."
            }
        }

        addRiderButton.setOnClickListener {
            val name = riderNameInput.text.toString()
            val lat = riderLatInput.text.toString().toDoubleOrNull()
            val lon = riderLonInput.text.toString().toDoubleOrNull()
            val destLat = riderDestLatInput.text.toString().toDoubleOrNull()
            val destLon = riderDestLonInput.text.toString().toDoubleOrNull()

            if (lat != null && lon != null && destLat != null && destLon != null) {
                riders.add(Rider(name, Pair(lat, lon), Pair(destLat, destLon)))
                outputTextView.text = "Rider $name added successfully!"
            } else {
                outputTextView.text = "Invalid rider or destination location input."
            }
        }

        requestRideButton.setOnClickListener {
            if (riders.isEmpty()) {
                outputTextView.text = "No riders available to request a ride."
                return@setOnClickListener
            }

            val rider = riders.last()
            val ride = rider.requestRide(drivers)
            if (ride != null) {
                outputTextView.text = "Ride assigned to ${ride.assignedDriver.name}. Estimated time: ${ride.estimatedTime} minutes."
            } else {
                outputTextView.text = "No drivers available for the ride."
            }
        }
    }
}
