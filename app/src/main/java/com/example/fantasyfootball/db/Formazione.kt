package com.example.fantasyfootball.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "Formazione",
    primaryKeys = ["ID_Utente", "Numero_Week", "ID_QB"],
)
data class Formazione(
    @ColumnInfo(name = "ID_Utente")
    val idUtente: Int,

    @ColumnInfo(name = "Numero_Week")
    val numeroWeek: Int,

    @ColumnInfo(name = "ID_QB")
    val idQb: Int,

    @ColumnInfo(name = "Punteggio_Qb")
    val punteggioQb: Double? = null,

    @ColumnInfo(name = "Locked")
    val locked: Boolean = false
)
