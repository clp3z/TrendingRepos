package com.clp3z.xapotestapp.sections.home.domain

import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import com.clp3z.xapotestapp.repository.database.client.RepositoryRoomDAO
import com.clp3z.xapotestapp.repository.database.entity.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Clelia López on 02/26/21
 */
class HomeDAO(private val repositoryRoomRoomDAO: RepositoryRoomDAO) {

    suspend fun insertAll(repositories: List<Repository>) {
        return withContext(Dispatchers.IO) {
            repositoryRoomRoomDAO.insertAll(repositories)
        }
    }

    suspend fun queryRepositories(): List<RepositoryItemQuery> {
        return withContext(Dispatchers.IO) {
            repositoryRoomRoomDAO.getRepositories()
        }
    }
}