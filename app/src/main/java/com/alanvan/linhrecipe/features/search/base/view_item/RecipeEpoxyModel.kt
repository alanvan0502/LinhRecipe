package com.alanvan.linhrecipe.features.search.base.view_item

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.alanvan.linhrecipe.LRApplication
import com.alanvan.linhrecipe.R
import com.alanvan.linhrecipe.base.epoxy.BaseEpoxyModel
import com.alanvan.linhrecipe.utilities.loadImage
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

@EpoxyModelClass
abstract class RecipeEpoxyModel : EpoxyModelWithHolder<RecipeEpoxyModel.Holder>(), KodeinAware {
    override val kodein: Kodein = LRApplication.kodein

    private val context: Context by instance()

    @EpoxyAttribute
    var recipeName: String? = null

    @EpoxyAttribute
    var recipeCalories: String? = null

    @EpoxyAttribute
    var recipeCarbohydrate: String? = null

    @EpoxyAttribute
    var recipeFat: String? = null

    @EpoxyAttribute
    var recipeProtein: String? = null

    @EpoxyAttribute
    var recipeImage: String? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash, EpoxyAttribute.Option.DoNotUseInToString)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.apply {
            recipeNameTextView.text = recipeName

            if (!recipeImage.isNullOrEmpty()) {
                recipeImageView.loadImage(
                    recipeImage,
                    R.drawable.recipe_placeholder,
                    goneWhenNullUrl = false
                ) {
                    it.centerCrop()
                }
            } else {
                recipeImageView.loadImage(
                    R.drawable.recipe_placeholder,
                    goneWhenNullUrl = false
                ) {
                    it.centerCrop()
                }
            }
            recipeCaloriesTextView.text = context.getString(
                R.string.calories_and_carbo,
                recipeCalories,
                recipeCarbohydrate)

            recipeFatTextView.text = context.getString(
                R.string.fat_and_protein,
                recipeFat,
                recipeProtein
            )

            itemView.setOnClickListener(clickListener)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.recipe
    }

    class Holder : BaseEpoxyModel.Holder() {
        val recipeNameTextView by bind<TextView>(R.id.recipeName)
        val recipeImageView by bind<ImageView>(R.id.recipeImage)
        val recipeCaloriesTextView by bind<TextView>(R.id.recipeCalories)
        val recipeFatTextView by bind<TextView>(R.id.recipeFat)
    }
}