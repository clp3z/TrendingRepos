package com.clp3z.xapotestapp.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.clp3z.xapotestapp.repository.model.Owner
import com.clp3z.xapotestapp.repository.model.Repository

/**
 * Created by Clelia López on 10/10/20
 */

const val DATABASE_NAME = "local_database"

@Database(entities = [Repository::class], version = 1,  exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {

    // Associated the DAO with this database
    abstract val databaseDao: LocalDatabaseDAO

    companion object {

        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context) : LocalDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }

                INSTANCE = instance

                return instance
            }
        }
    }
}