package com.clp3z.xapotestapp.base.util

import com.clp3z.xapotestapp.repository.database.entity.Repository
import com.clp3z.xapotestapp.repository.network.schema.RepositoryResponse

/**
 * Created by Clelia López on 02/26/21
 */
private fun repositoryFromResponse(response: RepositoryResponse): Repository {
    return Repository(
        response.id,
        response.name,
        response.description,
        response.owner,
        response.forks,
        response.issues,
        response.watchers,
        response.created,
        response.updated,
        response.pushed,
        response.size,
        response.language,
        response.url,
        response.contributorsUrl,
    )
}

fun getRepositoryList(list: List<RepositoryResponse>): List<Repository> {
    val result = ArrayList<Repository>()
    for (item in list) {
        result.add(repositoryFromResponse(item))
    }
    return result
}