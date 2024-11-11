package com.example.kspatual.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.ui.theme.navigateTo
import com.example.kspatual.viewmodel.UserListScreen

@Composable
fun Tela2(navController: NavController,database: AppDatabase) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UserListScreen(database)
            Text(text = "Tela Vermelha", color = Color.Black)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navigateTo(navController, "tela1") }) {
                Text(text = "tela1")
            }
            Button(onClick = { navigateTo(navController, "tela3") }) {
                Text(text = "tela3")
            }
        }
    }
}
