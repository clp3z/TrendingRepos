package com.clp3z.xapotestapp.base.interfaces

interface RepositoryMethods {

    fun fetch(
        visibleItemCount: Int,
        totalItemCount: Int,
        firstVisibleItemPosition: Int
    )
}