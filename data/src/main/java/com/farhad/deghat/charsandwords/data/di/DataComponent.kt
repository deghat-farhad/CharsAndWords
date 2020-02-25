package com.farhad.deghat.charsandwords.data.di

import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import dagger.Component

@Component(modules = [DataModule::class])
interface DataComponent {
    fun getWebPageRepositoryImpl(): WebPageRepository
}