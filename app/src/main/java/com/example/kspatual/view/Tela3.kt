package com.example.kspatual.view

import android.annotation.SuppressLint
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
import com.example.kspatual.ui.theme.navigateTo
import com.example.kspatual.viewmodel.deposit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Tela3(navController: NavController, database: AppDatabase) {
    // Variáveis para armazenar o valor do depósito e a moeda selecionada
    var valorDeposito by remember { mutableStateOf("") }
    var moedaSelecionada by remember { mutableStateOf("USD") } // Valor padrão, pode ser alterado.

    // Função que será executada ao clicar nos botões para fazer o depósito
    fun fazerDepositoComCotacoes(moeda: String, valor: Double) {
        // Executa a função assíncrona de depósito, passando a moeda e o valor
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Chama getCotacoes dentro de uma corrotina
                val cotacoes = getCotacoes()

                // Chama a função de depósito com as cotações obtidas
                deposit(database, moeda, valor, cotacoes)

                // Navegar para o perfil após o depósito
                CoroutineScope(Dispatchers.Main).launch {
                    navigateTo(navController, "tela2")
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
                color = Color(0xff000000),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de entrada numérico para o valor do depósito
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
                        // Agora, a lógica de depósito é executada aqui através da função
                        val valor = valorDeposito.toDoubleOrNull()
                        if (valor != null && valor > 0) {
                            fazerDepositoComCotacoes(moeda, valor) // Chamando a função criada
                        } else {
                            // Exibir mensagem de erro para o usuário
                            println("Por favor, insira um valor válido.")
                        }
                    }) {
                        Text(text = moeda)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botões de navegação
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { navigateTo(navController, "tela1") }) {
                    Text(text = "Log Out")
                }
                Button(onClick = { navigateTo(navController, "tela2") }) {
                    Text(text = "Meu Perfil")
                }
            }
        }
    }
}
