package com.clp3z.xapotestapp.repository.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.clp3z.xapotestapp.base.general.PREFERENCES_NAME


/**
 * Created by Clelia López on 3/2/21
 */
class DataStoreClient(context: Context) {

    private val Context.dataStore: DataStore<Preferences> by
        preferencesDataStore(name = PREFERENCES_NAME)

    val dataStoreInstance: DataStore<Preferences> by lazy {
        context.dataStore
    }
}