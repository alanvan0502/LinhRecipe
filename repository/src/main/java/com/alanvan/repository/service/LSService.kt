package com.alanvan.repository.service

import com.alanvan.repository.data.model.recipe_types.RecipeTypesResponse
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface LSService {
    @POST("/rest/server.api")
    fun getRecipeTypes(@Query("method") method: String,
                       @Query("format") format: String,
                       @Query("token") token: String): Single<RecipeTypesResponse>
}