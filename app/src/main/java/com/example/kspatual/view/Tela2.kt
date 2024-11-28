package com.example.kspatual.view

import android.content.Intent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kspatual.api.Cotacoes
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.viewmodel.UserListScreen
import kotlinx.coroutines.launch
import com.example.kspatual.api.getCotacoes

@Composable
fun Tela2(navController: NavController, database: AppDatabase) {
    val coroutineScope = rememberCoroutineScope()
    var cotacoes by remember { mutableStateOf<Cotacoes?>(null) }

    // Busca cotações ao entrar na tela
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            cotacoes = getCotacoes()
        }
    }

    val context = LocalContext.current // Contexto para uso no Intent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFcadae3))
    ) {
        // Animação dos blocos flutuantes
        Column(modifier = Modifier.fillMaxSize()) {
            FloatingBlocks(cotacoes)
        }

        // Conteúdo principal da tela
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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

            Spacer(modifier = Modifier.height(16.dp))

            // Bloco 2 - Lista de Usuários
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFcadae3))
                    .padding(16.dp)
            ) {
                UserListScreen(database)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bloco 3 - Botões
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
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
                Button(onClick = { navController.navigate("tela3") }) {
                    Text(text = "Realizar Depósito")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão de Compartilhamento
            Button(
                onClick = {
                    cotacoes?.let {
                        shareCotacoes(context, it) // Chama a função de compartilhamento
                    }
                }
            ) {
                Text(text = "Compartilhar Cotações")
            }
        }
    }
}

fun shareCotacoes(context: android.content.Context, cotacoes: Cotacoes) {
    val shareText = """
        Cotações Atualizadas:
        USD/BRL: ${cotacoes.usdToBrl}
        EUR/BRL: ${cotacoes.eurToBrl}
        BTC/BRL: ${cotacoes.btcToBrl}
    """.trimIndent()

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}

@Composable
fun FloatingBlocks(cotacoes: Cotacoes?) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offset by infiniteTransition.animateFloat(
        initialValue = -400f,
        targetValue = 450f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp)
            .height(50.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val cotacoesList = listOf(
            "USD: ${cotacoes?.usdToBrl ?: "Carregando..."}",
            "EUR: ${cotacoes?.eurToBrl ?: "Carregando..."}",
            "BTC: ${cotacoes?.btcToBrl ?: "Carregando..."}"
        )

        cotacoesList.forEach { texto ->
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .offset(x = offset.dp)
                    .background(Color.Blue, RoundedCornerShape(8.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = texto,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}
