package com.clp3z.xapotestapp.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.clp3z.xapotestapp.repository.model.Owner
import com.clp3z.xapotestapp.repository.model.Repository

/**
 * Created by Clelia López on 10/9/20
 */
@Dao
interface LocalDatabaseDAO {

    @Insert
    fun insert(repository: Repository)

    @Query("SELECT * FROM repository_table WHERE id= :id")
    fun get(id: Int): Repository

    @Query("SELECT * FROM repository_table")
    fun getRepositories(): LiveData<List<Repository>>

    @Query("SELECT * FROM owner_table WHERE id= :id")
    fun getRepositoryContributors(id: Int): LiveData<List<Owner>>

    @Insert
    fun insert(contributor: Owner)
}