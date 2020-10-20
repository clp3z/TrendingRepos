package com.clp3z.xapotestapp.base.general

/**
 * Created by Clelia López on 10/19/20
 */

enum class ModelState {

    // No internet connection detected when performing network request
    NO_INTERNET,

    // Room database does not exist yet.
    // A network request will be performed and the data will later on be stored on it
    LOADING,

    // Downloading more items into the database for infinite scrolling
    DOWNLOADING,

    // Room database exits, so data will be loaded from it
    AVAILABLE,

    // Unknown error
    ERROR
}