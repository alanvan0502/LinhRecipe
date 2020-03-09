package com.alanvan.repository.data.model.recipe_details

import com.alanvan.domain.base.DataMapper
import com.alanvan.domain.model.recipe_details.RecipeDetails

class RecipeDetailsMapper : DataMapper<RecipeDetailsResponse, RecipeDetails>() {

    override fun map(source: RecipeDetailsResponse): RecipeDetails {
        return RecipeDetails(
            recipeName = source.recipe.recipe_name,
            cookingTime = source.recipe.cooking_time_min.toString(),
            direction = source.recipe.directions.direction?.map { it.direction_description }
                ?: emptyList(),
            ingredients = source.recipe.ingredients.ingredient?.map { it.ingredient_description }
                ?: emptyList(),
            rating = source.recipe.rating,
            recipeDescription = source.recipe.recipe_description,
            recipeImage = source.recipe.recipe_images.recipe_image
        )
    }

}