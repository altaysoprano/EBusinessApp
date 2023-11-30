package com.example.ebusinessapp.presentation.ui

import android.app.ActionBar
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.ebusinessapp.databinding.FragmentDiscoverScreenBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DiscoverScreen : Fragment() {

    private lateinit var binding: FragmentDiscoverScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscoverScreenBinding.inflate(inflater, container, false)

        return binding.root
    }
}