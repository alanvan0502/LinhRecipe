package com.alanvan.linhrecipe.features.recipe_details

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
import com.alanvan.linhrecipe.LRApplication
import com.alanvan.linhrecipe.R
import com.alanvan.linhrecipe.ViewState
import com.alanvan.linhrecipe.utilities.increaseTouchableArea
import kotlinx.android.synthetic.main.fragment_recipe_details.*
import kotlinx.android.synthetic.main.fragment_recipe_details.back_button
import kotlinx.android.synthetic.main.fragment_recipe_details.recyclerView
import kotlinx.android.synthetic.main.fragment_search_by_type.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class RecipeDetailFragment : Fragment(), KodeinAware {

    override val kodein: Kodein = LRApplication.kodein

    private val args: RecipeDetailFragmentArgs by navArgs()

    private lateinit var recipeDetailViewModel: RecipeDetailViewModel
    private val epoxyController = RecipeDetailsEpoxyController(kodein)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.setController(epoxyController)

        val recipeId = args.recipeId
        val recipeName = args.recipeName
        setupAppbar(recipeName)

        recipeDetailViewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)
        recipeDetailViewModel.getRecipe(recipeId)
        recipeDetailViewModel.recipeDetails().observe(viewLifecycleOwner, Observer {
            epoxyController.setData(it)
        })
        recipeDetailViewModel.viewState().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ViewState.Error -> {
                    // TODO: retry
                }
                is ViewState.Loading -> {
                    // TODO: show loading
                }
            }
        })
    }

    private fun setupAppbar(recipeName: String) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        back_button.apply {
            increaseTouchableArea()
            setOnClickListener {
                activity?.onBackPressed()
            }
        }
        recipe_name.text = recipeName
    }

}