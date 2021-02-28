package com.clp3z.xapotestapp.repository.network.schema

import com.squareup.moshi.Json

/**
 * Created by Clelia López on 02/26/21
 */
data class RepositoryResponse (

    var id: Int,

    @Json(name = "full_name")
    var name: String,

    var description: String?,

    var owner: OwnerResponse,

    var forks: Int,

    @Json(name = "open_issues")
    var issues: Int,

    var watchers: Int,

    @Json(name = "created_at")
    var created: String,

    @Json(name = "updated_at")
    var updated: String,

    @Json(name = "pushed_at")
    var pushed: String,

    var size: Int,

    var language: String,

    @Json(name = "html_url")
    var url: String,

    @Json(name = "contributors_url")
    var contributorsUrl: String
)