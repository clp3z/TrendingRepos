package com.clp3z.xapotestapp.base.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.clp3z.xapotestapp.repository.database.Repository
import com.clp3z.xapotestapp.repository.database.RepositoryItemQuery
import com.clp3z.xapotestapp.repository.database.RepositoryQuery

/**
 * Created by Clelia López on 10/9/20
 */
@Dao
interface LocalDatabaseDAO: DatabaseDAO<Repository> {

    @Query("SELECT id, name, description, forks, watchers, issues, owner_avatar FROM repository_table WHERE id= :id")
    fun getRepositoryById(id: Int): RepositoryQuery

    @Query("SELECT id, name, owner_login, forks, owner_avatar FROM repository_table ORDER BY forks DESC")
    fun getRepositories(): LiveData<List<RepositoryItemQuery>>
}