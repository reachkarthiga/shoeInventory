package com.udacity.shoestore.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.databinding.FragmentSignUpBinding
import com.udacity.shoestore.login.LoginViewModel


class SignUp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentSignUpBinding.inflate(layoutInflater)

        // Declare and attach viewModel and binder
        val viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Navigate to welcome on sign up button click
        viewModel.signUpButtonClicked.observe(viewLifecycleOwner, Observer {
            if (it) {
                this@SignUp.findNavController().navigate(SignUpDirections.actionSignUpToWelcome())
                viewModel.navigatedToHome()
            }
        })

        // Show/Hide error for email id
        viewModel.isEmailError.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.errorEmail.text = resources.getString(R.string.invalid_email)
                binding.errorEmail.visibility = View.VISIBLE
            } else {
                binding.errorEmail.visibility = View.INVISIBLE
            }
        })

        // Show/Hide error for password
        viewModel.isPasswordError.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.errorPassword.text = resources.getString(R.string.invalid_password)
                binding.errorPassword.visibility = View.VISIBLE
            } else {
                binding.errorPassword.visibility = View.INVISIBLE
            }
        })

        return binding.root
    }

}