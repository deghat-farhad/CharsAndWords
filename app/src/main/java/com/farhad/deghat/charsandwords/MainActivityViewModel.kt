package com.farhad.deghat.charsandwords

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farhad.deghat.charsandwords.domain.base.DefaultObserver
import com.farhad.deghat.charsandwords.domain.every10thCharacter.Every10thCharacterUseCase
import com.farhad.deghat.charsandwords.domain.tenthCharacterRequest.TenthCharacterUseCase
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val tenthCharacterUseCase: TenthCharacterUseCase,
    private val every10thCharacterUseCase: Every10thCharacterUseCase
): ViewModel() {

    val tenthCharacter: MutableLiveData<Char> by lazy { MutableLiveData<Char>() }
    val showTenthCharProgress: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    val every10thCharactersSequence: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val showEvery10thCharProgress: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

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
            }
        }

        showTenthCharProgress.value = true
        tenthCharacterUseCase.execute(tenthCharacterObserver)
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
            }
        }

        showEvery10thCharProgress.value = true
        every10thCharacterUseCase.execute(every10thCharacterObserver)
    }

    fun btnFireClicked() {
        executeTenthCharacterUseCase()
        executeEvery10thCharacterUseCase()
    }
}