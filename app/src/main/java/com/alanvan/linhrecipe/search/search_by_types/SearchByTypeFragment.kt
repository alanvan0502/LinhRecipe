package com.alanvan.linhrecipe.search.search_by_types

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanvan.linhrecipe.R
import com.alanvan.linhrecipe.utilities.increaseTouchableArea
import kotlinx.android.synthetic.main.fragment_home.recyclerView
import kotlinx.android.synthetic.main.fragment_search_by_type.*

class SearchByTypeFragment : Fragment() {

    private lateinit var searchViewModel: SearchByTypeViewModel
    private lateinit var controller: SearchByTypeEpoxyController

    private val args: SearchByTypeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()
        searchViewModel = ViewModelProvider(this).get(SearchByTypeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search_by_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeType = args.recipeType
        back_button.apply {
            increaseTouchableArea()
            setOnClickListener {
                findNavController().navigate(R.id.nav_home)
            }
        }
        recipe_type.text = recipeType

        controller = SearchByTypeEpoxyController()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = controller.adapter

        searchViewModel = ViewModelProvider(this).get(SearchByTypeViewModel::class.java)
        searchViewModel.initialize(recipeType)
        searchViewModel.recipeList?.observe(viewLifecycleOwner, Observer {
            controller.submitList(it)
        })
    }
}