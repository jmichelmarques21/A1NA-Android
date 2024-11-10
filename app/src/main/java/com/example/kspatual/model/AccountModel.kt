package com.example.kspatual.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "accounts",
    foreignKeys = [ForeignKey(
        entity = UserModel::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AccountModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    var real: Double,
    var dollar: Double,
    var euro: Double
)

