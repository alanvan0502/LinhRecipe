package com.alanvan.linhrecipe.features.home.view_item

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.alanvan.domain.model.home.RecipeType
import com.alanvan.linhrecipe.LRApplication
import com.alanvan.linhrecipe.R
import com.alanvan.linhrecipe.base.epoxy.BaseEpoxyModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

@EpoxyModelClass
abstract class RecipeTypeEpoxyModel
    : EpoxyModelWithHolder<RecipeTypeEpoxyModel.Holder>(), KodeinAware {
    override val kodein: Kodein = LRApplication.kodein

    private val context: Context by instance()

    @EpoxyAttribute
    var recipeType: RecipeType? = null

    @EpoxyAttribute
    var spanCount: Int? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClickListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.apply {
            val background = when (recipeType) {
                RecipeType.APPETIZER -> ContextCompat.getDrawable(context, R.drawable.appetizer)
                RecipeType.SOUP -> ContextCompat.getDrawable(context, R.drawable.soup)
                RecipeType.MAIN_DISH -> ContextCompat.getDrawable(context, R.drawable.main_dish)
                RecipeType.SIDE_DISH -> ContextCompat.getDrawable(context, R.drawable.side_dish)
                RecipeType.BAKED -> ContextCompat.getDrawable(context, R.drawable.baked)
                RecipeType.SALAD -> ContextCompat.getDrawable(context, R.drawable.salad)
                RecipeType.SAUCE -> ContextCompat.getDrawable(context, R.drawable.sauce)
                RecipeType.DESSERT -> ContextCompat.getDrawable(context, R.drawable.dessert)
                RecipeType.SNACK -> ContextCompat.getDrawable(context, R.drawable.snack)
                RecipeType.BEVERAGE -> ContextCompat.getDrawable(context, R.drawable.beverage)
                RecipeType.OTHER -> ContextCompat.getDrawable(context, R.drawable.other)
                RecipeType.BREAKFAST -> ContextCompat.getDrawable(context, R.drawable.breakfast)
                RecipeType.LUNCH -> ContextCompat.getDrawable(context, R.drawable.lunch)
                else -> null
            }
            background?.let { image.background = it }
            if (spanCount != null && spanCount!! > 0) {
                val width = context.resources.displayMetrics.widthPixels / spanCount!!
                val height = context.resources.displayMetrics.widthPixels / spanCount!!
                val layoutParams = ConstraintLayout.LayoutParams(width, height)
                image.layoutParams = layoutParams
            }
            description.text = recipeType?.value

            itemView.setOnClickListener(onClickListener)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.recipe_type
    }

    class Holder : BaseEpoxyModel.Holder() {
        val image by bind<ImageView>(R.id.image)
        val description by bind<TextView>(R.id.description)
    }
}