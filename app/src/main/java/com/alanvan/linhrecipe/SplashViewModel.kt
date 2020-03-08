package com.alanvan.linhrecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanvan.linhrecipe.features.account.AccountManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SplashViewModel : ViewModel() {

    private val bag = CompositeDisposable()
    val tokenLiveData = MutableLiveData<String?>(null)

    fun initialize() {
        bag.add(AccountManager.getTokenSubject()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                tokenLiveData.value = it
            }
        )
    }

    fun cleanUp() {
        bag.clear()
    }

}