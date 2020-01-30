package com.arcane.coldstorageexamples.remotecall

import com.arcane.coldstorageannotation.CacheKey
import com.arcane.coldstorageannotation.Freeze
import java.net.URL

@Freeze(generatedClassName = "MyBeautifulCacheLayer")
class MakeRemoteCallWithFreeze {



    fun makeRemoteCallToServiceA(value: String): String {
        val url = "https://httpbin.org/get?param1=$value"
        val textResponse = URL(url).readText()
        return textResponse
    }


    /**
     * Here I am marking the parameters that will together form the cache key
     * with @CacheKey
     */
    fun makeRemoteCallToServiceB(
        @CacheKey parameter1: String,
        @CacheKey parameter2: String,
        parameter3: String
    ): String {
        val url = "https://httpbin.org/get?param1=$parameter1&param2=$parameter2&param3=$parameter3"
        val textResponse = URL(url).readText()
        return textResponse
    }
}