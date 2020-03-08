package com.alanvan.domain.repository

import com.alanvan.domain.model.home.RecipeType
import io.reactivex.Single

interface RecipeRepository {
    fun getRecipeTypes(token: String?): Single<List<RecipeType>>
}