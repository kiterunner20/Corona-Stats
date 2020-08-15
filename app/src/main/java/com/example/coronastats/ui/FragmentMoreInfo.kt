package com.example.coronastats.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.coronastats.databinding.FragmentMoreInfoBinding

class FragmentMoreInfo : BaseFragment() {

    private lateinit var binding: FragmentMoreInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMoreInfoBinding.inflate(inflater, container, false)
        val safeArgs: FragmentMoreInfoArgs by navArgs()
        binding.recivedInfo.text = safeArgs.countryname

        return binding.root

    }


}