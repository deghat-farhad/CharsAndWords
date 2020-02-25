package com.farhad.deghat.charsandwords.di.viewModel

import com.farhad.deghat.charsandwords.MainActivity
import com.farhad.deghat.charsandwords.di.Domain.DomainModule
import dagger.Component

@Component(modules = [ViewModelModule::class, DomainModule::class])
interface ViewModelComponent {
    fun injectActivity(mainActivity: MainActivity)
}