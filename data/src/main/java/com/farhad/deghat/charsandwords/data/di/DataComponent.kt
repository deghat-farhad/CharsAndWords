package com.farhad.deghat.charsandwords.data.di

import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataModule::class])
@Singleton
interface DataComponent {
    fun getWebPageRepositoryImpl(): WebPageRepository
}