package com.alanvan.domain.repository

import com.alanvan.domain.model.home.RecipeType
import com.alanvan.domain.model.recipe_details.RecipeDetails
import com.alanvan.domain.model.search.Recipes
import io.reactivex.Single

interface RecipeRepository {
    fun getRecipeTypes(token: String?): Single<List<RecipeType>>

    fun searchRecipes(token: String?,
                      searchExpression: String,
                      recipeType: String,
                      pageNumber: Int,
                      maxResults: Int): Single<Recipes>

    fun getRecipe(token: String?, recipeId: String): Single<RecipeDetails>
}