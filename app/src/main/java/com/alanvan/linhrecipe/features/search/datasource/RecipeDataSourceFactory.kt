package com.alanvan.linhrecipe.features.search.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.alanvan.domain.model.search.Recipes
import io.reactivex.disposables.CompositeDisposable

class RecipeDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val searchExpression: String,
    private val recipeType: String
) : DataSource.Factory<Int, Recipes.Recipe>() {

    val recipeDataSourceLiveData = MutableLiveData<RecipeDataSource>()

    override fun create(): DataSource<Int, Recipes.Recipe> {
        val recipeDataSource = RecipeDataSource(compositeDisposable, searchExpression, recipeType)
        recipeDataSourceLiveData.postValue(recipeDataSource)
        return recipeDataSource
    }
}