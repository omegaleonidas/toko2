package com.sidiq.toko.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sidiq.toko.Model.TokoModel


@Database(entities = [TokoModel::class], version = 2)

abstract class DatabaseToko : RoomDatabase() {

    abstract fun tokoDAO(): DaoToko

    companion object {
        @Volatile
        private var INSTANCE: DatabaseToko? = null
        fun getInstance(context: Context): DatabaseToko? {

            if (INSTANCE == null) {
                synchronized(DatabaseToko::class) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseToko::class.java,
                            "dbToko.db"
                        ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

    }
}