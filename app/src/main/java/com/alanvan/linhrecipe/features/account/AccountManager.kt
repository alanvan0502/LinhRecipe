package com.alanvan.linhrecipe.features.account

import android.os.CountDownTimer
import com.alanvan.domain.features.account.GetAuthUseCase
import com.alanvan.linhrecipe.LRApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class AccountManager : KodeinAware {
    override val kodein: Kodein = LRApplication.kodein

    private val getAuthUseCase: GetAuthUseCase by instance()
    private val bag = CompositeDisposable()
    private val tokenSubject = PublishSubject.create<String>()

    private var token: String? = null
    private var expiryTime: Long? = null

    private var countDownTimer: CountDownTimer? = null

    companion object {
        const val FETCH_AUTH_BEFORE = 60_000
        private val instance by lazy { AccountManager() }

        fun initialize() {
            instance.fetchAuthToken()
        }

        fun cleanUp() {
            instance.cleanUp()
        }

        fun getAuthToken() = instance.token
        fun getTokenSubject() = instance.tokenSubject
        fun forceFetchAuthToken() = instance.fetchAuthToken()
    }

    private fun fetchAuthToken() {
        bag.add(getAuthUseCase.execute().map {
            countDownTimer = object : CountDownTimer(it.expiresIn, 1000) {

                override fun onFinish() {
                    // do nothing
                }

                override fun onTick(millisUntilFinished: Long) {
                    if (millisUntilFinished < FETCH_AUTH_BEFORE) {
                        fetchAuthToken()
                        countDownTimer?.cancel()
                    }
                }
            }.start()
            return@map it
        }.subscribe({ auth ->
            token = auth.accessToken
            expiryTime = auth.expiresIn
            tokenSubject.onNext(token ?: "")
        }, {
            tokenSubject.onNext("")
        }))
    }

    private fun cleanUp() {
        bag.clear()
    }
}