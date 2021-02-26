package com.clp3z.xapotestapp.repository.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.clp3z.xapotestapp.repository.network.OwnerResponse

/**
 * Created by Clelia López on 10/9/20
 */
@Entity(tableName = "repository_table")
data class Repository (

    @PrimaryKey
    var id: Int,

    @ColumnInfo
    var name: String,

    @ColumnInfo
    var description: String?,

    @Embedded(prefix = "owner_")
    var owner: OwnerResponse,

    @ColumnInfo
    var forks: Int,

    @ColumnInfo
    var issues: Int,

    @ColumnInfo
    var watchers: Int,

    @ColumnInfo
    var created: String,

    @ColumnInfo
    var updated: String,

    @ColumnInfo
    var pushed: String,

    @ColumnInfo
    var size: Int,

    @ColumnInfo
    var language: String,

    @ColumnInfo
    var url: String,

    @ColumnInfo
    var contributorsUrl: String
)
