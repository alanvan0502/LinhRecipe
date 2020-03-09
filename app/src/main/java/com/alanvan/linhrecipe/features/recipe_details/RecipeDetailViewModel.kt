package com.alanvan.linhrecipe.features.recipe_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.domain.features.recipe_details.GetRecipeDetailsUseCase
import com.alanvan.domain.model.recipe_details.RecipeDetails
import com.alanvan.linhrecipe.LRApplication
import com.alanvan.linhrecipe.ViewState
import com.alanvan.linhrecipe.features.account.AccountManager
import com.alanvan.linhrecipe.loadWithLiveData
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class RecipeDetailViewModel : ViewModel(), KodeinAware {

    override val kodein: Kodein = LRApplication.kodein

    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase by instance()

    private val viewStateLiveData = MutableLiveData<ViewState<RecipeDetails>>()
    private val recipeDetailsLiveData = MutableLiveData<RecipeDetails>()

    fun viewState() = viewStateLiveData
    fun recipeDetails() = recipeDetailsLiveData

    fun getRecipe(recipeId: String) {
        getRecipeDetailsUseCase.loadWithLiveData(viewStateLiveData, recipeDetailsLiveData).invoke(
            GetRecipeDetailsUseCase.Params(AccountManager.getAuthToken(), recipeId)
        )
    }
}