package com.example.kspatual.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kspatual.dao.AccountDao
import com.example.kspatual.dao.UserDao
import com.example.kspatual.model.AccountModel
import com.example.kspatual.model.UserModel

@Database(entities = [UserModel::class, AccountModel::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun accountDao(): AccountDao
}