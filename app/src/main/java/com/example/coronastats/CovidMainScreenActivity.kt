package com.example.coronastats

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.coronastats.databinding.ActivityMainBinding


class CovidMainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setUpBottomNavigation(navController)

    }

    private fun setUpBottomNavigation(navController: NavController) {
        binding.bottomNavView.setupWithNavController(navController)
    }

    fun getNavController(): NavController {
        return navController
    }


}

