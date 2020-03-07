package com.alanvan.linhrecipe.data.injection

import android.content.Context
import com.alanvan.domain.base.GetAuthUseCase
import com.alanvan.domain.repository.LSAccountRepository
import com.alanvan.linhrecipe.LRApplication
import com.alanvan.linhrecipe.features.account.AccountManager
import com.alanvan.repository.repository.LSApiAccountRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

object Modules {

    enum class Tag {
        CONTEXT,
        THREAD_IO,
        THREAD_UI
    }

    private val production = Kodein.Module("Production") {
        bind<Kodein>() with singleton { kodein }

        bind<Scheduler>(Tag.THREAD_IO) with singleton { Schedulers.io() }
        bind<Scheduler>(Tag.THREAD_UI) with singleton { AndroidSchedulers.mainThread() }
        bind<Context>(Tag.CONTEXT) with provider { application }

        bind<LSAccountRepository>() with singleton { LSApiAccountRepository() }
        bind<GetAuthUseCase>() with provider {
            GetAuthUseCase(
                instance(),
                instance(Tag.THREAD_IO),
                instance(Tag.THREAD_UI)
            )
        }
    }

    val container = Kodein {
        import(production, allowOverride = true)
    }

    lateinit var application: LRApplication
}