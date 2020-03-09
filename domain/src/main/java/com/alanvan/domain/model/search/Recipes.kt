package com.alanvan.domain.model.search

data class Recipes (
    val max_results: Int,
    val page_number: Int,
    val recipe: List<Recipe>?,
    val total_results: Int
) {
    data class Recipe (
        val recipe_description: String,
        val recipe_id: String,
        val recipe_image: String,
        val recipe_name: String,
        val recipe_nutrition: Nutrition,
        val recipe_url: String
    ) {
        data class Nutrition (
            val calories: Int,
            val carbohydrate: Double,
            val fat: Double,
            val protein: Double
        )
    }
}