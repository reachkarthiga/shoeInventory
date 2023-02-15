package com.udacity.shoestore.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel:ViewModel() {

    private val _signUpButtonClicked = MutableLiveData<Boolean>()
    val signUpButtonClicked: LiveData<Boolean>
        get() = _signUpButtonClicked

    private val _isEmailError = MutableLiveData<Boolean>()
    val isEmailError: LiveData<Boolean>
        get() = _isEmailError

    private val _isPasswordError = MutableLiveData<Boolean>()
    val isPasswordError: LiveData<Boolean>
        get() = _isPasswordError

    var _email = MutableLiveData<String>()
    var _password = MutableLiveData<String>()


    fun signUpClicked() {

        // Validate the fields
        validateInputs()

        // Allow Sign Up process based on above field validations
        _signUpButtonClicked.value = !(_isEmailError.value == true || _isPasswordError.value == true)
    }

    private fun validateInputs() {

        // Email Id validation
        if (_email.value?.trim().isNullOrEmpty()) {
            _isEmailError.value = true
        } else {
            _isEmailError.value = !(_email.value!!.contains(".com") && _email.value!!.contains("@"))
        }

        // Password validation
        if (_email.value?.trim().isNullOrEmpty()) {
            _isPasswordError.value = true
        } else {
            _isPasswordError.value = !(_password.value!!.length > 8
                    && _password.value!!.any { it.isDigit() }
                    && _password.value!!.any { it.isUpperCase() }
                    && _password.value!!.any { it.isLowerCase() })
        }

    }

    fun navigatedToHome() {
        _signUpButtonClicked.value = false
    }

}