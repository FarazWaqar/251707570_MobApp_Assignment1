package com.example.ridesharing.models

import com.example.ridesharing.utils.haversine

class Rider(
    val name: String, // Rider's name
    var currentLocation: Pair<Double, Double>, // Rider's current location
    var destinationLocation: Pair<Double, Double> // Rider's destination
) {
    // Request a ride by finding the closest available driver
    fun requestRide(availableDrivers: List<Driver>): Ride? {
        val closestDriver = availableDrivers
            .filter { it.isAvailable } // Only consider available drivers
            .minByOrNull {
                haversine(
                    currentLocation.first, currentLocation.second,
                    it.currentLocation.first, it.currentLocation.second
                )
            }

        // Assign the closest driver to the rider, or return null if no driver is found
        return closestDriver?.let {
            Ride(this, it).apply {
                closestDriver.setAvailability(false) // Mark the driver as unavailable
            }
        }
    }
}
