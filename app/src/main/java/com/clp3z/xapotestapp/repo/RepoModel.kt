package com.clp3z.xapotestapp.repo

import com.clp3z.xapotestapp.base.database.LocalDatabaseDAO
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.repository.database.RepositoryQuery

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