package com.clp3z.xapotestapp.base.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clp3z.xapotestapp.repository.database.Repository

/**
 * Created by Clelia López on 10/9/20
 */
@Dao
interface DatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: Repository)

    @Query("SELECT * FROM repository_table WHERE id= :id")
    fun get(id: Int): Repository

    @Query("SELECT * FROM repository_table ORDER BY forks DESC")
    fun getRepositories(): LiveData<List<Repository>>
}