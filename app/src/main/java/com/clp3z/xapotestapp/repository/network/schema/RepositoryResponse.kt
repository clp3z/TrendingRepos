package com.clp3z.xapotestapp.repository.network

import com.google.gson.annotations.SerializedName

/**
 * Created by Clelia López on 10/9/20
 */
data class RepositoryResponse (

    var id: Int,

    @SerializedName("full_name")
    var name: String,

    var description: String?,

    var owner: OwnerResponse,

    var forks: Int,

    @SerializedName("open_issues")
    var issues: Int,

    var watchers: Int,

    @SerializedName("created_at")
    var created: String,

    @SerializedName("updated_at")
    var updated: String,

    @SerializedName("pushed_at")
    var pushed: String,

    var size: Int,

    var language: String,

    @SerializedName("html_url")
    var url: String,

    @SerializedName("contributors_url")
    var contributorsUrl: String
)