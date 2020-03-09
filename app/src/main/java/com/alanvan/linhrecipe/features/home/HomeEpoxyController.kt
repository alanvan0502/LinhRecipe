package com.alanvan.linhrecipe.features.home

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import com.alanvan.domain.model.home.RecipeType
import com.alanvan.linhrecipe.features.home.view_item.RecipeTypeEpoxyModel_
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class HomeEpoxyController(
    override val kodein: Kodein,
    private val actionListener: HomeEpoxyControllerActionListener
) :
    Typed2EpoxyController<Int, List<RecipeType>>(), KodeinAware {

    override fun buildModels(data1: Int?, data2: List<RecipeType>?) {
        data2?.forEachIndexed { index, recipeType ->
            RecipeTypeEpoxyModel_()
                .id(index)
                .recipeType(recipeType)
                .onClickListener(View.OnClickListener {
                    actionListener.onRecipeTypeClick(recipeType.value)
                })
                .spanCount(data1)
                .addTo(this)
        }
    }

    interface HomeEpoxyControllerActionListener {
        fun onRecipeTypeClick(recipeType: String)
    }
}