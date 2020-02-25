package com.farhad.deghat.charsandwords.data.mapper

import com.farhad.deghat.charsandwords.data.model.WebPageEntity
import com.farhad.deghat.charsandwords.domain.model.WebPage
import javax.inject.Inject

class WebPageEntityMapper @Inject constructor(){
    fun mapToDomain(webPageEntity: WebPageEntity) = WebPage(webPageEntity.content)
}