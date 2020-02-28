package com.farhad.deghat.charsandwords.domain.useCase.base

import io.reactivex.Observable
import io.reactivex.Scheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UseCaseTest{
    @Mock
    lateinit var subscribeOnScheduler : Scheduler
    @Mock
    lateinit var observeOnScheduler : Scheduler
    @Mock
    lateinit var observer: DefaultObserver<Unit>
    @Mock
    lateinit var observable : Observable<Unit>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(observable.subscribeOn(any())).thenReturn(observable)
        Mockito.`when`(observable.observeOn(any())).thenReturn(observable)
    }

    @Test
    fun checkSchedulers() {
        val useCase = object : UseCase<Unit>(subscribeOnScheduler, observeOnScheduler) {
            override fun buildObservable(): Observable<Unit> = observable
        }

        useCase.execute(observer)

        Mockito.verify(observable).subscribeOn(subscribeOnScheduler)
        Mockito.verify(observable).observeOn(observeOnScheduler)
    }

    @Test
    fun checkSubscriber() {
        val useCase = object : UseCase<Unit>(subscribeOnScheduler, observeOnScheduler) {
            override fun buildObservable() = observable
        }

        useCase.execute(observer)
        Mockito.verify(observable).subscribeWith(observer)
    }

    @Test
    fun checkOutPut() {
        val useCase = object : UseCase<Unit>(subscribeOnScheduler, observeOnScheduler) {
            override fun buildObservable() = observable
        }

        val disposableObserver = useCase.execute(observer)
        assertEquals(disposableObserver, observable.subscribeWith(observer))
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T
}