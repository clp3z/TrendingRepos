package com.clp3z.xapotestapp.base.database

import androidx.room.TypeConverter
import com.clp3z.xapotestapp.repository.network.OwnerResponse
import com.google.gson.Gson

/**
 * Created by Clelia López on 10/10/20
 */
class Converters {

    @TypeConverter
    fun ownerToString(ownerResponse: OwnerResponse): String
            = Gson().toJson(ownerResponse)

    @TypeConverter
    fun fromJsonOwner(jsonOwner: String): OwnerResponse
            = Gson().fromJson(jsonOwner, OwnerResponse::class.java)
}