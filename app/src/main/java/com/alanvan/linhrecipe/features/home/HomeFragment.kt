package com.alanvan.linhrecipe.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alanvan.linhrecipe.LRApplication
import com.alanvan.linhrecipe.R
import com.alanvan.linhrecipe.ViewState
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class HomeFragment : Fragment(), HomeEpoxyController.HomeEpoxyControllerActionListener, KodeinAware {

    override val kodein: Kodein = LRApplication.kodein

    private lateinit var homeViewModel: HomeViewModel
    private val epoxyController = HomeEpoxyController(kodein, this)

    companion object {
        const val SPAN_COUNT = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        recyclerView.setController(epoxyController)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getRecipeTypes()
        homeViewModel.recipeTypes().observe(viewLifecycleOwner, Observer {
            epoxyController.setData(it)
        })
        homeViewModel.viewState().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ViewState.Error -> {
                    // retry
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onRecipeTypeClick(recipeType: String) {
        val action = HomeFragmentDirections.actionSearchByRecipeTypes(recipeType)
        findNavController().navigate(action)
    }
}