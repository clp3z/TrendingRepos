package com.clp3z.xapotestapp.viewmodel

import androidx.lifecycle.ViewModel
import com.clp3z.xapotestapp.repository.database.LocalDatabaseDAO

/**
 * Created by Clelia López on 10/10/20
 */
class MainViewModel(private val dataSource: LocalDatabaseDAO)
    : ViewModel() {

}