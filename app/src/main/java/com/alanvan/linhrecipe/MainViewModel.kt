package com.alanvan.linhrecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.domain.features.recipe_types.GetRecipeTypesUseCase
import com.alanvan.linhrecipe.features.account.AccountManager
import com.alanvan.domain.model.recipe_type.RecipeType
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainViewModel : ViewModel(), KodeinAware {

    override val kodein: Kodein = LRApplication.kodein
    private val getRecipeTypesUseCase: GetRecipeTypesUseCase by instance()

    private val viewStateLiveData = MutableLiveData<ViewState<List<RecipeType>>>()
    private val recipeTypesLiveData = MutableLiveData<List<RecipeType>>()

    fun viewState() = viewStateLiveData
    fun recipeTypes() = recipeTypesLiveData

    fun getRecipeTypes() {
        getRecipeTypesUseCase.loadWithLiveData(viewStateLiveData, recipeTypesLiveData).invoke(
            GetRecipeTypesUseCase.Params(AccountManager.getAuthToken())
        )
    }
}