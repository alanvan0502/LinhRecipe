package com.alanvan.linhrecipe.search.search_by_name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanvan.linhrecipe.R
import kotlinx.android.synthetic.main.fragment_home.*

class SearchByNameFragment : Fragment() {

//    private lateinit var searchViewModel: SearchByTypeViewModel
//    private lateinit var controller: SearchByTypeEpoxyController
//
//    private val args: SearchFragmentArgs by navArgs()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        searchViewModel = ViewModelProvider(this).get(SearchByTypeViewModel::class.java)
//        return inflater.inflate(R.layout.fragment_search, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        controller = SearchByTypeEpoxyController()
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.adapter = controller.adapter
//
//        val recipeType = args.recipeType
//        searchViewModel = ViewModelProvider(this).get(SearchByTypeViewModel::class.java)
//        searchViewModel.initialize(recipeType)
//        searchViewModel.recipeList?.observe(viewLifecycleOwner, Observer {
//            controller.submitList(it)
//        })
//    }
}