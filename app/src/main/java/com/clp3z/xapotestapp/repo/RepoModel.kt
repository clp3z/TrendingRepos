package com.clp3z.xapotestapp.repo

import com.clp3z.xapotestapp.base.database.DatabaseDAO
import com.clp3z.xapotestapp.base.generic.GenericModel
import com.clp3z.xapotestapp.repository.database.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Clelia López on 10/19/20
 */
class RepoModel(database: DatabaseDAO, id: Int)
    : GenericModel<Int>(database, id) {

    suspend fun getRepositoryById(): Repository {
        return withContext(Dispatchers.IO) {
            database.get(arguments)
        }
    }
}