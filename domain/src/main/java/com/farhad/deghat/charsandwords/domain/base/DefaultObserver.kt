package com.farhad.deghat.charsandwords.domain.base

import io.reactivex.observers.DisposableObserver

abstract class DefaultObserver<T>: DisposableObserver<T>() {
    override fun onComplete() {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}