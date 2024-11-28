package com.example.kspatual.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.model.AccountModel
import kotlinx.coroutines.launch

@Composable
fun LoginView(navController: NavController, database: AppDatabase) {
    val coroutineScope = rememberCoroutineScope()

    // Estados para capturar email e senha
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var loginErro by remember { mutableStateOf(false) }

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
            // Campo de Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de Senha
            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Mensagem de erro, se necessário
            if (loginErro) {
                Text(text = "Email ou senha inválidos", color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Botão de Login
            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            val user = database.userDao().login(senha.trim(), email.trim())
                            if (user != null) {
                                loginErro = false
                                println("Usuário encontrado: ${user.name}, ID: ${user.id}")

                                // Verifica se o usuário já tem uma conta
                                val userAccounts = database.accountDao().getAccountsForUser(user.id)
                                if (userAccounts.isNullOrEmpty()) {
                                    // Cria uma nova conta se nenhuma for encontrada
                                    val account = AccountModel(userId = user.id, real = 100.0, dollar = 50.0, euro = 30.0)
                                    database.accountDao().insert(account)
                                    println("Conta criada para o usuário: ${user.name}")
                                } else {
                                    println("Usuário já possui uma conta.")
                                }

                                // Navegar para Tela2 com o userId
                                navController.navigate("tela2/${user.id}")
                            } else {
                                loginErro = true
                                println("Usuário não encontrado ou credenciais inválidas.")
                            }
                        } catch (e: Exception) {
                            loginErro = true
                            e.printStackTrace()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Botão para Cadastro
            Button(
                onClick = { navController.navigate("cadastro") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Cadastrar")
            }
        }
    }
}