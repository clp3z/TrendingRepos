package com.clp3z.xapotestapp.sections.repository

import com.clp3z.xapotestapp.model.RepositoryQuery
import com.clp3z.xapotestapp.repository.database.client.RepositoryRoomDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryDAO (
    private val repositoryRoomDAO: RepositoryRoomDAO,
    private val id: Int
) {

    suspend fun getRepositoryById(): RepositoryQuery {
        return withContext(Dispatchers.IO) {
            repositoryRoomDAO.getRepositoryById(id)
        }
    }
}