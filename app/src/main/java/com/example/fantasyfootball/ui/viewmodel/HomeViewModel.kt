package com.example.fantasyfootball.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantasyfootball.db.AppDatabase
import com.example.fantasyfootball.db.repository.FFRepository
import com.example.fantasyfootball.db.Qb
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val repo = FFRepository(db)

    // esponiamo una StateFlow di lista di Qb (default: lista vuota)
    val qbs: StateFlow<List<Qb>> = repo.getAllQbFlow()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
