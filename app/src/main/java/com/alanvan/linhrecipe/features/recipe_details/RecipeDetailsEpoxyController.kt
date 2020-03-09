package com.alanvan.linhrecipe.features.recipe_details

import com.airbnb.epoxy.TypedEpoxyController
import com.alanvan.domain.model.recipe_details.RecipeDetails
import com.alanvan.linhrecipe.features.recipe_details.view_item.RecipeHeaderEpoxyModel_
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class RecipeDetailsEpoxyController(
    override val kodein: Kodein
) : TypedEpoxyController<RecipeDetails>(), KodeinAware {

    override fun buildModels(data: RecipeDetails?) {
        RecipeHeaderEpoxyModel_()
            .id("${javaClass.name}-0")
            .recipeDescription(data?.recipeDescription)
            .recipeImage(data?.recipeImage)
            .rating(data?.rating)
            .addTo(this)
    }
}