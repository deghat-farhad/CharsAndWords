package com.farhad.deghat.charsandwords.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val provider = providers[modelClass]
            ?: providers.asIterable()
                .firstOrNull{modelClass.isAssignableFrom(it.key)}
                ?.value
            ?: throw IllegalArgumentException("Unknown model class: $modelClass")
        try {
            return provider.get() as T
        }catch (e: Exception){
            throw RuntimeException(e)
        }
    }
}