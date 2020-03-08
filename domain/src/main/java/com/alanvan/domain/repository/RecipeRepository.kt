package com.alanvan.domain.repository

import com.alanvan.domain.model.recipe_type.RecipeType
import io.reactivex.Single

interface RecipeRepository {
    fun getRecipeTypes(token: String?): Single<List<RecipeType>>
}