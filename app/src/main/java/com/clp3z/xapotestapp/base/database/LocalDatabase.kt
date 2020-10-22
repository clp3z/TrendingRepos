package com.clp3z.xapotestapp.base.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clp3z.xapotestapp.base.general.DATABASE_NAME
import com.clp3z.xapotestapp.repository.database.Repository

/**
 * Created by Clelia López on 10/10/20
 */
@Database(entities = [Repository::class], version = 1,  exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    // Associated the DAO with this database
    abstract val localDatabaseDao: LocalDatabaseDAO

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