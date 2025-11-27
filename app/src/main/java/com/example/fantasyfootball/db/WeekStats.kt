package com.example.fantasyfootball.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Weekstats",
    foreignKeys = [
        ForeignKey(entity = Qb::class, parentColumns = ["ID_QB"], childColumns = ["ID_QB"], onDelete = ForeignKey.RESTRICT),
        ForeignKey(entity = Calendario::class, parentColumns = ["ID_Partita"], childColumns = ["ID_Partita"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["ID_Partita"]), Index(value = ["ID_QB"]), Index(value = ["ID_QB", "ID_Partita"], unique = true)]
)
data class WeekStats(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_Weekstat")
    val id: Int = 0,

    @ColumnInfo(name = "ID_QB")
    val idQb: Int,

    @ColumnInfo(name = "ID_Partita")
    val idPartita: Int,

    @ColumnInfo(name = "Punteggio_QB")
    val punteggioQb: Double
)
