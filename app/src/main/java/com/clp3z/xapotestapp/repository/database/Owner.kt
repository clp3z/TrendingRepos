package com.clp3z.xapotestapp.repository.database

import com.google.gson.annotations.SerializedName

/**
 * Created by Clelia López on 10/9/20
 */
data class Owner (

    var id: Int,

    @SerializedName("avatar_url")
    var avatar: String,

    @SerializedName("html_url")
    var url: String,

    var login: String
)