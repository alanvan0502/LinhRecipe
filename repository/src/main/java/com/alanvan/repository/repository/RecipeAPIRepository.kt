package com.alanvan.repository.repository

import com.alanvan.domain.model.home.RecipeType
import com.alanvan.domain.model.recipe_details.RecipeDetails
import com.alanvan.domain.model.search.Recipes
import com.alanvan.domain.repository.RecipeRepository
import com.alanvan.repository.data.injection.Modules
import com.alanvan.repository.data.model.recipe_details.RecipeDetailsMapper
import com.alanvan.repository.data.model.recipe_types.RecipeTypesMapper
import com.alanvan.repository.service.LSService
import io.reactivex.Single
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class RecipeAPIRepository : RecipeRepository, KodeinAware {
    override val kodein: Kodein = Modules.container

    private val service: LSService by instance()

    override fun getRecipeTypes(token: String?): Single<List<RecipeType>> {
        return service.getRecipeTypes("recipe_types.get", "json", token ?: "")
            .map { RecipeTypesMapper().map(it) }
    }

    override fun searchRecipes(
        token: String?,
        searchExpression: String,
        recipeType: String,
        pageNumber: Int,
        maxResults: Int
    ): Single<Recipes> {
        return service.searchRecipe(
            method = "recipes.search",
            format = "json",
            token = token ?: "",
            searchExpression = searchExpression,
            recipeType = recipeType,
            pageNumber = pageNumber,
            maxResults = maxResults
        ).map {
            it.recipes
        }
    }

    override fun getRecipe(token: String?, recipeId: String): Single<RecipeDetails> {
        return service.getRecipe(
            method = "recipe.get",
            format = "json",
            token = token ?: "",
            recipeId = recipeId
        ).map {
            RecipeDetailsMapper().map(it)
        }
    }
}