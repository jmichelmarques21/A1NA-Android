package com.example.kspatual.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Data class inicial
data class ModeloCotacoes(
    val code: String,
    val codein: String,
    val name: String,
    val high: String,
    val low: String,
    val varBid: String,
    val pctChange: String,
    val bid: String,
    val ask: String,
    val timestamp: String,
    val create_date: String
)

// Interface do Retrofit para a API
interface CotacoesApiService {
    @GET("json/last/{currencies}")
    suspend fun getCurrencies(@Path("currencies") currencies: String): Map<String, ModeloCotacoes>
}

// Instância do Retrofit
object CotacoesApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://economia.awesomeapi.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: CotacoesApiService = retrofit.create(CotacoesApiService::class.java)
}

// Data Class pra armazenar as cotações
data class Cotacoes(
    val usdToBrl: String,
    val eurToBrl: String,
    val btcToBrl: String
)

// Função que retorna as cotações em variáveis distintas
suspend fun getCotacoes(): Cotacoes {
    val currencies = "USD-BRL,EUR-BRL,BTC-BRL"
    val currencyRates = withContext(Dispatchers.IO) {
        CotacoesApi.service.getCurrencies(currencies)
    }

    val cotaUSD = currencyRates["USDBRL"]?.bid ?: "N/A"
    val cotaEUR = currencyRates["EURBRL"]?.bid ?: "N/A"
    val cotaBTC = currencyRates["BTCBRL"]?.bid ?: "N/A"

    return Cotacoes(
        usdToBrl = cotaUSD,
        eurToBrl = cotaEUR,
        btcToBrl = cotaBTC
    )
}