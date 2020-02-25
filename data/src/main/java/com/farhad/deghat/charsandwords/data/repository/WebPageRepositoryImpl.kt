package com.farhad.deghat.charsandwords.data.repository

import com.farhad.deghat.charsandwords.data.mapper.WebPageEntityMapper
import com.farhad.deghat.charsandwords.data.remote.WebsiteContentGrabber
import com.farhad.deghat.charsandwords.domain.model.WebPage
import com.farhad.deghat.charsandwords.domain.repository.WebPageRepository
import io.reactivex.Observable
import javax.inject.Inject

class WebPageRepositoryImpl @Inject constructor(
    private val websiteContentGrabber: WebsiteContentGrabber,
    private val webPageEntityMapper: WebPageEntityMapper
) : WebPageRepository {
    override fun getWebPage(): Observable<WebPage> {
        return websiteContentGrabber.grabContentOf()
            .map { webPageEntity -> webPageEntityMapper.mapToDomain(webPageEntity) }
    }
}