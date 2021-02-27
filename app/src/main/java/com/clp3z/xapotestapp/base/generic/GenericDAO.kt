package com.clp3z.xapotestapp.base.generic

import com.clp3z.xapotestapp.repository.database.client.BaseDAO

abstract class GenericDAO<T: BaseDAO<*>>(protected val roomDAO: T)