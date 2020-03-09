package com.alanvan.linhrecipe.search.base.datasource

import androidx.paging.DataSource
import com.alanvan.domain.model.search.Recipes
import io.reactivex.disposables.CompositeDisposable

class RecipeDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val searchExpression: String,
    private val recipeType: String
) : DataSource.Factory<Int, Recipes.Recipe>() {

    override fun create(): DataSource<Int, Recipes.Recipe> {
        return RecipeDataSource(compositeDisposable, searchExpression, recipeType)
    }

}