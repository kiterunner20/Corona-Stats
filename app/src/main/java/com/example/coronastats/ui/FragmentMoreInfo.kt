package com.example.coronastats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.example.coronastats.CovidMainScreenActivity
import com.example.coronastats.databinding.FragmentMoreInfoBinding

class FragmentMoreInfo : BaseFragment() {

    private lateinit var binding: FragmentMoreInfoBinding
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMoreInfoBinding.inflate(inflater, container, false)
        navController = (activity as CovidMainScreenActivity).getNavController()
        val safeArgs: FragmentMoreInfoArgs by navArgs()
        binding.recivedInfo.text = safeArgs.countryname

        return binding.root

    }


}