package com.example.kspatual.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kspatual.api.Cotacoes
import com.example.kspatual.dao.UserDao
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.data.UserWithAccounts
import com.example.kspatual.model.AccountModel
import com.example.kspatual.model.UserModel

@Composable
fun GreetingCard(userWithAccounts: UserWithAccounts) {
    val user = userWithAccounts.user
    val account = userWithAccounts.accounts.firstOrNull() // Exibe a primeira conta como exemplo

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Bem-vindo, ${user.name}!")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "User_id: ${user.id}")
        Spacer(modifier = Modifier.height(8.dp))

        account?.let {
            Text(text = "Saldo em Reais: ${it.real}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Saldo em Dollar: ${it.dollar}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Saldo em Euro: ${it.euro}")
        } ?: Text("Sem conta associada.")
    }
}

public suspend fun deposit(
    database: AppDatabase,
    moeda: String,
    valor: Double,
    cotacoes: Cotacoes
) {
    val account = database.accountDao().get(1)
    val valorConvertido: Double = when (moeda) {
        "dollar" -> valor * cotacoes.usdToBrl.toDouble()
        "euro" -> valor * cotacoes.eurToBrl.toDouble()
        "real" -> valor
        else -> throw IllegalArgumentException("Moeda não suportada")
    }
    when (moeda) {
        "dollar" -> account.dollar += valorConvertido
        "euro" -> account.euro += valorConvertido
        "real" -> account.real += valorConvertido
    }
    database.accountDao().update(account)
}

@Composable
fun UserListScreen(database: AppDatabase) {
    var usersWithAccounts by remember { mutableStateOf<List<UserWithAccounts>>(emptyList()) }

    LaunchedEffect(Unit) {

        usersWithAccounts = database.userDao().getAllUserWithAccounts(1)

    }

    LazyColumn {
        items(usersWithAccounts) { userWithAccounts ->
            GreetingCard(userWithAccounts)
        }
    }
}

public suspend fun insertSampleData(database: AppDatabase) {
    val user = UserModel(name = "LucasLucas", cpf = "08645119990")
    val account2 = AccountModel(userId = 3, real = 200.0, dollar = 50.0, euro = 30.0)
    database.userDao().insert(user)
    database.accountDao().insert(account2)
}
