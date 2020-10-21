package com.clp3z.xapotestapp.base.generic

import com.clp3z.xapotestapp.base.database.LocalDatabaseDAO
import com.clp3z.xapotestapp.base.general.Logger
import com.clp3z.xapotestapp.base.interfaces.ModelMethods

/**
 *  The model carries the business logic for an specific section of the application.
 *
 *  In this way, it represents the Domain layer and such, it should be separated from the ViewModel,
 *  which is part of the Presentation layer.
 *
 * Created by Clelia López on 10/19/20
 */
abstract class GenericModel<T> (
    protected val localDatabase: LocalDatabaseDAO,
    protected val arguments: T
):
    ModelMethods {

    /**
     * Logger
     */
    protected lateinit var TAG: String
    protected lateinit var logger: Logger


    init {
        this.fetch()
    }
}