package com.example.ebusinessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ebusinessapp.databinding.ActivityMainBinding
import com.example.ebusinessapp.presentation.ui.FragmentNavigation
import com.example.ebusinessapp.presentation.ui.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentNavigation {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.coordinator, HomeScreen())
                .commit()
        }

    }

    override fun navigateFrag(fragment: Fragment, addToStack: Boolean) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        transaction.setCustomAnimations(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out
        )

        transaction.replace(R.id.coordinator, fragment)

        when (fragment) {
            is HomeScreen -> {
                if (!addToStack) {
                    fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
            }
        }

        if (addToStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }
}