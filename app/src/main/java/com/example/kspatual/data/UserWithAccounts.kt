package com.example.kspatual.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.kspatual.model.AccountModel
import com.example.kspatual.model.UserModel

data class UserWithAccounts(
    @Embedded val user: UserModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val accounts: List<AccountModel>
)