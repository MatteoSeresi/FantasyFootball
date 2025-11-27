package com.example.fantasyfootball.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fantasyfootball.db.Qb
import com.example.fantasyfootball.ui.viewmodel.HomeViewModel
import com.example.fantasyfootball.ui.viewmodel.HomeViewModelFactory

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    val vm: HomeViewModel = viewModel(factory = HomeViewModelFactory(application))
    val qbs by vm.qbs.collectAsState()

    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        if (qbs.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Nessun QB trovato (tabella vuota o ancora caricamento).", modifier = Modifier.padding(8.dp))
                Spacer(modifier = Modifier.height(12.dp))
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(contentPadding = PaddingValues(12.dp)) {
                items(qbs) { qb: Qb ->
                    QbRow(qb = qb, modifier = Modifier.padding(vertical = 6.dp))
                }
            }
        }
    }
}

@Composable
private fun QbRow(qb: Qb, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = qb.nome, style = MaterialTheme.typography.titleMedium)
            Text(text = "Squadra: ${qb.squadra}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Stato: ${qb.stato}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
