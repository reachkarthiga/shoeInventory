package com.udacity.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding


class Login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(layoutInflater)

        // Declare and attach viewModel and binder
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Navigate to Welcome page on login button click
        viewModel.loginButtonClicked.observe(viewLifecycleOwner, Observer {
            if (it) {
                this@Login.findNavController().navigate(LoginDirections.actionLoginToWelcome())
                viewModel.navigatedToWelcome()
            }
        })

        // Navigate to Sign up Page on sign up button click
        viewModel.signUpButtonClicked.observe(viewLifecycleOwner, Observer {
            if (it) {
                this@Login.findNavController().navigate(LoginDirections.actionLoginToSignUp())
                viewModel.navigatedToSignUp()
            }
        })

        // Show/Hide error for email id
        viewModel.isEmailError.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.errorEmail.text = resources.getString(R.string.invalid_email)
                binding.errorEmail.visibility = VISIBLE
            } else {
                binding.errorEmail.visibility = INVISIBLE
            }
        })

        //Show/Hide error for password
        viewModel.isPasswordError.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.errorPassword.text = resources.getString(R.string.invalid_password)
                binding.errorPassword.visibility = VISIBLE
            } else {
                binding.errorPassword.visibility = INVISIBLE
            }
        })

        return binding.root

    }

}