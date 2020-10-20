package com.clp3z.xapotestapp.base.interfaces

/**
 * Created by Clelia López on 10/19/20
 */
interface Listener {

    interface OnServerResponseListener<R> {

        fun onServerResponse(response: R?, returnCode: Int)
    }
}