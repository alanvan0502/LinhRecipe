package com.alanvan.linhrecipe.utilities

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.util.Log

fun Activity.startActivitySafe(intent: Intent, requestCode: Int? = null) {
    try {
        if (requestCode != null) {
            startActivityForResult(intent, requestCode)
        } else {
            startActivity(intent)
        }
    } catch (e: Exception) {
        Log.e(javaClass.simpleName, e.message, e)
    }
}

fun tryLazy(safeCall: () -> Unit) {
    try {
        safeCall()
    } catch (e: Exception) {
    }
}

val Context.activity: Activity?
    get() =
        this as? Activity ?: if (this is ContextWrapper) {
            this.baseContext.activity
        } else {
            null
        }