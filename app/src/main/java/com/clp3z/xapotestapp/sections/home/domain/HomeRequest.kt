package com.clp3z.xapotestapp.sections.home.domain

import com.clp3z.xapotestapp.repository.network.client.RestServerAPI
import com.clp3z.xapotestapp.repository.network.schema.RepositoryResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Clelia López on 02/26/21
 */
class HomeRequest(private val webservice: RestServerAPI) {

    sealed class Result {
        class Success(val repositories: List<RepositoryResponse>): Result()
        object Failure: Result()
    }

    suspend fun getRepositories(page: Int): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = webservice.getRepositories(page)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.items)
                } else {
                    return@withContext Result.Failure
                }
            } catch (exception: Throwable) {
                if (exception !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw exception
                }
            }
        }
    }
}
