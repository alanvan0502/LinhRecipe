package com.alanvan.linhrecipe.features.recipe_details.view_item

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.alanvan.linhrecipe.R
import com.alanvan.linhrecipe.base.epoxy.BaseEpoxyModel

@EpoxyModelClass
abstract class RecipeInstructionEpoxyModel :
    EpoxyModelWithHolder<RecipeInstructionEpoxyModel.Holder>() {

    @EpoxyAttribute
    var content: String? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.apply {
            item.text = content
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.recipe_direction_item
    }

    class Holder : BaseEpoxyModel.Holder() {
        val item by bind<TextView>(R.id.item)
    }
}