package com.alanvan.linhrecipe.features.recipe_details

import android.content.Context
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.alanvan.domain.model.recipe_details.RecipeDetails
import com.alanvan.linhrecipe.R
import com.alanvan.linhrecipe.features.recipe_details.view_item.RecipeHeaderEpoxyModel_
import com.alanvan.linhrecipe.features.recipe_details.view_item.RecipeInstructionEpoxyModel_
import com.alanvan.linhrecipe.features.recipe_details.view_item.RecipeInstructionHeaderEpoxyModel_
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class RecipeDetailsEpoxyController(
    override val kodein: Kodein
) : Typed2EpoxyController<RecipeDetails, String>(), KodeinAware {

    private val context: Context by instance()

    override fun buildModels(data1: RecipeDetails?, data2: String) {
        RecipeHeaderEpoxyModel_()
            .id("${javaClass.name}-header")
            .recipeDescription(data1?.recipeDescription)
            .recipeImage(data2)
            .rating(data1?.rating)
            .cookingTime(data1?.cookingTime)
            .addTo(this)

        RecipeInstructionHeaderEpoxyModel_()
            .id("${javaClass.name}-ingredients")
            .content(context.getString(R.string.ingredients))
            .addTo(this)

        data1?.let {
            it.ingredients.forEachIndexed { index, item ->
                RecipeInstructionEpoxyModel_()
                    .id("${javaClass.name}-ingredients-item-$index")
                    .content(context.getString(R.string.item, item))
                    .addTo(this)
            }
        }

        RecipeInstructionHeaderEpoxyModel_()
            .id("${javaClass.name}-directions")
            .content(context.getString(R.string.directions))
            .addTo(this)

        data1?.let {
            it.direction.forEachIndexed { index, item ->
                RecipeInstructionEpoxyModel_()
                    .id("${javaClass.name}-directions-item-$index")
                    .content(context.getString(R.string.item, item))
                    .addTo(this)
            }
        }
    }
}