package com.alanvan.domain.base

abstract class DataMapper<T, R> {

    abstract fun map(source: T): R
    open fun map(source: List<T>) = source.map { map(it) }

}