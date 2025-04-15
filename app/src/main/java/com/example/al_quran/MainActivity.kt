package com.example.al_quran

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.al_quran.ViewModel.QuranViewModel
import com.example.al_quran.ViewModel.SurahDetailViewModel
import com.example.al_quran.ui.SurahDetailScreen
import com.example.al_quran.ui.SurahList

class MainActivity : ComponentActivity() {
    private val quranViewModel: QuranViewModel by viewModels()
    private val surahDetailViewModel: SurahDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "surahList") {
                    composable("surahList") {
                        SurahList(viewModel = quranViewModel) { surahId ->
                            navController.navigate("surahDetail/$surahId")
                        }
                    }

                    composable(
                        "surahDetail/{surahId}",
                        arguments = listOf(navArgument("surahId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val surahId = backStackEntry.arguments?.getInt("surahId") ?: 1
                        SurahDetailScreen(surahId = surahId, viewModel = surahDetailViewModel)
                    }
                }
            }
        }
    }
}