package com.example.ridesharing.utils

import kotlin.math.*

fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val R = 6371e3
    val v1 = Math.toRadians(lat1)
    val v2 = Math.toRadians(lat2)
    val v3 = Math.toRadians(lat2 - lat1)
    val v4 = Math.toRadians(lon2 - lon1)
    val a = sin(v3/2)*sin(v3/2)*cos(v1)*cos(v2)*sin(v4/2)*sin(v4/2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return R * c
}
