package com.alanvan.linhrecipe.utilities

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

fun View.increaseTouchableArea(extraSpace: Int = 50) {
    val parent = this.parent as View
    parent.post {
        tryLazy {
            val touchableArea = Rect()
            this.getHitRect(touchableArea)
            touchableArea.top -= extraSpace
            touchableArea.bottom += extraSpace
            touchableArea.left -= extraSpace
            touchableArea.right += extraSpace
            parent.touchDelegate = TouchDelegate(touchableArea, this)
        }
    }
}

fun ImageView.loadImage(
    imageUrl: String?, @DrawableRes placeHolder: Int,
    goneWhenNullUrl: Boolean = true,
    applyOptions: ((requestOptions: RequestOptions) -> Unit)? = null
): ImageView {
    if (context.activity != null && context.activity!!.isDestroyed) {
        return this
    }

    if (imageUrl.isNullOrEmpty()) {
        this.visibility = if (goneWhenNullUrl) View.GONE else View.INVISIBLE
    } else {
        this.visibility = View.VISIBLE
        val requestBuilder =
            Glide.with(this)
                .load(imageUrl)
                .placeholder(placeHolder)
                .transition(withCrossFade())
        applyOptions?.let {
            val requestOptions = RequestOptions()
            applyOptions(requestOptions)
            requestBuilder.apply(requestOptions)
        }
        requestBuilder.into(this)
    }

    return this
}

fun ImageView.loadImage(
    @RawRes @DrawableRes resId: Int?, goneWhenNullUrl: Boolean = true,
    applyOptions: ((requestOptions: RequestOptions) -> Unit)? = null
): ImageView {
    if (context.activity != null && context.activity!!.isDestroyed) {
        return this
    }

    if (resId == null) {
        this.visibility = if (goneWhenNullUrl) View.GONE else View.INVISIBLE
    } else {
        this.visibility = View.VISIBLE
        val requestBuilder = Glide.with(this).load(resId).transition(withCrossFade())
        applyOptions?.let {
            val requestOptions = RequestOptions()
            applyOptions(requestOptions)
            requestBuilder.apply(requestOptions)
        }
        requestBuilder.into(this)
    }
    return this
}

fun View.hideKeyboard() {
    tryLazy {
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
    }
}