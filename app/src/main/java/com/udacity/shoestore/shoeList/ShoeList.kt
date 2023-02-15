package com.udacity.shoestore.shoeList

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ListItemBinding
import com.udacity.shoestore.shoeDetails.ShoeViewModel
import timber.log.Timber


class ShoeList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentShoeListBinding.inflate(layoutInflater)

        // Declare and attach viewModel and binder
        val viewModel: ShoeViewModel by viewModels(
            ownerProducer = { requireParentFragment() }
        )

        binding.viewModel = viewModel
        binding.toolBar.inflateMenu(R.menu.shoe_list_menu)
        binding.toolBar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { it ->
            if (it.itemId == R.id.log_out) {
                this@ShoeList.findNavController().navigate(ShoeListDirections.actionShoeListToLogin2())
            }
            return@OnMenuItemClickListener false
        })

        // Navigate to Detail Page on FAB Click
        viewModel.addNewClicked.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.clearDetailScreen()
                this@ShoeList.findNavController().navigate(ShoeListDirections.actionShoeListToShoeDetails())
                viewModel.navigatedToShoeDetail()
            }
        })

        // Add Views on Linear Layout for each item

        viewModel.shoeModelList.observe(viewLifecycleOwner, Observer {

            Timber.i(viewModel.shoeModelList.toString())
            
            it.forEach { shoeElement ->
                val listItemBinding = ListItemBinding.inflate(layoutInflater)
                listItemBinding.viewModel = shoeElement
                listItemBinding.executePendingBindings()
                binding.list.addView(listItemBinding.root)
            }
        })

        return binding.root

    }
}