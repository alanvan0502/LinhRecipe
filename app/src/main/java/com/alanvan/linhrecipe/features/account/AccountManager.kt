package com.alanvan.linhrecipe.features.account

import android.os.CountDownTimer
import com.alanvan.domain.base.GetAuthUseCase
import com.alanvan.linhrecipe.LRApplication
import io.reactivex.disposables.CompositeDisposable
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class AccountManager : KodeinAware {
    override val kodein: Kodein = LRApplication.kodein

    private val getAuthUseCase: GetAuthUseCase by instance()
    private val bag = CompositeDisposable()

    private var token: String? = null
    private var expiryTime: Long? = null

    private var countDownTimer: CountDownTimer? = null

    companion object {
        private val instance by lazy { AccountManager() }

        fun initialize() {
            instance.fetchAuthToken()
        }

        fun cleanUp() {
            instance.cleanUp()
        }

        fun getAuthToken() = instance.token
        fun forceFetchAuthToken() = instance.fetchAuthToken()
    }

    private fun fetchAuthToken() {
        bag.add(getAuthUseCase.execute().map {
            countDownTimer = object : CountDownTimer(it.expiresIn, 1000) {

                override fun onFinish() {
                    // do nothing
                }

                override fun onTick(millisUntilFinished: Long) {
                    if (millisUntilFinished < 60_0000) {
                        fetchAuthToken()
                        countDownTimer?.cancel()
                    }
                }
            }.start()
            return@map it
        }.subscribe({ auth ->
            token = auth.accessToken
            expiryTime = auth.expiresIn
        }, {

        }))
    }

    private fun cleanUp() {
        bag.clear()
    }
}