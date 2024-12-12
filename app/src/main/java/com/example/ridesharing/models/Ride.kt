package com.example.ridesharing.models

import com.example.ridesharing.utils.haversine

class Ride(
    val rider: Rider, // Rider requesting the ride
    val assignedDriver: Driver // Driver assigned to the ride
) {
    // Distance between rider's current location and destination
    var distanceToDestination: Double = calculateDistance()
    // Estimated time to destination
    var estimatedTime: Double = calculateEstimatedTime()

    // Calculate the distance to the destination using the haversine formula
    fun calculateDistance(): Double {
        return haversine(
            rider.currentLocation.first, rider.currentLocation.second,
            rider.destinationLocation.first, rider.destinationLocation.second
        )
    }

    // Estimate the time to destination, factoring in traffic
    fun calculateEstimatedTime(): Double {
        val trafficFactor = (1..2).random().toDouble() // Random traffic factor (1 to 2)
        val speed = 60.0 // Average speed in km/h
        return (distanceToDestination / 1000) / speed * 60 * trafficFactor // Time in minutes
    }
}
