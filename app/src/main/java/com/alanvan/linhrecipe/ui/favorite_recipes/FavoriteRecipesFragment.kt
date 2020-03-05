package com.alanvan.linhrecipe.ui.favorite_recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alanvan.linhrecipe.R

class FavoriteRecipesFragment : Fragment() {

    private lateinit var toolsViewModel: FavoriteRecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsViewModel =
            ViewModelProviders.of(this).get(FavoriteRecipesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorite_recipes, container, false)
        val textView: TextView = root.findViewById(R.id.text_tools)
        toolsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}