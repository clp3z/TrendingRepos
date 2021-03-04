package com.clp3z.xapotestapp.base.general

/**
 * Created by Clelia López on 3/2/20
 */

enum class RepositoryState {

    // Internet- Performing network request
    DATA_DOWNLOADING,

    // Internet - Request successful
    DATA_UPDATED_FROM_NETWORK,

    // No Internet - Updated from database
    DATA_UPDATED_FROM_DATABASE,

    // No internet - No database
    DATA_EMPTY_NO_INTERNET,

    // Error on server side - No database
   DATA_EMPTY_REQUEST_ERROR,

   // Error on subsequent network call
   DATA_ERROR_WITH_DATA
}

enum class HomeViewState {

    INITIAL_DOWNLOAD,

    // Downloading dialog
    DOWNLOADING,

    // Error message view
    UNKNOWN_ERROR,

    // Error message on subsequent call
    SUBSEQUENT_UNKNOWN_ERROR,

    // No internet message view
    NO_INTERNET,

    // Snack with Retry action
    NO_INTERNET_SNACKBAR,

    ACTIVE
}