package com.alanvan.repository.data.model.recipe_details

data class RecipeDetailsResponse(
    val recipe: RecipeDetail
) {
    data class RecipeDetail(
        val cooking_time_min: Int,
        val directions: Directions,
        val ingredients: Ingredients,
        val number_of_servings: Int,
        val preparation_time_min: Int,
        val rating: String?,
        val recipe_description: String,
        val recipe_id: Long,
        val recipe_name: String,
        val recipe_url: String
    ) {

        data class Directions(
            val direction: List<Direction>?
        ) {
            data class Direction(
                val direction_description: String,
                val direction_number: Int
            )
        }


        data class Ingredients(
            val ingredient: List<Ingredient>?
        ) {
            data class Ingredient(
                val food_id: Long,
                val food_name: String,
                val ingredient_description: String,
                val ingredient_url: String,
                val measurement_description: String,
                val number_of_units: Float,
                val serving_id: Long
            )
        }
    }
}