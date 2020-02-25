package com.farhad.deghat.charsandwords.data.remote

import com.farhad.deghat.charsandwords.data.model.WebPage
import io.reactivex.Observable
import java.lang.Exception
import java.net.URL

class WebsiteContentGrabber {
    fun grabContentOf(url: String): Observable<WebPage>{
        return Observable.create{emitter ->
            try{
                val connection = URL(url)
                val webPage = WebPage(connection.readText())
                emitter.onNext(webPage)
                emitter.onComplete()
            }catch (e: Exception){
                emitter.onError(e)
            }
        }
    }
}