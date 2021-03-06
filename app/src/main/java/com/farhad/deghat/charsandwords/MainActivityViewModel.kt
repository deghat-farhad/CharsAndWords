package com.farhad.deghat.charsandwords

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farhad.deghat.charsandwords.domain.useCase.base.DefaultObserver
import com.farhad.deghat.charsandwords.domain.useCase.Every10thCharacterUseCase
import com.farhad.deghat.charsandwords.domain.useCase.TenthCharacterUseCase
import com.farhad.deghat.charsandwords.domain.useCase.WordCounterUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val tenthCharacterUseCase: TenthCharacterUseCase,
    private val every10thCharacterUseCase: Every10thCharacterUseCase,
    private val wordCounterUseCase: WordCounterUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val tenthCharacter: MutableLiveData<Char> by lazy { MutableLiveData<Char>() }
    val showTenthCharProgress: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    val every10thCharactersSequence: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val showEvery10thCharProgress: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    val wordCounterOutput: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val showWordCounterProgress: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    val errorText: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private fun executeTenthCharacterUseCase(){
        val tenthCharacterObserver = object : DefaultObserver<Char>(){
            override fun onNext(t: Char) {
                super.onNext(t)
                tenthCharacter.value = t
            }

            override fun onComplete() {
                super.onComplete()
                showTenthCharProgress.value = false
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                showTenthCharProgress.value = false
                errorText.value = e.localizedMessage
            }
        }

        showTenthCharProgress.value = true
        tenthCharacterUseCase.execute(tenthCharacterObserver)?.addTo(compositeDisposable)
    }

    private fun executeEvery10thCharacterUseCase(){
        val every10thCharacterObserver = object : DefaultObserver<String>(){
            override fun onNext(t: String) {
                super.onNext(t)
                every10thCharactersSequence.value = t
            }

            override fun onComplete() {
                super.onComplete()
                showEvery10thCharProgress.value = false
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                showEvery10thCharProgress.value = false
                errorText.value = e.localizedMessage
            }
        }

        showEvery10thCharProgress.value = true
        every10thCharacterUseCase.execute(every10thCharacterObserver)?.addTo(compositeDisposable)
    }

    private fun executeWordCounterUseCase(){
        val wordCounterObserver = object : DefaultObserver<String>(){
            override fun onNext(t: String) {
                super.onNext(t)
                wordCounterOutput.value = t
            }

            override fun onComplete() {
                super.onComplete()
                showWordCounterProgress.value = false
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                showWordCounterProgress.value = false
                errorText.value = e.localizedMessage
            }
        }

        showWordCounterProgress.value = true
        wordCounterUseCase.execute(wordCounterObserver)?.addTo(compositeDisposable)
    }

    fun btnFireClicked() {
        executeTenthCharacterUseCase()
        executeEvery10thCharacterUseCase()
        executeWordCounterUseCase()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}