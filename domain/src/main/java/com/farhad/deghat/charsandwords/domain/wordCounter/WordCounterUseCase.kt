package com.farhad.deghat.charsandwords.domain.wordCounter

import com.farhad.deghat.charsandwords.domain.base.UseCaseNoParams
import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.*

class WordCounterUseCase(
    subscribeOnScheduler: Scheduler,
    observeOnScheduler: Scheduler,
    private val webPageRepository: WebPageRepository
): UseCaseNoParams<String>(subscribeOnScheduler, observeOnScheduler) {
    override fun buildObservable(): Observable<String> {
        return webPageRepository.getWebPage().map { (countWords(it.content)).toString().replace(", ", "\n")}
    }


    private fun countWords(input: String): Map<String, Int> {
        val words = input.split("\\s+".toRegex())
        return words.groupingBy{word -> word.toLowerCase(Locale.US)}.eachCount()
    }
}