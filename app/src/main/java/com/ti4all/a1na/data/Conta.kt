package com.ti4all.a1na.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conta")
data class Conta(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name : String,
    var dolar : Double,
    var euro : Double,
    var real : Double
)

