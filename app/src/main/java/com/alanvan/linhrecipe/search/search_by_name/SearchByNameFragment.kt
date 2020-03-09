package com.alanvan.linhrecipe.search.search_by_name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanvan.linhrecipe.R
import com.alanvan.linhrecipe.search.base.SearchViewModel
import com.alanvan.linhrecipe.search.base.SearchEpoxyController
import com.alanvan.linhrecipe.utilities.increaseTouchableArea
import kotlinx.android.synthetic.main.fragment_home.recyclerView
import kotlinx.android.synthetic.main.fragment_search_by_type.*

class SearchByNameFragment : Fragment() {

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

        controller = SearchEpoxyController()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = controller.adapter

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.initialize("")
        searchViewModel.recipeList?.observe(viewLifecycleOwner, Observer {
            controller.submitList(it)
        })

        setupSearch()
    }

    private fun setupSearch() {
        search.apply {
            increaseTouchableArea()
            setOnClickListener {
                searchViewModel.search(search_text.text.toString())
            }
        }
    }
}