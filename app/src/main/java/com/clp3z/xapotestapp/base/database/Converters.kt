package com.clp3z.xapotestapp.base.database

import androidx.room.TypeConverter
import com.clp3z.xapotestapp.repository.database.Owner
import com.google.gson.Gson

/**
 * Created by Clelia López on 10/10/20
 */
class Converters {

    @TypeConverter
    fun ownerToString(owner: Owner): String
            = Gson().toJson(owner)

    @TypeConverter
    fun fromJsonOwner(jsonOwner: String): Owner
            = Gson().fromJson(jsonOwner, Owner::class.java)
}