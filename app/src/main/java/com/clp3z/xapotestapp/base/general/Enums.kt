package com.clp3z.xapotestapp.base.general

/**
 * Created by Clelia López on 3/2/20
 */

enum class RepositoryState {

    // Internet - Request successful
    DATA_UPDATED_FROM_NETWORK,

    // No Internet - Updated from database
    DATA_UPDATED_FROM_DATABASE,

    // No internet - No database
    DATA_EMPTY,

    // Unknown error on server side
   DATA_ERROR
}

enum class HomeViewState {

    // Downloading dialog
    DOWNLOADING,

    // Error message view
    UNKNOWN_ERROR,

    // No internet message view
    NO_INTERNET,

    // Snack with Retry action
    NO_INTERNET_SNACKBAR,

    ACTIVE
}