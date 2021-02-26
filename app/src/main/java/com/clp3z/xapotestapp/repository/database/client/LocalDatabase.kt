package com.clp3z.xapotestapp.repository.database.client

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clp3z.xapotestapp.base.general.DATABASE_NAME
import com.clp3z.xapotestapp.repository.database.entity.Repository

/**
 * Created by Clelia López on 02/26/21
 */
@Database(entities = [Repository::class], version = 1,  exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract val repositoryRoomDAO: RepositoryRoomDAO

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
