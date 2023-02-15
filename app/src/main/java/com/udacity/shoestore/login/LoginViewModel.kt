package com.udacity.shoestore.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {

    private val _loginButtonClicked = MutableLiveData<Boolean>()
    val loginButtonClicked: LiveData<Boolean>
        get() = _loginButtonClicked

    private val _signUpButtonClicked = MutableLiveData<Boolean>()
    val signUpButtonClicked: LiveData<Boolean>
        get() = _signUpButtonClicked

    private val _isEmailError = MutableLiveData<Boolean>()
    val isEmailError: LiveData<Boolean>
        get() = _isEmailError

    private val _isPasswordError = MutableLiveData<Boolean>()
    val isPasswordError: LiveData<Boolean>
        get() = _isPasswordError

    val _email = MutableLiveData<String>()
    val _password = MutableLiveData<String>()


    fun loginClicked() {

        Log.i("ViewModel" , "${_email.value} , ${_password.value}")
        // Validate the inputs and allow login based on the validations
        validateInputs()
        _loginButtonClicked.value = !(_isEmailError.value == true || _isPasswordError.value == true)

    }

    private fun validateInputs() {

        // Validate Email
        if (_email.value?.trim().isNullOrEmpty()) {
            _isEmailError.value = true
        } else {
            _isEmailError.value = !(_email.value!!.contains(".com") && _email.value!!.contains("@"))
        }

        // Validate Password
        _isPasswordError.value = _password.value?.trim().isNullOrEmpty()

    }

    fun signUpClicked() {
        _signUpButtonClicked.value = true
    }

    fun navigatedToWelcome() {
        _loginButtonClicked.value = false
    }

    fun navigatedToSignUp() {
        _signUpButtonClicked.value = false
    }


}