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

    private fun executeTenthCharacterUseCase(){
        val tenthCharacterObserver = object : DefaultObserver<Char>(){
            override fun onNext(t: Char) {
                tenthCharacter.value = t
            }
        }

        tenthCharacterUseCase.execute(tenthCharacterObserver)
    }

    fun btnFireClicked() {
        executeTenthCharacterUseCase()
    }
}