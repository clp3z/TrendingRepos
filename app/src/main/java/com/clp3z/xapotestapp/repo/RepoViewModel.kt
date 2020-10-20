package com.clp3z.xapotestapp.repo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clp3z.xapotestapp.base.generic.GenericViewModel
import com.clp3z.xapotestapp.repository.database.Repository
import kotlinx.coroutines.launch

/**
 * Created by Clelia López on 10/19/20
 */
class RepoViewModel(
    application: Application,
    repoModel: RepoModel
):
    GenericViewModel<RepoModel>(application, repoModel) {

    /**
     * Retrieves repository from database and updates via LiveData
     */
    private var _repository =  MutableLiveData<Repository>()
    val repository: LiveData<Repository>
        get() = _repository


    override fun fetch() {
        uiScope.launch {
            _repository.value = model.getRepositoryById()
        }
    }

    override fun handleClick(id: Int) {
        // Does nothing
    }
}