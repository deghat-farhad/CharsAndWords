package com.farhad.deghat.charsandwords

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farhad.deghat.charsandwords.domain.base.DefaultObserver
import com.farhad.deghat.charsandwords.domain.tenthCharacterRequest.TenthCharacterUseCase
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val tenthCharacterUseCase: TenthCharacterUseCase
): ViewModel() {

    val tenthCharacter: MutableLiveData<Char> by lazy { MutableLiveData<Char>() }
    val showTenthCharProgress: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

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

    fun btnFireClicked() {
        executeTenthCharacterUseCase()
    }
}