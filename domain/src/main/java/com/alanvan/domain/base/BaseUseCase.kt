package com.alanvan.domain.base

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

abstract class BaseUseCase<T, in Params> {

    private val onResultTasks: MutableList<(T) -> Unit> = mutableListOf()

    private val doBeforeTasks: MutableList<() -> Unit> = mutableListOf()
    private val doAfterTasks: MutableList<() -> Unit> = mutableListOf()
    private val onErrorTasks: MutableList<(Throwable) -> Unit> = mutableListOf()

    open operator fun invoke(params: Params? = null): Disposable {
        doBeforeTasks.forEach { it() }
        val disposable = execute(params)
            .subscribe({ result ->
                onResultTasks.forEach { tasks -> tasks(result) }
            }, { error ->
                onErrorTasks.forEach { task -> task(error) }
            })
        doAfterTasks.forEach { it() }
        return disposable
    }

    abstract fun execute(params: Params? = null): Observable<T>

    fun addToOnResultTasks(delegate: (T) -> Unit) {
        onResultTasks.add(delegate)
    }

    fun addToDoBeforeTasks(delegate: () -> Unit) {
        doBeforeTasks.add(delegate)
    }

    fun addToDoAfterTasks(delegate: () -> Unit) {
        doAfterTasks.add(delegate)
    }

    fun addToOnErrorTasks(delegate: (Throwable) -> Unit) {
        onErrorTasks.add(delegate)
    }
}