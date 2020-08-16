package com.example.coronastats

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.coronastats.databinding.ActivityMainBinding


class CovidMainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.btnIndianData.setOnClickListener {
            navController.navigate(R.id.indian_covid_stats)
        }


    }

    fun getNavController(): NavController {
        return navController
    }

    fun hideButton() {
        binding.btnIndianData.visibility = View.VISIBLE
    }

    fun showButton() {
        binding.btnIndianData.visibility = View.VISIBLE
    }

}

