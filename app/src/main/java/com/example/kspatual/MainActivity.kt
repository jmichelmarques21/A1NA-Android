package com.example.kspatual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.model.AccountModel
import com.example.kspatual.model.UserModel
import com.example.kspatual.ui.theme.KSPatualTheme
import com.example.kspatual.view.Tela2
import com.example.kspatual.view.Tela3
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import com.example.kspatual.api.getCotacoes
import com.example.kspatual.ui.theme.login
import com.example.kspatual.view.CadastroView
import com.example.kspatual.view.LoginView
import com.example.kspatual.viewmodel.deposit
import com.example.kspatual.viewmodel.insertSampleData

class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user_database"
        ).build()

        lifecycleScope.launch {
            val user = UserModel(name = "Lucas Matheus", email = "teste@teste.com", senha = "teste" ,cpf = "08645112990")
            val account = AccountModel(userId = 1, real = 120.00, dollar = 120.00, euro = 100.0)
//            database.userDao().insert(user) // Insere o usuário
//            database.accountDao().insert(account) // Tenta inserir a conta sem garantir que o ID do usuário seja 1

//            insertSampleData(database)
//            val accountData = database.accountDao().get(2)
//            if (accountData != null) {
//                deposit(database, "dollar", 200.0, getCotacoes())
//            }
        }

        setContent {
            KSPatualTheme {
                // Adicionando o NavHost diretamente aqui
                AppNavHost(database)
            }
        }
    }
}

@Composable
fun AppNavHost(database: AppDatabase) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginView(navController) // Passa o banco de dados para a Tela2
        }

        composable("cadastro"){
            CadastroView(navController, database)
        }

        composable("tela2"){
            Tela2(navController, database)
        }

        composable("tela3") {
            Tela3(navController, database) // Exemplo para navegar para outra tela
        }

        // Adicione outras telas aqui
    }
}
