package com.example.kspatual.dao


import androidx.room.*
import com.example.kspatual.data.UserWithAccounts
import com.example.kspatual.model.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserModel)

    @Update
    suspend fun update(user: UserModel)

    @Delete
    suspend fun delete(user: UserModel)


    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserModel>

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getAllUserWithAccounts(userId: Int): List<UserWithAccounts>
}