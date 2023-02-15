package com.udacity.shoestore.shoeDetails

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding

class ShoeDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentShoeDetailsBinding.inflate(layoutInflater)

        // Declare and attach viewModel and binder
        val viewModel: ShoeViewModel by viewModels(
            ownerProducer = { requireParentFragment() }
        )

        binding.viewModel = viewModel

        // Add the shoe detail and navigate to shoes list page
        viewModel.saveShoeDetails.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.addShoeDetailToList()
                viewModel.clearDetailScreen()
                this@ShoeDetails.findNavController()
                    .navigate(ShoeDetailsDirections.actionShoeDetailsToShoeList2())
                viewModel.navigatedToMainPage()

            }
        })

        // Check if the entered url provides and actual image using Glide and show error if required
        viewModel.downloadImage.observe(viewLifecycleOwner, Observer {
            if (it) {
                // Load image with Glide
                Glide.with(this)
                    .load(binding.editTextImage.text.toString())
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            viewModel.imageLoadingError()
                            binding.errorImage.visibility = VISIBLE
                            binding.errorImage.text = resources.getString(R.string.invalidImageURL)
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            viewModel.closeImageField()
                            binding.errorImage.visibility = INVISIBLE
                            viewModel.imageLoadingSuccess()
                            return false
                        }

                    })
                    .error(R.drawable.ic_baseline_error_24)
                    .centerCrop()
                    .into(binding.img1)
            }
        })

        // Disable/Enable the Edittext for Image URL
        viewModel.refreshImageClicked.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.editTextImage.isFocusable = true
                binding.editTextImage.isFocusableInTouchMode = true
                binding.editTextImage.text?.clear()
                binding.img1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_image_24
                    )
                )
                binding.boxImage.setBackgroundColor(resources.getColor(android.R.color.white))

            } else {
                binding.editTextImage.isFocusable = false
                binding.editTextImage.isFocusableInTouchMode = false
                binding.boxImage.setBackgroundColor(resources.getColor(R.color.lightBackground))
            }
        })

        // Show/Hide error for shoe name
        viewModel.isNameError.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.errorShoeName.text = resources.getString(R.string.inavlid_name)
                binding.errorShoeName.visibility = VISIBLE
            } else {
                binding.errorShoeName.visibility = INVISIBLE
            }
        })

        // Show/Hide Error for company
        viewModel.isCompanyError.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.errorCompany.text = resources.getString(R.string.invalid_company)
                binding.errorCompany.visibility = VISIBLE
            } else {
                binding.errorCompany.visibility = INVISIBLE
            }
        })

        // Shoe/Hide Error for size
        viewModel.isShoeSizeError.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.errorSize.text = resources.getString(R.string.invalid_size)
                binding.errorSize.visibility = VISIBLE
            } else {
                binding.errorSize.visibility = INVISIBLE
            }
        })

        // Show/Hide Error for description
        viewModel.isDescriptionError.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.errorDescription.text = resources.getString(R.string.invalid_description)
                binding.errorDescription.visibility = VISIBLE
            } else {
                binding.errorDescription.visibility = INVISIBLE
            }
        })

        // Shoe/Hide Error for Image
        viewModel.isImageError.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.errorImage.text = resources.getString(R.string.add_image)
                binding.errorImage.visibility = VISIBLE
            } else {
                binding.errorImage.visibility = INVISIBLE
            }
        })

        return binding.root

    }


}