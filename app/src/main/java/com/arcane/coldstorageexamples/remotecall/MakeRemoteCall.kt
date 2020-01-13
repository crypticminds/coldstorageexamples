package com.arcane.coldstorageexamples.remotecall

import com.arcane.coldstorageannotation.Refrigerate
import java.net.URL

class MakeRemoteCall {

    /**
     * A method that makes a call to the "https://httpbin.org/get"
     * endpoint. This endpoint simply returns the arguments that were passed to it.
     * For the caching logic the parameter passed to the endpoint will act as
     * the key of the cache.
     *
     * Since there is only one parameter we don't need to specify the the keys
     * field in the annotation.
     */
    @Refrigerate(operation = "CallToServiceA")
    fun makeRemoteCallToServiceA(value: String): String {
        val url = "https://httpbin.org/get?param1=$value"
        val textResponse = URL(url).readText()
        return textResponse
    }


    /**
     * We will use the same endpoint but this time we will pass 3 parameters.
     * However , out cache key should only be the first 2.
     * We can configure the "keys" field in the annotation by specifying
     * the variable names that should act as the key of the cache.
     * In this case "parameter1" and "parameter2"
     */
    @Refrigerate(
        operation = "CallToServiceB", timeToLive = 10000,
        keys = ["parameter1", "parameter2"]
    )
    fun makeRemoteCallToServiceB(parameter1: String, parameter2: String, parameter3: String): String {
        val url = "https://httpbin.org/get?param1=$parameter1&param2=$parameter2&param3=$parameter3"
        val textResponse = URL(url).readText()
        return textResponse
    }
}