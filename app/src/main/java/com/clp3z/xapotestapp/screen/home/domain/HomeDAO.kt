package com.clp3z.xapotestapp.screen.home.domain

import com.clp3z.xapotestapp.base.generic.GenericDAO
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import com.clp3z.xapotestapp.repository.database.client.RepositoryRoomDAO
import com.clp3z.xapotestapp.repository.database.entity.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Clelia López on 02/26/21
 */
class HomeDAO(
    dao: RepositoryRoomDAO
):
    GenericDAO<RepositoryRoomDAO>(dao) {

    suspend fun insertAll(repositories: List<Repository>) {
        return withContext(Dispatchers.IO) {
            roomDAO.insertAll(repositories)
        }
    }

    suspend fun queryRepositories(): List<RepositoryItemQuery> {
        return withContext(Dispatchers.IO) {
            roomDAO.getRepositories()
        }
    }
}