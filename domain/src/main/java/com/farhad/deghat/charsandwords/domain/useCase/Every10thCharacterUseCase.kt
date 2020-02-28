package com.farhad.deghat.charsandwords.domain.useCase

import com.farhad.deghat.charsandwords.domain.useCase.base.UseCase
import com.farhad.deghat.charsandwords.domain.model.WebPage
import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class Every10thCharacterUseCase(
    subscribeOnScheduler: Scheduler,
    observeOnScheduler: Scheduler,
    private val webPageRepository: WebPageRepository
): UseCase<String>(subscribeOnScheduler, observeOnScheduler) {
    override fun buildObservable(): Observable<String> {
        return webPageRepository.getWebPage().map { webPage -> filter10Ths(webPage) }
    }

    private fun filter10Ths(webPage: WebPage): String{
        return webPage.content.filterIndexed { index, _ ->  (index + 1) % 10 == 0}
    }
}