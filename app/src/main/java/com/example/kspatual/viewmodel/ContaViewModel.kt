package com.example.kspatual.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kspatual.dao.UserDao
import com.example.kspatual.data.UserWithAccounts
import com.example.kspatual.model.AccountModel

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
