package com.clp3z.xapotestapp.screen.home.domain

import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.generic.GenericNetworkRequest
import com.clp3z.xapotestapp.repository.network.client.RetrofitWebservice
import com.clp3z.xapotestapp.repository.network.schema.RepositoriesResponse
import com.clp3z.xapotestapp.repository.network.schema.RepositoryResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.await

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

    suspend fun getRepositories(page: Int): List<RepositoryResponse>? {
        val method = "getRepositories"
        return withContext(Dispatchers.IO) {
            try {
                result = webservice.getRepositories(page).await()
                return@withContext result?.items

            } catch (exception: Throwable) {
                when (exception) {
                    is CancellationException ->
                        logger.logWarning(method, "CancellationException occurred")

                    is HttpException -> {
                        httpException = exception
                        logger.logError(method, "HttpException: $httpError")
                    }

                    else -> {
                        throwable = exception
                        logger.logError(method, "Exception: $exceptionMessage")
                    }
                }
            }
            return@withContext null
        }
    }
}
