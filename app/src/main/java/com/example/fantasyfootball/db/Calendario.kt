package com.example.fantasyfootball.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Calendario",
    indices = [
        Index(value = ["Week_Number"]),
        Index(value = ["Week_Number","Squadra_Casa","Squadra_Ospite"], unique = true)
    ]
)
data class Calendario(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_Partita")
    val id: Int = 0,

    @ColumnInfo(name = "Week_Number")
    val weekNumber: Int,

    @ColumnInfo(name = "Squadra_Casa")
    val squadraCasa: String,

    @ColumnInfo(name = "Squadra_Ospite")
    val squadraOspite: String,

    @ColumnInfo(name = "Partita_Giocata")
    val partitaGiocata: Boolean = false,

    @ColumnInfo(name = "Risultato")
    val risultato: String? = null
)
