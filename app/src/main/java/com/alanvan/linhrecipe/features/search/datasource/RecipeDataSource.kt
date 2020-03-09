package com.alanvan.linhrecipe.features.search.datasource

import androidx.paging.PageKeyedDataSource
import com.alanvan.domain.model.search.Recipes
import com.alanvan.domain.repository.RecipeRepository
import com.alanvan.linhrecipe.LRApplication
import com.alanvan.linhrecipe.features.account.AccountManager
import io.reactivex.disposables.CompositeDisposable
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class RecipeDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val searchExpression: String,
    private val recipeType: String
) : PageKeyedDataSource<Int, Recipes.Recipe>(), KodeinAware {

    override val kodein: Kodein = LRApplication.kodein

    private val repository: RecipeRepository by instance()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Recipes.Recipe>
    ) {
        compositeDisposable.add(
            repository.searchRecipes(
                token = AccountManager.getAuthToken(),
                searchExpression = searchExpression,
                recipeType = recipeType,
                pageNumber = 0,
                maxResults = params.requestedLoadSize
            ).subscribe({
                callback.onResult(it.recipe, 0, 1)
            }, {

            })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Recipes.Recipe>) {
        compositeDisposable.add(
            repository.searchRecipes(
                token = AccountManager.getAuthToken(),
                searchExpression = searchExpression,
                recipeType = recipeType,
                pageNumber = params.key,
                maxResults = params.requestedLoadSize
            ).subscribe({
                callback.onResult(it.recipe, params.key + 1)
            }, {

            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Recipes.Recipe>) {
        // do nothing
    }
}