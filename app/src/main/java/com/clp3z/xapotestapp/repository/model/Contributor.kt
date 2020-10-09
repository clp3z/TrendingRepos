package com.clp3z.xapotestapp.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Clelia López on 10/9/20
 */
@Entity(tableName = "contributor_table")
data class Contributor (

    @PrimaryKey
    val id: Int,

    @ColumnInfo
    @SerializedName("avatar_url")
    val avatar: String,

    @ColumnInfo
    @SerializedName("html_url")
    val url: String
)