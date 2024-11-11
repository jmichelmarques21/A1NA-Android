package com.example.kspatual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.kspatual.dao.AccountDao
import com.example.kspatual.data.AppDatabase
import com.example.kspatual.model.AccountModel
import com.example.kspatual.model.UserModel
import com.example.kspatual.ui.theme.KSPatualTheme
import com.example.kspatual.ui.theme.NavGrap
import com.example.kspatual.view.Tela1
import com.example.kspatual.view.Tela2
import com.example.kspatual.view.Tela3
import com.example.kspatual.viewmodel.*
import kotlinx.coroutines.launch

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
            val user = UserModel(name = "Lucas Matheus", cpf = "08645112990")
            val accont = AccountModel(userId = 1 , real = 120.00, dollar = 120.00, euro = 100.0)
            //database.userDao().insert(user)
            //database.accountDao().insert(accont)
            //insertSampleData(database)
            //val account = database.accountDao().get(2)
            //if (account != null) {

                //deposit(database, account, "dollar", 200.0, 5.6)
            //}
        }

        setContent {
            KSPatualTheme {
                NavGrap(database)
                //UserListScreen(database = database)
            }
        }
    }


}







