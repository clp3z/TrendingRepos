package com.clp3z.xapotestapp.base.generic

import com.clp3z.xapotestapp.repository.database.client.BaseDAO

/**
 * Created by Clelia López on 02/27/21
 */
abstract class GenericDAO<T: BaseDAO<*>>(
    protected val roomDAO: T
)