package com.example.ebusinessapp.presentation.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.nfc.NfcAdapter
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ebusinessapp.R
import com.example.ebusinessapp.databinding.FragmentHomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var navRegister: FragmentNavigation
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        navRegister = activity as FragmentNavigation
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.homeToolbar)
        (activity as AppCompatActivity?)!!.setTitle("Home")

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_nfc -> {
                openNFCSettings()
            }

            R.id.action_navigation -> {
                navigateToDiscoverScreen()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openNFCSettings() {
        val nfcAdapter: NfcAdapter? = NfcAdapter.getDefaultAdapter(context)

        if (nfcAdapter != null) {
            val intent = Intent(Settings.ACTION_NFC_SETTINGS)
            startActivity(intent)
        } else {
            Toast.makeText(context, "Your device does not have NFC capability.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun navigateToDiscoverScreen() {
        val discoverFragment = DiscoverScreen()
        navRegister.navigateFrag(discoverFragment, true)
    }
}