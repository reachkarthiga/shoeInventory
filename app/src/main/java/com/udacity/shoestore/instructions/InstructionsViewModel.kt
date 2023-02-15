package com.udacity.shoestore.instructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionsViewModel:ViewModel() {
    private val _nextButtonClicked = MutableLiveData<Boolean>()
    val nextButtonClicked : LiveData<Boolean>
        get() = _nextButtonClicked


    fun onNextClicked() {
        _nextButtonClicked.value = true
    }

    fun navigatedToShoesList() {
        _nextButtonClicked.value = false
    }
}