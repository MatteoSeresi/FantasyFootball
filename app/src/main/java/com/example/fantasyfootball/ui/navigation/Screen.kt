package com.example.fantasyfootball.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    object Login : Screen("login", "Login")
    object Register : Screen("register", "Registrazione")
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Team : Screen("team", "Squadra", Icons.Default.EmojiEvents) // icona provvisoria
    object Calendar : Screen("calendar", "Calendario", Icons.Default.CalendarToday)
    object Stats : Screen("stats", "Statistiche", Icons.Default.BarChart)
    object Ranking : Screen("ranking", "Classifica", Icons.Default.Star) // icona provvisoria
    object Profile : Screen("profile", "Profilo", Icons.Default.Person)
}
