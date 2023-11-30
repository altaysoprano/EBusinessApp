package com.example.ebusinessapp.util

import com.google.android.gms.maps.model.LatLng
import kotlin.random.Random

object Constants {
    fun getHeatmapData(): ArrayList<LatLng> {
        val data = ArrayList<LatLng>()

        for(i in 1..200) {
            val latitude = Random.nextDouble(50.0, 75.0)
            val longitude = Random.nextDouble(50.0, 75.0)
            data.add(LatLng(latitude, longitude))
        }

        return data
    }
}