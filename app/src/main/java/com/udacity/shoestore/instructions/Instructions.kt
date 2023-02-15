package com.udacity.shoestore.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.welcome.WelcomeViewModel


class Instructions : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentInstructionsBinding.inflate(layoutInflater)

        // Declare and attach viewModel and binder
        val viewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)
        binding.viewModel = viewModel

        // Navigate to Shoes List Page on next button click
        viewModel.nextButtonClicked.observe(viewLifecycleOwner, Observer {
            if (it) {
                this@Instructions.findNavController().navigate(InstructionsDirections.actionInstructionsToShoeList())
                viewModel.navigatedToShoesList()
            }
        })

        return binding.root
    }
}