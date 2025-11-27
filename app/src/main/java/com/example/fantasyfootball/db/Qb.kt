package com.example.fantasyfootball.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Qb",
    indices = [Index(value = ["Squadra"])]
)
data class Qb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_QB")
    val id: Int = 0,

    @ColumnInfo(name = "Nome")
    val nome: String,

    @ColumnInfo(name = "Squadra")
    val squadra: String,

    @ColumnInfo(name = "Stato")
    val stato: String
)
