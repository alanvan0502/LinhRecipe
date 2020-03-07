package com.alanvan.linhrecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.domain.base.GetAuthUseCase
import com.alanvan.domain.model.Auth
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainViewModel : ViewModel(), KodeinAware {

    override val kodein: Kodein = LRApplication.kodein
    private val getAuthUseCase: GetAuthUseCase by instance()

    private val viewStateLiveData = MutableLiveData<ViewState<Auth>>()
    private val authLiveData = MutableLiveData<Auth>()

    fun viewState() = viewStateLiveData
    fun auth() = authLiveData

    fun getAuthToken() {
        getAuthUseCase.loadWithLiveData(viewStateLiveData, authLiveData).invoke()
    }

    fun getAuthTokenObservable() {
        getAuthUseCase.execute()
    }

}