package com.example.ridesharing.models

import com.example.ridesharing.utils.haversine
import kotlin.math.*

class Ride(
    val rider: Rider,
    val assignedDriver: Driver
) {
    var distanceToDestination: Double = calculateDistance()
    var estimatedTime: Double = calculateEstimatedTime()

    fun calculateDistance(): Double {
        return haversine(rider.currentLocation.first, rider.currentLocation.second, rider.destinationLocation.first, rider.destinationLocation.second)
    }

    fun calculateEstimatedTime(): Double {
        val trafficFactor = (1..2).random().toDouble()
        val speed = 60.0
        return (distanceToDestination / 1000) / speed * 60 * trafficFactor
    }
}
