package com.clp3z.xapotestapp.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Clelia López on 10/9/20
 */
@Entity(tableName = "repository_table")
data class Repository (

    @PrimaryKey
    var id: Int,

    @ColumnInfo
    @SerializedName("full_name")
    var name: String,

    @ColumnInfo
    var description: String,

    @ColumnInfo
    var owner: Owner,

    @ColumnInfo
    var forks: Int,

    @ColumnInfo
    @SerializedName("open_issues")
    var issues: Int,

    @ColumnInfo
    var watchers: Int,

    @ColumnInfo
    @SerializedName("created_at")
    var created: String,

    @ColumnInfo
    @SerializedName("updated_at")
    var updated: String,

    @ColumnInfo
    @SerializedName("pushed_at")
    var pushed: String,

    @ColumnInfo
    var size: Int,

    @ColumnInfo
    var language: String,

    @ColumnInfo
    @SerializedName("html_url")
    var url: String,

    @ColumnInfo
    @SerializedName("contributors_url")
    var contributorsUrl: String
)