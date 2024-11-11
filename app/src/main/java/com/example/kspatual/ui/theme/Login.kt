package com.example.kspatual.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kspatual.R

@Composable
fun login(navController: NavController){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.robber_entering_by_a_window_icon_icons_com_70631), contentDescription = "login",
            modifier = Modifier.size(200.dp))
        Text(text = "Welcome to KSPatual", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = "" , onValueChange = {}, label = {
            Text(text = "e-mail addrees", color = Color.White)
        })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = "" , onValueChange = {}, label ={
            Text(text = "password", color = Color.White)
        })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navigateTo(navController, "tela2") }) {
                Text(text = "Sing in")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navigateTo(navController, "tela2") }) {
                Text(text = "Sing up")
        }



    }
}