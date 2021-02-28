package com.clp3z.xapotestapp.repository.network.schema

import com.squareup.moshi.Json

/**
 * Created by Clelia López on 02/26/21
 */
data class OwnerResponse (

    var id: Int,

    @Json(name = "avatar_url")
    var avatar: String,

    @Json(name = "html_url")
    var url: String,

    var login: String
)