package com.ti4all.a1na

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ti4all.a1na.controller.ContaController
import com.ti4all.a1na.dao.ContaDao
import com.ti4all.a1na.ui.theme.A1NATheme
import com.ti4all.a1na.data.AppDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Acessando o Dao do banco de dados
        val contaDao = AppDatabase.getDatabase(applicationContext).contaDao()
        val contaController = ContaController(contaDao)

        setContent {
            A1NATheme {



            }
        }
    }
}
