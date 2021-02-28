package com.clp3z.xapotestapp.base.generic

import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.repository.network.client.RetrofitWebservice
import retrofit2.HttpException

/**
 * Created by Clelia López on 02/27/21
 */
abstract class GenericNetworkRequest<T>(
    protected val webservice: RetrofitWebservice
) {

    protected lateinit var tagClass: String
    protected lateinit var logger: Logger

    protected var result: T? = null
    protected lateinit var httpException: HttpException
    protected lateinit var throwable: Throwable


    protected val exceptionMessage
        get() = throwable.message ?: ""

    protected val httpError
        get() = httpException.response()?.errorBody()?.string().toString()
}
