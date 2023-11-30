package com.example.ebusinessapp.presentation.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.ebusinessapp.R
import com.example.ebusinessapp.databinding.FragmentDiscoverScreenBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DiscoverScreen : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentDiscoverScreenBinding
    private var mGoogleMap: GoogleMap? = null
    private val PERMISSION_REQUEST_LOCATION = 1001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscoverScreenBinding.inflate(inflater, container, false)

        setMap()
        checkLocationPermission()

        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        getCurrentLocation()
    }

/*
    override fun onResume() {
        super.onResume()
        getCurrentLocation()
    }
*/

    private fun setMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        } else {
            getCurrentLocation()
        }
    }

    private fun requestLocationPermission() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        requestPermissions(
            permissions,
            PERMISSION_REQUEST_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Location permission denied. You need to grant permission to show the location.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val latLng = LatLng(location.latitude, location.longitude)
                    mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    mGoogleMap?.addMarker(
                        MarkerOptions().position(latLng).title("Mevcut Konum")
                    )
                }
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}
