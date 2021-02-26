package com.clp3z.xapotestapp.repository.database.client

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Created by Clelia López on 02/26/21
 */
interface BaseDAO<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item: T)

    @Delete
    fun delete(vararg item: T)

    @Update
    fun update(vararg item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<T>)
}