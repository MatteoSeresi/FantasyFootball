package com.example.fantasyfootball.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fantasyfootball.db.*

@Database(
    entities = [Utente::class, Qb::class, Calendario::class, WeekStats::class, Formazione::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun utenteDao(): com.example.fantasyfootball.db.UtenteDao
    abstract fun qbDao(): com.example.fantasyfootball.db.QbDao
    abstract fun calendarioDao(): com.example.fantasyfootball.db.CalendarioDao
    abstract fun weekStatsDao(): com.example.fantasyfootball.db.WeekStatsDao
    abstract fun formazioneDao(): com.example.fantasyfootball.db.FormazioneDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fantasyfootball.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
