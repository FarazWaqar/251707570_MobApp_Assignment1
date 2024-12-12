package com.example.ridesharing.models

class Driver(
    val name: String, // Driver's name
    var currentLocation: Pair<Double, Double>, // Driver's current location
    var isAvailable: Boolean = true, // Driver's availability status
    var rating: Double = 5.0 // Driver's initial rating
) {
    // Update driver's availability
    fun setAvailability(available: Boolean) {
        isAvailable = available
    }

    // Update the driver's rating by averaging the old and new ratings
    fun updateRating(newRating: Double) {
        rating = (rating + newRating) / 2
    }
}
