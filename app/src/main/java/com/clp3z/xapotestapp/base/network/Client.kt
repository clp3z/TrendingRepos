package com.clp3z.xapotestapp.base.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Clelia López on 10/10/20
 */
object Client {

    /**
     * Properties
     */
    private const val host = "https://api.github.com/"
    private var restAPIServer: RestAPIServer? = null

    private val RETROFIT_LOG_LEVEL = HttpLoggingInterceptor.Level.BASIC


    private fun getInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = RETROFIT_LOG_LEVEL
        return loggingInterceptor
    }

    private fun getOkHttpBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(getInterceptor())
        return builder
    }

    private fun getDefaultRetrofitBuilder(): Retrofit =
        Retrofit.Builder()
        .baseUrl(host)
        .client(getOkHttpBuilder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    internal fun getRestAPIService(): RestAPIServer? {
        if (restAPIServer == null)
            restAPIServer = getDefaultRetrofitBuilder().create(RestAPIServer::class.java)
        return restAPIServer
    }
}