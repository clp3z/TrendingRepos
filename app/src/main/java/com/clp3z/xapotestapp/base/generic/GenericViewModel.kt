package com.clp3z.xapotestapp.base.generic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.clp3z.properlytestapp.base.architecture.ViewModelMethods
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Base class for any ViewModel in this MVVMB (Model-View-ViewModel-Binder) architecture
 *
 * A ViewModel is an Android architecture component designed to hold UI's/View data (as is needed
 * via transformations maps) and survive configuration changes.
 *
 * As the ViewModel should not contain any reference to View Components (Activities, Fragments or Views),
 * or any data that would be destroyed on configuration changes but is needed by the UI, the
 * communication with the View layer is done via LiveData updates, with one or two-way data binding.
 *
 * Created by Clelia López on 10/19/20
 */
@Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")
abstract class GenericViewModel<M>(
    protected val appContext: Application,
    protected val model: M
):
    AndroidViewModel(appContext), ViewModelMethods {

    protected val viewModelJob: Job by lazy {
        Job()
    }

    protected val uiScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + viewModelJob)
    }

    init {
        this.fetch()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}