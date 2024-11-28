package com.example.kspatual.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kspatual.api.getCotacoes
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.viewmodel.deposit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Tela3(navController: NavController, database: AppDatabase, userId: Int) {
    var valorDeposito by remember { mutableStateOf("") }

    // Função para realizar o depósito
    fun fazerDeposito(moeda: String, valor: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Busca a conta associada ao usuário
                val account = database.accountDao().getAccountsForUser(userId).firstOrNull()
                if (account != null) {
                    val cotacoes = getCotacoes()

                    deposit(database,moeda,valor, cotacoes,account)

                    CoroutineScope(Dispatchers.Main).launch {
                        navController.popBackStack()
                    }
                } else {
                    println("Conta não encontrada para o usuário ID: $userId")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFcadae3)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Faça um depósito",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de entrada para o valor do depósito
            TextField(
                value = valorDeposito,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() || it == '.' }) {
                        valorDeposito = newValue
                    }
                },
                placeholder = { Text(text = "Insira o valor") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botões para selecionar a moeda e realizar o depósito
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("real", "euro", "dollar").forEach { moeda ->
                    Button(onClick = {
                        val valor = valorDeposito.toDoubleOrNull()
                        if (valor != null && valor > 0) {
                            fazerDeposito(moeda, valor)
                        } else {
                            println("Por favor, insira um valor válido.")
                        }
                    }) {
                        Text(text = moeda)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão de cancelar depósito
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Cancelar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botões de navegação
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true } // Remove todas as telas anteriores
                        }
                    }
                ) {
                    Text(text = "Log Out")
                }
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "Meu Perfil")
                }
            }
        }
    }
}
