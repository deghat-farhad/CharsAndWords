package com.farhad.deghat.charsandwords.domain.base

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class UseCase<Params, T>(
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
){
    protected abstract fun buildObservable(params: Params): Observable<T>

    fun execute(observer: DefaultObserver<T>, params: Params): DefaultObserver<T> {
        return setSchedulers(buildObservable(params)).subscribeWith(observer)
    }

    private fun setSchedulers(observable: Observable<T>): Observable<T> {
        return observable
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
    }
}
