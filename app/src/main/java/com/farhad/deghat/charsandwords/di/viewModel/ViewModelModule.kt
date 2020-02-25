package com.farhad.deghat.charsandwords.di.viewModel

import androidx.lifecycle.ViewModel
import com.farhad.deghat.charsandwords.MainActivityViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindGetGamesViewModel(viewModelSearchDoctor: MainActivityViewModel): ViewModel
}