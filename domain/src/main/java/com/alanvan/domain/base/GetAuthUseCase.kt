package com.alanvan.domain.base

import com.alanvan.domain.model.Auth
import com.alanvan.domain.repository.LSAccountRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetAuthUseCase(
    private val repository: LSAccountRepository,
    private val executionScheduler: Scheduler,
    private val postExecutionScheduler: Scheduler
) : BaseUseCase<Auth, Nothing>() {

    override fun execute(params: Nothing?): Observable<Auth> {
        return repository.getAuthToken()
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
            .toObservable()
    }
}