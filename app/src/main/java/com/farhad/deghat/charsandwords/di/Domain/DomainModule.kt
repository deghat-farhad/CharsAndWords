package com.farhad.deghat.charsandwords.di.Domain

import com.farhad.deghat.charsandwords.data.di.DaggerDataComponent
import com.farhad.deghat.charsandwords.domain.every10thCharacter.Every10thCharacterUseCase
import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import com.farhad.deghat.charsandwords.domain.tenthCharacterRequest.TenthCharacterUseCase
import com.farhad.deghat.charsandwords.domain.wordCounter.WordCounterUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class DomainModule {
    companion object {
        const val OBSERVE_ON_NAME = "observeOnScheduler"
        const val SUBSCRIBE_ON_NAME = "subscribedOnScheduler"
    }

    @Provides
    fun tenthCharacterProvider(
        @Named(SUBSCRIBE_ON_NAME)
        subscribeOnScheduler: Scheduler,
        @Named(OBSERVE_ON_NAME)
        observeOnScheduler: Scheduler,
        webPageRepository: WebPageRepository
        ) = TenthCharacterUseCase(subscribeOnScheduler, observeOnScheduler, webPageRepository)

    @Provides
    fun every10thCharacterUseCaseProvider(
        @Named(SUBSCRIBE_ON_NAME)
        subscribeOnScheduler: Scheduler,
        @Named(OBSERVE_ON_NAME)
        observeOnScheduler: Scheduler,
        webPageRepository: WebPageRepository
    ) = Every10thCharacterUseCase(subscribeOnScheduler, observeOnScheduler, webPageRepository)

    @Provides
    fun wordCounterUseCaseProvider(
        @Named(SUBSCRIBE_ON_NAME)
        subscribeOnScheduler: Scheduler,
        @Named(OBSERVE_ON_NAME)
        observeOnScheduler: Scheduler,
        webPageRepository: WebPageRepository
    ) = WordCounterUseCase(subscribeOnScheduler, observeOnScheduler, webPageRepository)

    @Provides
    fun webPageRepositoryProvider(): WebPageRepository =
        DaggerDataComponent.create().getWebPageRepositoryImpl()

    @Provides
    @Named(SUBSCRIBE_ON_NAME)
    fun subscribeOnSchedulerProvider(): Scheduler = Schedulers.io()

    @Provides
    @Named(OBSERVE_ON_NAME)
    fun observeOnSchedulerProvider(): Scheduler = AndroidSchedulers.mainThread()
}