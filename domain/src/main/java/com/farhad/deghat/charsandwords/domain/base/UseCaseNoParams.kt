package com.farhad.deghat.charsandwords.domain.base

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class UseCaseNoParams<T>(
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
){
    abstract fun buildObservable(): Observable<T>

    fun execute(observer: DefaultObserver<T>): DefaultObserver<T> {
        val observable = setSchedulers(buildObservable())
        return observable.subscribeWith(observer)
    }

    private fun setSchedulers(observable: Observable<T>): Observable<T> {
        return observable
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
    }
}
