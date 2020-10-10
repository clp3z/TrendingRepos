package com.clp3z.xapotestapp.base

import com.clp3z.xapotestapp.repository.model.Repository


/**
 * Created by Clelia López on 10/10/20
 */
interface Listener {

    interface OnItemClickListener {

        fun onItemSelected(repository: Repository)
    }

    interface OnServerResponseListener<R> {

        fun onServerResponse(response: R?, returnCode: Int)
    }

    interface RepositoryEventListener {

        fun onDataReady()

        fun onConnectionNotAvailable()

        fun onCacheNotAvailable()

        fun onUnknownError()
    }
}