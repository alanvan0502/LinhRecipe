package com.alanvan.linhrecipe.features.search

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.alanvan.domain.model.search.Recipes
import com.alanvan.linhrecipe.features.search.view_item.RecipeEpoxyModel_


class SearchEpoxyController: PagedListEpoxyController<Recipes.Recipe>() {

    override fun buildItemModel(currentPosition: Int, item: Recipes.Recipe?): EpoxyModel<*> {
        return if (item == null) {
            RecipeEpoxyModel_()
                .id(-currentPosition)
                .recipeName("loading $currentPosition")
        } else {
            RecipeEpoxyModel_()
                .id(item.recipe_id)
                .recipeName(item.recipe_name)
        }
    }
}