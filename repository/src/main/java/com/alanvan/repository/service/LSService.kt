package com.alanvan.repository.service

import com.alanvan.repository.data.model.recipe_details.RecipeDetailsResponse
import com.alanvan.repository.data.model.recipe_types.RecipeTypesResponse
import com.alanvan.repository.data.model.search.RecipesResponse
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface LSService {
    @POST("/rest/server.api")
    fun getRecipeTypes(
        @Query("method") method: String,
        @Query("format") format: String,
        @Query("token") token: String
    ): Single<RecipeTypesResponse>

    @POST("/rest/server.api")
    fun searchRecipe(
        @Query("method") method: String,
        @Query("format") format: String,
        @Query("token") token: String,
        @Query("search_expression") searchExpression: String,
        @Query("recipe_type") recipeType: String,
        @Query("page_number") pageNumber: Int,
        @Query("max_results") maxResults: Int
    ): Single<RecipesResponse>

    @POST("/rest/server.api")
    fun getRecipe(
        @Query("method") method: String,
        @Query("format") format: String,
        @Query("token") token: String,
        @Query("recipe_id") recipeId: String
    ): Single<RecipeDetailsResponse>
}