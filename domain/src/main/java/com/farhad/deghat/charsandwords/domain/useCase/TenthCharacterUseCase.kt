package com.farhad.deghat.charsandwords.domain.useCase

import com.farhad.deghat.charsandwords.domain.useCase.base.UseCase
import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class TenthCharacterUseCase(
    subscribeOnScheduler: Scheduler,
    observeOnScheduler: Scheduler,
    private val webPageRepository: WebPageRepository
) : UseCase<Char>(subscribeOnScheduler, observeOnScheduler) {

    override fun buildObservable(): Observable<Char> {
        return webPageRepository.getWebPage()
            .map { webPage -> webPage.content[9] } //I hate static values :|
    }
}