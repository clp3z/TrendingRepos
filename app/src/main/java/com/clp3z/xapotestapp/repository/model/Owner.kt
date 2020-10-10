package com.clp3z.xapotestapp.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Clelia López on 10/9/20
 */
@Entity(tableName = "owner_table")
data class Owner (

    @PrimaryKey
    var id: Int,

    @ColumnInfo
    @SerializedName("avatar_url")
    var avatar: String,

    @ColumnInfo
    @SerializedName("html_url")
    var url: String
)