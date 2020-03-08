package com.alanvan.repository.data.model.recipe_types

import com.alanvan.domain.base.DataMapper
import com.alanvan.domain.model.recipe_type.RecipeType

class RecipeTypesMapper : DataMapper<RecipeTypesResponse, List<RecipeType>>() {

    override fun map(source: RecipeTypesResponse): List<RecipeType> {
        val result = mutableListOf<RecipeType>()
        source.recipe_types.recipe_type?.forEach { typeString ->
            when (typeString) {
                RecipeType.APPETIZER.value -> RecipeType.APPETIZER
                RecipeType.SOUP.value -> RecipeType.SOUP
                RecipeType.MAIN_DISH.value -> RecipeType.MAIN_DISH
                RecipeType.SIDE_DISH.value -> RecipeType.SIDE_DISH
                RecipeType.BAKED.value -> RecipeType.BAKED
                RecipeType.SALAD.value -> RecipeType.SALAD
                RecipeType.SAUCE.value -> RecipeType.SAUCE
                RecipeType.DESSERT.value -> RecipeType.DESSERT
                RecipeType.SNACK.value -> RecipeType.SNACK
                RecipeType.BEVERAGE.value -> RecipeType.BEVERAGE
                RecipeType.OTHER.value -> RecipeType.OTHER
                RecipeType.BREAKFAST.value -> RecipeType.BREAKFAST
                RecipeType.LUNCH.value -> RecipeType.LUNCH
                else -> null
            }?.let {
                result.add(it)
            }
        }
        return result
    }
}