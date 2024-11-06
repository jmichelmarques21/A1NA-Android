package com.ti4all.a1na.dao

import androidx.room.Insert
import androidx.room.Query
import com.ti4all.a1na.data.Conta

interface ContaDao {

    @Insert
    suspend fun insert(conta: Conta)

    @Query("SELECT * FROM conta WHERE id = :id")
    suspend fun conta(id: Int): Conta?

    @Query("UPDATE conta SET dolar = :dolar WHERE id = :id")
    suspend fun dolar(dolar: Double, id: Int)

    @Query("UPDATE conta SET real = :real WHERE id = :id")
    suspend fun real(real: Double, id: Int)

    @Query("UPDATE conta SET euro = :euro WHERE id = :id")
    suspend fun euro(euro: Double, id: Int)
}
