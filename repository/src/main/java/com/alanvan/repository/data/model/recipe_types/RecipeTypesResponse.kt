package com.alanvan.repository.data.model.recipe_types

data class RecipeTypesResponse(
    val recipe_types: RecipeTypesResponseData
) {
    data class RecipeTypesResponseData(
        val recipe_type: List<String>?
    )
}