package com.clp3z.xapotestapp.screen.home.domain

import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.generic.GenericNetworkRequest
import com.clp3z.xapotestapp.repository.network.client.RetrofitWebservice
import com.clp3z.xapotestapp.repository.network.schema.RepositoriesResponse
import com.clp3z.xapotestapp.repository.network.schema.RepositoryResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Clelia López on 02/26/21
 */
class HomeNetworkRequest(
    retrofitWebservice: RetrofitWebservice
):
    GenericNetworkRequest<RepositoriesResponse>(retrofitWebservice) {

    init {
        tagClass = javaClass.simpleName
        logger = Logger(tagClass)
    }

    sealed class Result {
        class Success(val items: List<RepositoryResponse>): Result()
        object Failure: Result()
    }

    suspend fun getRepositories(page: Int): Result {
        val method = "getRepositories"
        return withContext(Dispatchers.IO) {
            try {

                response = webservice.getRepositories(page)

                if (isResponseValid) {
                    return@withContext Result.Success(responseBody.items)
                } else {
                    logger.log(method, "Error: $responseError")
                    return@withContext Result.Failure
                }
            } catch (exception: Throwable) {
                throwable = exception
                if (exception !is CancellationException) {
                    logger.log(method, "Exception: $errorMessage")
                    return@withContext Result.Failure
                } else {
                    logger.log(method, "CancellationException occurred")
                    throw exception
                }
            }
        }
    }
}
