package com.alanvan.linhrecipe.features.recipe_details.view_item

import android.content.Context
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
abstract class RecipeHeaderEpoxyModel : EpoxyModelWithHolder<RecipeHeaderEpoxyModel.Holder>(), KodeinAware {
    override val kodein: Kodein = LRApplication.kodein

    private val context: Context by instance()

    @EpoxyAttribute
    var recipeImage: String? = null

    @EpoxyAttribute
    var recipeDescription: String? = null

    @EpoxyAttribute
    var rating: String? = null

    @EpoxyAttribute
    var cookingTime: String? = null

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.apply {
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

            recipeDescriptionTextView.text = recipeDescription
            val ratingText = if (rating == "NaN" || rating.isNullOrEmpty()) {
                "NA"
            } else {
                rating
            }
            ratingTextView.text = context.getString(R.string.rating, ratingText)
            cookingTimeTextView.text = context.getString(R.string.cooking_time, cookingTime)
        }
    }

    override fun getDefaultLayout(): Int {
        return R.layout.recipe_details_header
    }

    class Holder : BaseEpoxyModel.Holder() {
        val recipeImageView: ImageView by bind(R.id.recipeImage)
        val recipeDescriptionTextView: TextView by bind(R.id.recipeDescription)
        val ratingTextView: TextView by bind(R.id.rating)
        val cookingTimeTextView: TextView by bind(R.id.cooking_time)
    }
}