package com.farhad.deghat.charsandwords.data.remote

import com.farhad.deghat.charsandwords.data.model.WebPageEntity
import io.reactivex.Observable
import java.lang.Exception
import java.net.URL
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class WebsiteContentGrabber @Inject constructor(@Named("PAGE_URL") private val pageUrl: String) {
    fun grabContentOf(): Observable<WebPageEntity>{
        return Observable.create{emitter ->
            try{
                val connection = URL(pageUrl)
                val webPage = WebPageEntity(connection.readText())
                emitter.onNext(webPage)
                emitter.onComplete()
            }catch (e: Exception){
                emitter.onError(e)
            }
        }
    }
}