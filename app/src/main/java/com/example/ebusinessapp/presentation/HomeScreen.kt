package com.example.ebusinessapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.ebusinessapp.R
import com.example.ebusinessapp.databinding.FragmentHomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity?)!!.title = "Home"
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.homeToolbar)

        return binding.root
    }
}