package com.example.kspatual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.data.UserWithAccounts
import com.example.kspatual.model.AccountModel
import com.example.kspatual.model.UserModel
import com.example.kspatual.ui.theme.KSPatualTheme
import com.example.kspatual.viewmodel.GreetingCard
import kotlinx.coroutines.launch

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
            //insertSampleData()
            val account = database.accountDao().get(2) // Recebe o account de ID 2
            if (account != null) {
                // Executa a função de depósito
                deposit(database, account, "dollar", 200.0, 5.6)
            }
        }

        setContent {
            KSPatualTheme {

                UserListScreen(database = database)
            }
        }
    }

    private suspend fun insertSampleData() {

        val account2 = AccountModel(id = 2, userId = 2, real = 200.0, dollar = 50.0, euro = 30.0)
        database.accountDao().insert(account2)


    }
}

@Composable
fun UserListScreen(database: AppDatabase) {
    var usersWithAccounts by remember { mutableStateOf<List<UserWithAccounts>>(emptyList()) }

    LaunchedEffect(Unit) {

        usersWithAccounts = database.userDao().getAllUserWithAccounts(2)

    }

    LazyColumn {
        items(usersWithAccounts) { userWithAccounts ->
            GreetingCard(userWithAccounts)
        }
    }
}

public suspend fun deposit(
    database: AppDatabase,
    account: AccountModel,
    moeda: String,
    valor: Double,
    valorMoeda: Double
) {
    var deposito = valor * valorMoeda

    // Atualiza o saldo dependendo da moeda
    when (moeda) {
        "dollar" -> {
            account.dollar += deposito
        }
        "euro" -> {
            account.euro += deposito
        }
        "real" -> {
            account.real += deposito
        }
    }

    // Atualiza a conta no banco de dados
    database.accountDao().update(account)
}




