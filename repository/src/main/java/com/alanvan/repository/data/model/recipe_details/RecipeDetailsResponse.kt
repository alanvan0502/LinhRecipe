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
        val rating: Int,
        val recipe_categories: Categories,
        val recipe_description: String,
        val recipe_id: Long,
        val recipe_images: Images,
        val recipe_name: String,
        val recipe_types: RecipeTypes,
        val recipe_url: String,
        val serving_sizes: ServingSizes
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

        data class Categories(
            val recipe_category: List<Category>?
        ) {
            data class Category(
                val recipe_category_name: String,
                val recipe_category_url: String
            )
        }

        data class Images(
            val recipe_image: String
        )

        data class RecipeTypes(
            val recipe_type: List<String>?
        )

        data class ServingSizes(
            val serving: Serving
        ) {
            data class Serving(
                val calcium: Int,
                val calories: Int,
                val carbohydrate: Double,
                val cholesterol: Int,
                val fat: Double,
                val fiber: Double,
                val iron: Int,
                val monounsaturated_fat: Double,
                val polyunsaturated_fat: Double,
                val potassium: Int,
                val protein: Double,
                val saturated_fat: Double,
                val serving_size: String,
                val sodium: Int,
                val sugar: Double,
                val trans_fat: Int,
                val vitamin_a: Int,
                val vitamin_c: Int
            )
        }
    }
}