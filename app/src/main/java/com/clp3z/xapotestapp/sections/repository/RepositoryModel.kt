package com.clp3z.xapotestapp.sections.repository

import com.clp3z.xapotestapp.repository.database.client.LocalDatabaseDAO
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.model.RepositoryQuery

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Clelia López on 10/19/20
 */
class RepoModel(
    localDatabase: LocalDatabaseDAO,
    id: Int
)
    : GenericModel<Int>(localDatabase, id) {


    override fun fetch() {
        // Does nothing
    }

    suspend fun getRepositoryById(): RepositoryQuery {
        return withContext(Dispatchers.IO) {
            localDatabase.getRepositoryById(param)
        }
    }
}