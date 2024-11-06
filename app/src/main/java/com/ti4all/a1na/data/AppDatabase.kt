package com.ti4all.a1na.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ti4all.a1na.dao.ContaDao

@Database(entities = [Conta::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contaDao(): ContaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "conta_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
