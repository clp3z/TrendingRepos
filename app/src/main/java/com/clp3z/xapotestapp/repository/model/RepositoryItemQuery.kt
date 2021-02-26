package com.clp3z.xapotestapp.repository.model

/**
 * Created by Clelia López on 02/26/21
 */
data class RepositoryItemQuery (
    var id: Int,
    var name: String,
    var owner_login: String,
    var forks: Int,
    var owner_avatar: String
)