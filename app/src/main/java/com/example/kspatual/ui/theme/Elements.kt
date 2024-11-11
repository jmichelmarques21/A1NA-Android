package com.example.kspatual.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.view.Tela1
import com.example.kspatual.view.Tela2
import com.example.kspatual.view.Tela3

fun navigateTo(navController: NavController, route: String) {
    navController.navigate(route)
}

@Composable
fun NavGrap(database: AppDatabase) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "tela1") {
        composable("tela1") { Tela1(navController = navController) }
        composable("tela2") { Tela2(navController = navController, database) }
        composable("tela3") { Tela3(navController = navController) }
    }
}

