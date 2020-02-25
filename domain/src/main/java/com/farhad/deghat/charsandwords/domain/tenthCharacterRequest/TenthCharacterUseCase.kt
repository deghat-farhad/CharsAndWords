package com.farhad.deghat.charsandwords.domain.tenthCharacterRequest

import com.farhad.deghat.charsandwords.domain.base.UseCaseNoParams
import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class TenthCharacterUseCase(
    subscribeOnScheduler: Scheduler,
    observeOnScheduler: Scheduler,
    private val webPageRepository: WebPageRepository
) : UseCaseNoParams<Char>(subscribeOnScheduler, observeOnScheduler) {
    override fun buildObservable(): Observable<Char> {
        return webPageRepository.getWebPage()
            .map { webPage -> webPage.content[9] } //I hate static values :|
    }
}