package com.alanvan.domain.base

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

abstract class BaseUseCase<T, in Params> {

    private val onResultTasks = TypeTasks<T>()

    private val doBeforeTasks = Tasks()
    private val doAfterTasks = Tasks()
    private val onErrorTasks = TypeTasks<Throwable>()

    open operator fun invoke(params: Params? = null): Disposable {
        doBeforeTasks.execute()
        val disposable = execute(params)
            .subscribe({ result ->
                onResultTasks.execute(result)
            }, { error ->
                onErrorTasks.execute(error)
            })
        doAfterTasks.execute()
        return disposable
    }

    abstract fun execute(params: Params? = null): Observable<T>

    fun addToOnResultTasks(delegate: (T) -> Unit) {
        onResultTasks.addTask(delegate)
    }

    fun addToDoBeforeTasks(delegate: () -> Unit) {
        doBeforeTasks.addTask(delegate)
    }

    fun addToDoAfterTasks(delegate: () -> Unit) {
        doAfterTasks.addTask(delegate)
    }

    fun addToOnErrorTasks(delegate: (Throwable) -> Unit) {
        onErrorTasks.addTask(delegate)
    }

    private class TypeTasks<T> {
        private val taskList = mutableListOf<(T) -> Unit>()

        fun addTask(task: (T) -> Unit) {
            taskList.add(task)
        }

        fun execute(result: T) {
            taskList.forEach { task ->
                task.invoke(result)
            }
            taskList.clear()
        }
    }

    private class Tasks {
        private val taskList = mutableListOf<() -> Unit>()

        fun addTask(task: () -> Unit) {
            taskList.add(task)
        }

        fun execute() {
            taskList.forEach { task ->
                task.invoke()
            }
            taskList.clear()
        }
    }
}