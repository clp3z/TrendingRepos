package com.clp3z.xapotestapp.repository.network.client

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Clelia López on 02/26/21
 */
object RetrofitClient {

    val webservice: RetrofitWebservice by lazy {
        getRetrofit(getOkHttpClient(getInterceptor())).create(RetrofitWebservice::class.java)
    }

    private fun getInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = RetrofitWebservice.loggingInterceptor
        return loggingInterceptor
    }

    private fun getMoshiSerializer(): Moshi =
        Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun getOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
        .baseUrl(RetrofitWebservice.host)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(getMoshiSerializer()))
        .build()
}