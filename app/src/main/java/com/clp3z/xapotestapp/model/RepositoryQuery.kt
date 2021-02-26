package com.clp3z.xapotestapp.model

/**
 * Created by Clelia López on 02/26/21
 */
data class RepositoryQuery (
    var id: Int,
    var name: String,
    var description: String?,
    var forks: Int,
    var watchers: Int,
    var issues: Int,
    var owner_avatar: String
)