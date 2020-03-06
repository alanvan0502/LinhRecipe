package com.alanvan.linhrecipe

sealed class ViewState<T> {
    class Loading<T>(val fullScreen: Boolean) : ViewState<T>()
    class Success<T> : ViewState<T>()
    class Error<T>(val throwable: Throwable?) : ViewState<T>()
}