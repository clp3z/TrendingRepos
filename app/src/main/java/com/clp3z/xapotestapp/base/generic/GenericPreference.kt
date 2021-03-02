package com.clp3z.xapotestapp.base.generic

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow


/**
 * Created by Clelia López on 3/2/21
 */
abstract class GenericPreference<T>(
    protected val dataStore: DataStore<Preferences>
) {

    protected lateinit var preferenceFlow: Flow<T>

    protected val preference get() = preferenceFlow.asLiveData()

    abstract suspend fun update(value: T)
}