package com.udacity.shoestore.shoeList

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

@BindingAdapter("shoeImage")
fun setImageResource(view: ImageView, item: Shoe) {

    item.let {
        Glide.with(view.context)
            .load(it.images[0]).centerInside()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .error(R.drawable.ic_baseline_error_24)
            )
            .into(view)
    }

}

@BindingAdapter("shoeName")
fun TextView.setShoeName(item: Shoe) {
    item.let {
        text = it.name
    }
}

@BindingAdapter("shoeCompany")
fun TextView.setCompanyName(item: Shoe) {
    item.let {
        text = it.company
    }
}

@BindingAdapter("shoeSize")
fun TextView.setShoeSize(item: Shoe) {
    item.let {
        text = it.size.toString()
    }
}

