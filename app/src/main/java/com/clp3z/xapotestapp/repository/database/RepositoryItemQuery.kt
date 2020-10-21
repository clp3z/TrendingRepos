package com.clp3z.xapotestapp.repository.database

/**
 * Created by Clelia López on 10/21/20
 */
data class RepositoryItemQuery (
    var id: Int,
    var name: String,
    var owner_login: String,
    var forks: Int,
    var owner_avatar: String
)