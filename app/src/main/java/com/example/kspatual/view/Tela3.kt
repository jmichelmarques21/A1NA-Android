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
import com.example.kspatual.ui.theme.navigateTo

@Composable
fun Tela3(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Tela Verde", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navigateTo(navController, "tela1") }) {
                Text(text = "tela1")
            }
            Button(onClick = { navigateTo(navController, "tela2") }) {
                Text(text = "tela2")
            }
        }
    }
}
