package com.clp3z.xapotestapp.repository.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.clp3z.xapotestapp.base.generic.GenericPreference
import kotlinx.coroutines.flow.map

/**
 * Created by Clelia López on 3/2/21
 */
@Suppress("PrivatePropertyName")
class RepositoryPreference(
    dataStore: DataStore<Preferences>
):
    GenericPreference<Boolean>(
        dataStore
    ) {

    val isRepositoryTableEmptyLiveDate = preference

    private val IS_REPOSITORY_TABLE_EMPTY =
        booleanPreferencesKey("is_repository_table_empty")

    init {
        preferenceFlow = dataStore.data
        .map { preference ->
            preference[IS_REPOSITORY_TABLE_EMPTY] ?: true
        }
    }

    override suspend fun update(value: Boolean) {
        dataStore.edit { preference ->
            preference[IS_REPOSITORY_TABLE_EMPTY] = value
        }
    }
}

