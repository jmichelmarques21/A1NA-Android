package com.example.kspatual


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.kspatual.dao.AccountDao
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.model.AccountModel
import com.example.kspatual.model.UserModel
import com.example.kspatual.ui.theme.KSPatualTheme
import com.example.kspatual.ui.theme.NavGrap
import com.example.kspatual.view.Tela2
import com.example.kspatual.view.Tela3
import com.example.kspatual.viewmodel.*
import kotlinx.coroutines.launch

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import com.example.kspatual.api.getCotacoes


class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user_database"
        ).build()

        lifecycleScope.launch {
            val user = UserModel(name = "Lucas Matheus", cpf = "08645112990")
            val accont = AccountModel(userId = 1 , real = 120.00, dollar = 120.00, euro = 100.0)
            //database.userDao().insert(user)
            //database.accountDao().insert(accont)
            //insertSampleData(database)
            //val account = database.accountDao().get(2)
            //if (account != null) {
            val quotes = getCotacoes()
                deposit(database,  "euro", 200.0, quotes)
            //}
        }

        setContent {
            KSPatualTheme {
                NavGrap(database)
                CurrencyScreen()
                //UserListScreen(database = database)
            }
        }
    }

    @Composable //EXEMPLO DE TELA, APENAS PARA DESENVOLVIMENTO, ** → EXCLUIR MAIS TARDE ← **
    fun CurrencyScreen() {
        val scope = rememberCoroutineScope()
        var cotaUSD by remember { mutableStateOf("Carregando...") }
        var cotaEUR by remember { mutableStateOf("Carregando...") }
        var cotaBTC by remember { mutableStateOf("Carregando...") }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        LaunchedEffect(Unit) {
            scope.launch {
                try {
                    val quotes = getCotacoes()
                    cotaUSD = quotes.usdToBrl
                    cotaEUR = quotes.eurToBrl
                    cotaBTC = quotes.btcToBrl
                } catch (e: Exception) {
                    errorMessage = "Erro ao buscar cotações: ${e.message}"
                }
            }
        }

        Column {
            Text(text = "USD para BRL: $cotaUSD")
            Text(text = "EUR para BRL: $cotaEUR")
            Text(text = "BTC para BRL: $cotaBTC")
            errorMessage?.let {
                Text(text = it, color = Color.Red)
            }
        }
    }
}