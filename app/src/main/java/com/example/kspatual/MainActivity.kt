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
import com.example.kspatual.view.CadastroView
import com.example.kspatual.view.LoginView

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
            LoginView(navController, database)
        }

        composable("cadastro") {
            CadastroView(navController, database)
        }

        composable("tela2/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            if (userId != null) {
                Tela2(navController, database, userId)
            }
        }

        composable("tela3/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            if (userId != null) {
                Tela3(navController, database, userId)
            }
        }
    }
}