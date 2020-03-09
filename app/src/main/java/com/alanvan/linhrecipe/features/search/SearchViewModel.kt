package com.alanvan.linhrecipe.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alanvan.domain.model.search.Recipes
import com.alanvan.linhrecipe.features.search.datasource.RecipeDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel : ViewModel() {

    var recipeList: LiveData<PagedList<Recipes.Recipe>>? = null

    companion object {
        const val PAGE_SIZE = 20
    }

    private val compositeDisposable = CompositeDisposable()
    private lateinit var sourceFactory: RecipeDataSourceFactory

    fun initialize(recipeType: String) {
        sourceFactory = RecipeDataSourceFactory(
            compositeDisposable = compositeDisposable,
            searchExpression = "",
            recipeType = recipeType
        )
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE * 2)
            .setEnablePlaceholders(false)
            .build()

        recipeList = LivePagedListBuilder<Int, Recipes.Recipe>(sourceFactory, config).build()
    }
}