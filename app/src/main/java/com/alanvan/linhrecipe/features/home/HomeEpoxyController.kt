package com.alanvan.linhrecipe.features.home

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import com.alanvan.domain.model.home.RecipeType
import com.alanvan.linhrecipe.features.home.view_item.RecipeTypeEpoxyModel_
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class HomeEpoxyController(
    override val kodein: Kodein,
    private val actionListener: HomeEpoxyControllerActionListener
) : TypedEpoxyController<List<RecipeType>>(), KodeinAware {

    override fun buildModels(data: List<RecipeType>?) {
        data?.forEachIndexed { index, recipeType ->
            RecipeTypeEpoxyModel_()
                .id(index)
                .recipeType(recipeType)
                .onClickListener(View.OnClickListener {
                    actionListener.onRecipeTypeClick(recipeType.value)
                })
                .spanCount(spanCount)
                .addTo(this)
        }
    }

    interface HomeEpoxyControllerActionListener {
        fun onRecipeTypeClick(recipeType: String)
    }
}