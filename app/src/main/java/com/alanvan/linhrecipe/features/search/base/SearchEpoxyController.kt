package com.alanvan.linhrecipe.features.search.base

import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.alanvan.domain.model.search.Recipes
import com.alanvan.linhrecipe.features.search.base.view_item.RecipeEpoxyModel_


class SearchEpoxyController(private val actionListener: SearchEpoxyControllerActionListener) :
    PagedListEpoxyController<Recipes.Recipe>() {

    override fun buildItemModel(currentPosition: Int, item: Recipes.Recipe?): EpoxyModel<*> {
        return if (item == null) {
            RecipeEpoxyModel_()
                .id(-currentPosition)
                .recipeName("loading $currentPosition")
        } else {
            RecipeEpoxyModel_()
                .id(item.recipe_id)
                .recipeName(item.recipe_name)
                .recipeImage(item.recipe_image)
                .recipeCalories(item.recipe_nutrition.calories.toString())
                .recipeCarbohydrate(item.recipe_nutrition.carbohydrate.toString())
                .recipeFat(item.recipe_nutrition.fat.toString())
                .recipeProtein(item.recipe_nutrition.protein.toString())
                .clickListener(View.OnClickListener {
                    actionListener.onRecipeClick(item.recipe_name, item.recipe_id)
                })
        }
    }

    interface SearchEpoxyControllerActionListener {
        fun onRecipeClick(recipeName: String, recipeId: String)
    }
}