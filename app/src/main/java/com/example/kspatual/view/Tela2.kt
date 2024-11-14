package com.example.kspatual.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.ui.theme.navigateTo
import com.example.kspatual.viewmodel.UserListScreen

@Composable
fun Tela2(navController: NavController, database: AppDatabase) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFcadae3)) // Fundo cinza claro
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Centraliza verticalmente
    ) {
        // Bloco 1 - Cabeçalho "Meu Perfil"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFcadae3))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Meu Perfil",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
                )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaçamento entre os blocos

        // Bloco 2 - Saudações e saldos do usuário
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFcadae3))
                .padding(16.dp)
        ) {
            UserListScreen(database)
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaçamento entre os blocos

        // Bloco 3 - Botões
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            Button(onClick = { navigateTo(navController, "tela1") }) {
                Text(text = "Log Out")
            }
            Button(onClick = { navigateTo(navController, "tela3") }) {
                Text(text = "Realizar Depósito")
            }
        }
    }
}
