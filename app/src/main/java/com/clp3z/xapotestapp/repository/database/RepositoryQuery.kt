package com.clp3z.xapotestapp.repository.database

/**
 * Created by Clelia López on 10/21/20
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