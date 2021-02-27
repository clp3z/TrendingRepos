package com.clp3z.xapotestapp.base.generic

import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.repository.network.client.RetrofitWebservice
import retrofit2.Response

/**
 * Created by Clelia López on 02/27/21
 */
abstract class GenericNetworkRequest<T>(
    protected val webservice: RetrofitWebservice
) {

    protected lateinit var tagClass: String
    protected lateinit var logger: Logger

    protected lateinit var response: Response<T>
    protected lateinit var throwable: Throwable

    protected val isResponseValid get() = response.isValid()
    protected val responseBody get() = response.getBody()
    protected val responseError get() = response.errorBody()
    protected val errorMessage get() = throwable.localizedMessage


    private fun <T> Response<T>.isValid() = isSuccessful && body() != null

    private fun <T> Response<T>.getBody(): T = body()!!
}
