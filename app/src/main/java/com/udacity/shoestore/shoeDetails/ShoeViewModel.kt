package com.udacity.shoestore.shoeDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    val shoeDetailName = MutableLiveData<String>()
    val shoeDetailCompany = MutableLiveData<String>()
    val shoeDetailDescription = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val image = MutableLiveData<String>()

    private val _isNameError = MutableLiveData<Boolean>()
    val isNameError: LiveData<Boolean>
        get() = _isNameError

    private val _isCompanyError = MutableLiveData<Boolean>()
    val isCompanyError: LiveData<Boolean>
        get() = _isCompanyError

    private val _isDescriptionError = MutableLiveData<Boolean>()
    val isDescriptionError: LiveData<Boolean>
        get() = _isDescriptionError

    private val _isShoeSizeError = MutableLiveData<Boolean>()
    val isShoeSizeError: LiveData<Boolean>
        get() = _isShoeSizeError

    private val _isImageError = MutableLiveData<Boolean>()
    val isImageError: LiveData<Boolean>
        get() = _isImageError

    private val _saveShoeDetails = MutableLiveData<Boolean>()
    val saveShoeDetails: LiveData<Boolean>
        get() = _saveShoeDetails

    private val _refreshImageClicked = MutableLiveData<Boolean>()
    val refreshImageClicked: LiveData<Boolean>
        get() = _refreshImageClicked

    private val _downloadImage = MutableLiveData<Boolean>()
    val downloadImage: LiveData<Boolean>
        get() = _downloadImage

    private val _addNewClicked = MutableLiveData<Boolean>()
    val addNewClicked: LiveData<Boolean>
        get() = _addNewClicked

    var imagesList = mutableListOf<String>()

    private val _shoeModelList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeModelList :LiveData<MutableList<Shoe>>
        get() = _shoeModelList

    private var imageLoaded = false

    // For Shoes List Fragment - FAB button
    fun shoeDetail() {
        _addNewClicked.value = true
    }

    fun navigatedToShoeDetail() {
        _addNewClicked.value = false
    }

    // For Shoes Details Page - Save Button
    fun saveDetails() {

        // Validate the fields
        validateInput()

        // Allow save button based on validations done above
        _saveShoeDetails.value = !(_isImageError.value == true || _isCompanyError.value == true ||
                _isShoeSizeError.value == true || _isDescriptionError.value == true || _isImageError.value == true || imageLoaded)
    }

    private fun validateInput() {

        // Edittext validations

        _isNameError.value = shoeDetailName.value?.trim().isNullOrEmpty()
        _isCompanyError.value = shoeDetailCompany.value?.trim().isNullOrEmpty()
        _isDescriptionError.value = shoeDetailDescription.value?.trim().isNullOrEmpty()

        try {
            _isShoeSizeError.value = ((shoeSize.value?.toDouble() ?: 0.0) < 1.0 )|| ((shoeSize.value?.toDouble() ?: 0.0) > 50.0)
        } catch (e:Exception) {
            _isShoeSizeError.value = true
        }

        onDownLoadImageClicked()

    }

    fun navigatedToMainPage() {
        _saveShoeDetails.value = false
    }

    // DownLoad Image button
    fun onDownLoadImageClicked() {
        if (!image.value?.trim().isNullOrEmpty()) {
            _downloadImage.value = true
            imageLoaded = false
        } else {
            _downloadImage.value = false
            _isImageError.value = true
        }
    }

    // Error in Image
    fun imageLoadingError() {
        _isImageError.value = true
    }

    fun imageLoadingSuccess() {
        imagesList.add(image.value.toString())
        _isImageError.value = false
        _downloadImage.value = false
        imageLoaded = true
    }

    // Disable/Enable Image URL Edittext
    fun openImageField() {
        _refreshImageClicked.value = true
        _isImageError.value = false
    }

    fun closeImageField() {
        _refreshImageClicked.value = false
    }

    fun addShoeDetailToList() {
        val shoeDetail = Shoe(shoeDetailName.value!!
            , shoeSize.value?.toDouble() ?: 0.0, shoeDetailCompany.value!! , shoeDetailDescription.value!!, imagesList )
        _shoeModelList.value?.add(shoeDetail)
        Log.i("viewModel", shoeModelList.value.toString())
    }

    fun clearDetailScreen() {
        shoeDetailName.value = ""
        shoeDetailCompany.value = ""
        shoeSize.value = ""
        shoeDetailDescription.value = ""
        image.value = ""
        imagesList = mutableListOf()
        openImageField()
        _isShoeSizeError.value = false
        _isImageError.value = false
        _isNameError.value = false
        _isCompanyError.value = false
        _isDescriptionError.value = false
    }

}


