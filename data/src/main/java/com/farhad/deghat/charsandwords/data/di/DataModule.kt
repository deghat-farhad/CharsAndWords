package com.farhad.deghat.charsandwords.data.di

import com.farhad.deghat.charsandwords.data.repository.WebPageRepositoryImpl
import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DataModule {
    companion object {
        const val PAGE_URL = "https://google.com"
    }

    @Provides
    fun bindWebPageRepositoryImpl(webPageRepositoryImpl: WebPageRepositoryImpl): WebPageRepository =
        webPageRepositoryImpl

    @Provides
    @Named("PAGE_URL")
    fun providePageUrl() = PAGE_URL
}