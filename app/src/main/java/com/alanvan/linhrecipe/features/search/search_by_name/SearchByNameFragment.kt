package com.alanvan.linhrecipe.features.search.search_by_name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanvan.linhrecipe.R
import com.alanvan.linhrecipe.features.search.base.SearchEpoxyController
import com.alanvan.linhrecipe.features.search.base.SearchViewModel
import com.alanvan.linhrecipe.utilities.hideKeyboard
import com.alanvan.linhrecipe.utilities.increaseTouchableArea
import kotlinx.android.synthetic.main.fragment_home.recyclerView
import kotlinx.android.synthetic.main.fragment_search_by_name.*
import kotlinx.android.synthetic.main.fragment_search_by_type.*
import kotlinx.android.synthetic.main.fragment_search_by_type.search
import kotlinx.android.synthetic.main.fragment_search_by_type.search_text

class SearchByNameFragment : Fragment(), SearchEpoxyController.SearchEpoxyControllerActionListener {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var controller: SearchEpoxyController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(this).get(javaClass.name, SearchViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search_by_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller = SearchEpoxyController(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = controller.adapter

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.initialize("")
        searchViewModel.recipeList?.observe(viewLifecycleOwner, Observer {
            controller.submitList(it)
        })

        setupAppbar()
        setupSearch()
    }

    private fun setupAppbar() {
        val appBar = (activity as AppCompatActivity).supportActionBar
        if (appBar != null && !appBar.isShowing) {
            appBar.show()
        }
    }

    private fun setupSearch() {
        search.apply {
            increaseTouchableArea()
            setOnClickListener {
                searchViewModel.search(search_text.text.toString())
                it.hideKeyboard()
            }
        }
    }

    override fun onRecipeClick(recipeName: String, recipeId: String, recipeImage: String) {
        val action = SearchByNameFragmentDirections
            .actionSearchByNameToRecipeDetailFragment(
                recipeId = recipeId,
                recipeName = recipeName,
                recipeImage = recipeImage
            )
        findNavController().navigate(action)
    }

    override fun onPause() {
        search.hideKeyboard()
        super.onPause()
    }
}