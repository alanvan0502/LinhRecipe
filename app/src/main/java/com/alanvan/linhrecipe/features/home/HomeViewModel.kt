package com.alanvan.linhrecipe.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.domain.features.home.GetRecipeTypesUseCase
import com.alanvan.domain.model.home.RecipeType
import com.alanvan.linhrecipe.LRApplication
import com.alanvan.linhrecipe.ViewState
import com.alanvan.linhrecipe.features.account.AccountManager
import com.alanvan.linhrecipe.loadWithLiveData
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class HomeViewModel : ViewModel(), KodeinAware {

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