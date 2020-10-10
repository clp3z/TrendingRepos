package com.clp3z.xapotestapp.repository.network

import com.clp3z.bottlerocket.base.network.Client
import com.clp3z.xapotestapp.base.Listener
import com.clp3z.xapotestapp.base.Logger

/**
 * Created by Clelia López on 10/10/20
 */
class RepositoriesRequest(
    parameters: Unit,
    returnCode: Int,
    listener: Listener.OnServerResponseListener<RepositoriesResponse>
):
    ServerRequestStrategy<Unit, RepositoriesResponse>(parameters, returnCode, listener) {

    init {
        TAG = javaClass.simpleName
        logger = Logger(TAG)
    }

    override fun setCall() {
        call = Client.getRestAPIService()?.getRepositories()
    }
}
