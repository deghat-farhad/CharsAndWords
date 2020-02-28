package com.farhad.deghat.charsandwords.domain.wordCounter

import com.farhad.deghat.charsandwords.domain.base.UseCaseNoParams
import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.lang.StringBuilder
import java.util.*

class WordCounterUseCase(
    subscribeOnScheduler: Scheduler,
    observeOnScheduler: Scheduler,
    private val webPageRepository: WebPageRepository
): UseCaseNoParams<String>(subscribeOnScheduler, observeOnScheduler) {
    override fun buildObservable(): Observable<String> {
        return webPageRepository.getWebPage().map { mapToString(countWords(it.content))}
    }


    private fun countWords(input: String): Map<String, Int> {
        val words = input.split("\\s+".toRegex())
        return words.groupingBy{word -> word.toLowerCase(Locale.US)}.eachCount()
    }

    private fun mapToString(wordsMap: Map<String, Int>): String {
        val outputStringBuilder = StringBuilder()

        for((word, count) in wordsMap){
            outputStringBuilder.append("$word --> $count\n")
        }

        outputStringBuilder.setLength(outputStringBuilder.length - 1) //remove last "next line"
        return outputStringBuilder.toString()
    }
}