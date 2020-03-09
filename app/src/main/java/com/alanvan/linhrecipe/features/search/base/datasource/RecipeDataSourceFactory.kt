package com.alanvan.linhrecipe.features.search.base.datasource

import androidx.paging.DataSource
import com.alanvan.domain.model.search.Recipes
import io.reactivex.disposables.CompositeDisposable

class RecipeDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private var searchExpression: String,
    private val recipeType: String
) : DataSource.Factory<Int, Recipes.Recipe>() {

    private var dataSource: RecipeDataSource? = null

    override fun create(): DataSource<Int, Recipes.Recipe> {
        dataSource = RecipeDataSource(compositeDisposable, searchExpression, recipeType)
        return dataSource!!
    }

    fun invalidate(searchExpression: String) {
        this.searchExpression = searchExpression
        dataSource?.invalidate()
    }

}