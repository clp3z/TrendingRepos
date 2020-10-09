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
    val id: Int,

    @ColumnInfo
    @SerializedName("full_name")
    val name: String,

    @ColumnInfo
    val description: String,

    @ColumnInfo
    val owner: Contributor,

    @ColumnInfo
    val forks: Int,

    @ColumnInfo
    @SerializedName("open_issues")
    val issues: Int,

    @ColumnInfo
    val watchers: Int,

    @ColumnInfo
    @SerializedName("created_at")
    val created: String,

    @ColumnInfo
    @SerializedName("updated_at")
    val updated: String,

    @ColumnInfo
    @SerializedName("pushed_at")
    val pushed: String,

    @ColumnInfo
    val size: Int,

    @ColumnInfo
    val language: String,

    @ColumnInfo
    @SerializedName("html_url")
    val url: String,

    @ColumnInfo
    @SerializedName("contributors_url")
    val contributorsUrl: String
)