package com.example.ridesharing.models
import com.example.ridesharing.utils.haversine

class Rider(
    val name: String,
    var currentLocation: Pair<Double, Double>,
    var destinationLocation: Pair<Double, Double>
) {
    fun requestRide(availableDrivers: List<Driver>): Ride? {
        val closestDriver = availableDrivers
            .filter { it.isAvailable }
            .minByOrNull { haversine(currentLocation.first, currentLocation.second, it.currentLocation.first, it.currentLocation.second) }

        return closestDriver?.let {
            Ride(this, it).apply {
                closestDriver.setAvailability(false)
            }
        } ?: run {
            println("No drivers available.")
            null
        }
    }
}
