package com.clp3z.xapotestapp.screen.repository.domain

import com.clp3z.xapotestapp.base.generic.GenericDAO
import com.clp3z.xapotestapp.repository.model.RepositoryQuery
import com.clp3z.xapotestapp.repository.database.client.RepositoryRoomDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Clelia López on 02/26/21
 */
class RepositoryDAO (
    dao: RepositoryRoomDAO
):
    GenericDAO<RepositoryRoomDAO>(dao){

    suspend fun getRepositoryById(id: Int): RepositoryQuery {
        return withContext(Dispatchers.IO) {
            roomDAO.getRepositoryById(id)
        }
    }
}