package com.example.kspatual.dao

import androidx.room.*
import com.example.kspatual.model.AccountModel

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountModel)

    @Update
    suspend fun update(account: AccountModel)

    @Delete
    suspend fun delete(account: AccountModel)

    @Query("SELECT * FROM accounts WHERE userId = :id")
    suspend fun get(id: Int): AccountModel

    @Query("SELECT * FROM accounts WHERE userId = :userId")
    suspend fun getAccountsForUser(userId: Int): List<AccountModel>

    @Query("SELECT * FROM accounts")
    suspend fun getAccountsForUserAll(): List<AccountModel>

}
