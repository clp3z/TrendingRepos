package com.clp3z.xapotestapp.base.generic

import com.clp3z.xapotestapp.base.database.DatabaseDAO

/**
 *  The model carries the business logic for an specific section of the application.
 *
 *  In this way, it represents the Domain layer and such, it should be separated from the ViewModel,
 *  which is part of the Presentation layer.
 *
 * Created by Clelia López on 10/19/20
 */
abstract class GenericModel<T> (
    protected val database: DatabaseDAO,
    protected val arguments: T
)