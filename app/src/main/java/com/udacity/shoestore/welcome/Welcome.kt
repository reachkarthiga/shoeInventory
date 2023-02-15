package com.udacity.shoestore.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding


class Welcome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentWelcomeBinding.inflate(layoutInflater)

        // Declare and attach viewModel and binder
        val viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        binding.viewModel = viewModel

        // Navigate to Instructions Page on next button click
        viewModel.nextButtonClicked.observe(viewLifecycleOwner, Observer {
            if (it) {
                this@Welcome.findNavController().navigate(WelcomeDirections.actionWelcomeToInstructions())
                viewModel.navigatedToInstructions()
            }
        })

        return binding.root

    }

}