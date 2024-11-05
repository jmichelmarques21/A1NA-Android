package com.ti4all.a1na.controller

import com.ti4all.a1na.data.Conta
import com.ti4all.a1na.dao.ContaDao

class ContaController(private val contaDao: ContaDao) {

    // Método para criar uma conta
    suspend fun createConta(name: String): Conta {
        val conta = Conta(name = name, dolar = 0.0, euro = 0.0, real = 0.0)
        contaDao.insert(conta)
        return conta
    }


    suspend fun depositar(id: Int, valor: Number, moeda: Int): Conta? {
        val conta = contaDao.conta(id) ?: return null

        when (moeda) {
            1 -> {
                conta.euro += valor.toDouble()
                contaDao.euro(conta.euro)
            }
            2 -> {
                conta.dolar += valor.toDouble()
                contaDao.dolar(conta.dolar)
            }
            3 -> {
                conta.real += valor.toDouble()
                contaDao.real(conta.real)
            }
            else -> {
                return null
            }
        }

        return conta
    }
}