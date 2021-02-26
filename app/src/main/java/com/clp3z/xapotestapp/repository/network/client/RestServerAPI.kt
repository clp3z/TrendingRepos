package com.clp3z.xapotestapp.base.network

import com.clp3z.xapotestapp.repository.network.schema.RepositoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Clelia López on 10/10/20
 */
interface RestAPIServer {

    // https://api.github.com/search/repositories?q=language:kotlin&sort=stars&order=desc&per_page=10&page=1

    @GET("search/repositories?q=language:kotlin&sort=forks&order=desc&per_page=10")
    fun getRepositories(@Query("page") page: Int): Call<RepositoriesResponse>
}