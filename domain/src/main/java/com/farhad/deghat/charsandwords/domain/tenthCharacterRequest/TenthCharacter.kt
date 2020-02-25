package com.farhad.deghat.charsandwords.domain.tenthCharacterRequest

import com.farhad.deghat.charsandwords.domain.base.UseCase
import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class TenthCharacter(
    subscribeOnScheduler: Scheduler,
    observeOnScheduler: Scheduler,
    private val webPageRepository: WebPageRepository
) : UseCase<ParamsTenthCharacter, Char>(subscribeOnScheduler, observeOnScheduler) {
    override fun buildObservable(params: ParamsTenthCharacter): Observable<Char> {
        return webPageRepository.getWebPage()
            .map { webPage -> webPage.content[params.charNumber - 1] }
    }
}