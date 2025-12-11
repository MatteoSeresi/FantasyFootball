package com.example.fantasyfootball.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UtenteDao {
    // ritorni Flow<Utente?> (nullable) — evita problemi se non esiste
    @Query("SELECT * FROM Utente WHERE ID_Utente = :id")
    fun getByIdFlow(id: Int): Flow<Utente?>

    // suspend che ritorna Utente? (singolo risultato)
    @Query("SELECT * FROM Utente WHERE Email = :email LIMIT 1") // Usa il nome corretto della tua tabella

    suspend fun getByEmail(email: String): Utente?

    // insert: suspend che ritorna Long (id inserito)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(utente: Utente): Long

    // update: suspend che ritorna Int (numero di righe modificate)
    @Update
    suspend fun update(utente: Utente): Int

    // delete: suspend che ritorna Int (numero di righe cancellate)
    @Delete
    suspend fun delete(utente: Utente): Int
}
