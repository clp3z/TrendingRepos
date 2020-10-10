package com.clp3z.bottlerocket.base.network

import com.clp3z.xapotestapp.repository.network.RepositoriesResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Clelia López on 10/10/20
 */
interface RestAPIServer {

    @GET("search/repositories?q=language:kotlin&sort=stars&order=desc&per_page=10")
    fun getRepositories(): Call<RepositoriesResponse>
}