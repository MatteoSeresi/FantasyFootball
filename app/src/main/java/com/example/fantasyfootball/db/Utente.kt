package com.example.fantasyfootball.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Utente")
data class Utente(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_Utente")
    val id: Int = 0,

    @ColumnInfo(name = "Email")
    val email: String,

    @ColumnInfo(name = "Username")
    val username: String,

    @ColumnInfo(name = "Password")
    val password: String,

    @ColumnInfo(name = "Nome_Team")
    val nomeTeam: String? = null
)
