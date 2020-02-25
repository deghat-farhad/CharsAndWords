package com.farhad.deghat.charsandwords.domain.repository

import com.farhad.deghat.charsandwords.domain.model.WebPage
import io.reactivex.Observable

interface WebPageRepository {
    fun getWebPage(): Observable<WebPage>
}