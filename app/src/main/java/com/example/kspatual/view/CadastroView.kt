package com.example.kspatual.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.model.UserModel
import kotlinx.coroutines.launch

@Composable
fun CadastroView(navController: NavController, database: AppDatabase) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFcadae3))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Criação de Conta", color = Color.Black)

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    val user = UserModel(name = nome, cpf = email) // Substitua "cpf" por um campo relevante
                    database.userDao().insert(user)
                    navController.navigate("tela1") // Volta para a tela inicial
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Cadastrar")
        }
    }
}
