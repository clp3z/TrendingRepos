package com.clp3z.xapotestapp.repository.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map


/**
 * Created by Clelia López on 3/2/21
 */
@Suppress("PrivatePropertyName")
class RepositoryPreference(
    private val dataStore: DataStore<Preferences>
) {

    private val IS_REPOSITORY_TABLE_EMPTY =
        booleanPreferencesKey("is_repository_table_empty")

    private val isRepositoryTableEmptyFlow = dataStore.data
        .map { preference ->
            preference[IS_REPOSITORY_TABLE_EMPTY] ?: true
        }

    suspend fun repositoryTableUpdated() {
        dataStore.edit { preference ->
            preference[IS_REPOSITORY_TABLE_EMPTY] = false
        }
    }

    val isRepositoryTableEmpty = isRepositoryTableEmptyFlow.asLiveData()
}