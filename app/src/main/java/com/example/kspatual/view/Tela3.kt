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
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.ui.theme.navigateTo

@Composable
fun Tela3(navController: NavController, database: AppDatabase) {
    // Variável para armazenar o valor do depósito
    var valorDeposito by remember { mutableStateOf("") }

    // Função para atualizar o saldo no banco de dados
    fun confirmarDeposito() {
        val valor = valorDeposito.toFloatOrNull()/*
        if (valor != null && valor > 0) {
            CoroutineScope(Dispatchers.IO).launch {
                // Obter o saldo atual do usuário e adicionar o valor do depósito
                val usuario = database.userDao().getAllUsers()
                usuario.saldo += valor
                database.userDao().updateUsuario(usuario)
                */
                // Navegar para a tela "Meu Perfil" após o depósito
                navigateTo(navController, "tela2")
            //}
        //}*/
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

            // Botão para confirmar o depósito
            Button(onClick = { confirmarDeposito() }) {
                Text(text = "Confirmar Depósito")
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
