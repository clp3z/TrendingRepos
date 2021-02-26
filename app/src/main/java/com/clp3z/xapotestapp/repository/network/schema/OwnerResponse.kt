package com.clp3z.xapotestapp.repository.network.schema

import com.google.gson.annotations.SerializedName

/**
 * Created by Clelia López on 02/26/21
 */
data class OwnerResponse (

    var id: Int,

    @SerializedName("avatar_url")
    var avatar: String,

    @SerializedName("html_url")
    var url: String,

    var login: String
)