package com.clp3z.xapotestapp.base.network

import com.clp3z.xapotestapp.base.general.ERROR_REQUEST
import com.clp3z.xapotestapp.base.interfaces.Listener
import com.clp3z.xapotestapp.base.general.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Clelia López on 10/10/20
 */
@Suppress("PropertyName")
abstract class ServerRequestStrategy<P,R>(

    /**
     * Data class that maps the parameters required by the server to execute this request
     */
    protected var parameter: P,

    /**
     * Identifier to differentiate from multiple calls on same class
     */
    protected var returnCode: Int,

    /**
     * Callback listener to be invoke when the server returns
     */
    protected var listener: Listener.OnServerResponseListener<R>
) {

    /**
     * Attributes
     */
    protected lateinit var TAG: String
    protected lateinit var logger: Logger

    /**
     * An invocation of a Retrofit method that sends a request to a web server and returns a response
     */
    protected var call: Call<R>? = null

    /**
     * Initializes retrofit call with the appropriate {@param @RestAPIServer}
     */
    abstract fun setCall()

    /**
     * Implements the actual server request using Retrofit
     */
    fun performServerRequest() {

        setCall()

        if (call != null) {
            call?.enqueue(
                object : Callback<R> {
                    override fun onResponse(call: Call<R>, response: Response<R>) {
                        if (response.isSuccessful)
                            listener.onServerResponse(response.body(), returnCode)
                        else {
                            logger.logError("onResponse", "Something went wrong. \nError ${response.message()}")
                            listener.onServerResponse(null, ERROR_REQUEST)
                        }
                    }

                    override fun onFailure(call: Call<R>, throwable: Throwable) {
                        logger.logError("onFailure", throwable.localizedMessage)
                        listener.onServerResponse(null, ERROR_REQUEST)
                    }
                }
            )
        } else
            throw NullPointerException("call:Call<R> is null")
    }
}
