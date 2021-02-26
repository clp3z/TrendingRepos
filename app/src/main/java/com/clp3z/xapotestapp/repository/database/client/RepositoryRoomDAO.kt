package com.clp3z.xapotestapp.repository.database.client

import androidx.room.Dao
import androidx.room.Query
import com.clp3z.xapotestapp.repository.database.RepositoryItemQuery
import com.clp3z.xapotestapp.repository.database.RepositoryQuery
import com.clp3z.xapotestapp.repository.database.entity.Repository

/**
 * Created by Clelia López on 02/26/21
 */
@Dao
interface RepositoryRoomDAO: BaseDAO<Repository> {

    @Query("SELECT id, name, description, forks, watchers, issues, owner_avatar FROM repository_table WHERE id= :id")
    fun getRepositoryById(id: Int): RepositoryQuery

    @Query("SELECT id, name, owner_login, forks, owner_avatar FROM repository_table ORDER BY forks DESC")
    fun getRepositories(): List<RepositoryItemQuery>
}