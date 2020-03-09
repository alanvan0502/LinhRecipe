package com.alanvan.linhrecipe.features.search.view_item

import android.content.Context
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.alanvan.linhrecipe.LRApplication
import com.alanvan.linhrecipe.R
import com.alanvan.linhrecipe.base.epoxy.BaseEpoxyModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

@EpoxyModelClass
abstract class RecipeEpoxyModel : EpoxyModelWithHolder<RecipeEpoxyModel.Holder>(), KodeinAware {
    override val kodein: Kodein = LRApplication.kodein

    private val context: Context by instance()

    @EpoxyAttribute
    var recipeName: String? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.apply {
            recipeNameTextView.text = recipeName
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.recipe
    }

    class Holder : BaseEpoxyModel.Holder() {
        val recipeNameTextView by bind<TextView>(R.id.name)
    }
}