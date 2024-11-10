package com.example.kspatual.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val cpf: String
)