package com.clp3z.xapotestapp.base.util

import com.clp3z.xapotestapp.repository.database.embedded.Owner
import com.clp3z.xapotestapp.repository.database.entity.Repository
import com.clp3z.xapotestapp.repository.model.RepositoryItemQuery
import com.clp3z.xapotestapp.repository.network.schema.OwnerResponse
import com.clp3z.xapotestapp.repository.network.schema.RepositoryResponse

/**
 * Created by Clelia López on 02/26/21
 */
fun RepositoryResponse.toRepository() = Repository(
    id,
    name,
    description,
    owner.toOwner(),
    forks,
    issues,
    watchers,
    created,
    updated,
    pushed,
    size,
    language,
    url,
    contributorsUrl,
)

private fun OwnerResponse.toOwner() = Owner(
    id,
    avatar,
    url,
    login
)

fun Repository.toRepositoryItemQuery() = RepositoryItemQuery(
    id,
    name,
    owner.login,
    forks,
    owner.avatar
)
