package com.clp3z.xapotestapp.repository.network.client

import com.clp3z.xapotestapp.repository.network.schema.RepositoriesResponse
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Clelia López on 02/26/21
 */
interface RetrofitWebservice {

    // https://api.github.com/search/repositories?q=language:kotlin&sort=stars&order=desc&per_page=10&page=1

    @GET("search/repositories?q=language:kotlin&sort=forks&order=desc&per_page=10")
    fun getRepositories(@Query("page") page: Int): Call<RepositoriesResponse?>


    companion object {
        const val host = "https://api.github.com/"
        val loggingInterceptor = HttpLoggingInterceptor.Level.BASIC
    }
}