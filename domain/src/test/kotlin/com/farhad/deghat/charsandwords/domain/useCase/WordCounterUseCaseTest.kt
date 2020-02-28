package com.farhad.deghat.charsandwords.domain.useCase

import com.farhad.deghat.charsandwords.domain.model.WebPage
import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WordCounterUseCaseTest {

    @Mock
    lateinit var subscribeOnScheduler: Scheduler
    @Mock
    lateinit var observeOnScheduler: Scheduler
    @Mock
    lateinit var webPageRepository: WebPageRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testCountWords() {
        val webPageContents = "test Boo Test boo test BOO tEsT"
        val webPageObservable = Observable.just(WebPage(webPageContents))
        Mockito.`when`(webPageRepository.getWebPage()).thenReturn(webPageObservable)

        val wordCounterUseCase =
            WordCounterUseCase(
                subscribeOnScheduler,
                observeOnScheduler,
                webPageRepository
            )

        wordCounterUseCase.buildObservable().test().assertValue {
            it == "test --> 4\nboo --> 3"
        }
    }
}