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
fun Tela2(navController: NavController,database: AppDatabase) {
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
            UserListScreen(database)
            Text(
                text = "Meu Perfil",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
                )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
            Button(onClick = { navigateTo(navController, "tela1") }) {
                Text(text = "Log Out")
            }
            Button(onClick = { navigateTo(navController, "tela3") }) {
                Text(text = "Realizar depósito")
            }
        }}
    }
}
