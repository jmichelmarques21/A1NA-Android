package com.example.kspatual.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.view.CadastroView
import com.example.kspatual.view.LoginView
import com.example.kspatual.view.Tela2
import com.example.kspatual.view.Tela3

fun navigateTo(navController: NavController, route: String, userId: Int? = null) {
    if (userId != null) {
        navController.navigate("$route/$userId")
    } else {
        navController.navigate(route)
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
