package com.clp3z.xapotestapp.sections.repository

import android.app.Application
import com.clp3z.xapotestapp.base.generic.GenericViewModel

/**
 * Created by Clelia López on 10/19/20
 */
class RepositoryViewModelR(
    application: Application,
    repositoryModel: RepositoryModel
):
    GenericViewModel<RepositoryModel>(application, repositoryModel) {

    override fun fetch() {

    }

    override fun handleClick(id: Int) {
        // Does nothing
    }
}