package com.udacity.shoestore.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel :ViewModel() {

    private val _nextButtonClicked = MutableLiveData<Boolean>()
    val nextButtonClicked :LiveData<Boolean>
        get() = _nextButtonClicked

    fun onNextClicked() {
        _nextButtonClicked.value = true
    }

    fun navigatedToInstructions() {
        _nextButtonClicked.value = false
    }

}