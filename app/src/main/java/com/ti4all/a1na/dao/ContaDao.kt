package com.ti4all.a1na.dao

import androidx.room.Insert
import androidx.room.Query
import com.ti4all.a1na.data.Conta

interface ContaDao {

    @Insert
    suspend fun insert(conta : Conta)

    @Query("SELECT * FROM conta WHERE id = :id")
    suspend fun conta(id: Int): Conta?

    @Query ("UPDATE CONTA SET dolar = :dolar ")
    suspend fun dolar(dolar : Number,)

    @Query ("UPDATE CONTA SET real = :real ")
    suspend fun real(real : Number,)

    @Query ("UPDATE CONTA SET euro = :euro ")
    suspend fun euro(euro : Number,)

}