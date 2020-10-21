package com.clp3z.xapotestapp.home

import android.app.Application
import com.clp3z.xapotestapp.base.database.LocalDatabaseDAO
import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.general.REPOSITORIES_REQUEST
import com.clp3z.xapotestapp.base.general.isInternetAvailable
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.base.interfaces.Listener
import com.clp3z.xapotestapp.repository.database.Repository
import com.clp3z.xapotestapp.repository.network.RepositoriesRequest
import com.clp3z.xapotestapp.repository.network.RepositoriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Clelia López on 10/20/20
 */

/* TODO:
* Which are the responsibilities of the model?
*
* The model should:
*
* - Perform the network request if:
*   There are no elements in the repositories list
*   Is getting more item onScroll
*
* -
*/

class HomeModel(
    private val application: Application,
    localDatabase: LocalDatabaseDAO,
    params: Pair<Boolean,Int>
):
    GenericModel<Pair<Boolean,Int>>(localDatabase, params),
    Listener.OnServerResponseListener<RepositoriesResponse> {


    private lateinit var request: RepositoriesRequest
    private lateinit var repositoryList: List<Repository>

    init {
        TAG = javaClass.simpleName
        logger = Logger(TAG)
    }

    override fun fetch() {
        if (isInternetAvailable(application)) {
            request = RepositoriesRequest(arguments.second, REPOSITORIES_REQUEST, this)
            request.performServerRequest()
        }
    }

    override fun onServerResponse(response: RepositoriesResponse?, returnCode: Int) {
        when (returnCode) {
            REPOSITORIES_REQUEST -> {
                // repositoryList = response?.items!!
            }

            // TODO: Manage server error code
            else ->  logger.logError("onServerResponse", "Error with code: ")
        }
    }

    private suspend fun insertAll(repositories: List<Repository>) {
        return withContext(Dispatchers.IO) {
            localDatabase.insertAll(repositories)
        }
    }
}