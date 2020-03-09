package com.alanvan.domain.model.recipe_details

data class RecipeDetails (
    val recipeName: String,
    val cookingTime: String,
    val direction: List<String>,
    val ingredients: List<String>,
    val rating: String,
    val recipeDescription: String
)