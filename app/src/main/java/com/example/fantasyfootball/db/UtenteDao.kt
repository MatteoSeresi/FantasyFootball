package com.example.fantasyfootball.db


import androidx.room.*
import com.example.fantasyfootball.db.Utente
import kotlinx.coroutines.flow.Flow

@Dao
interface UtenteDao {
    @Query("SELECT * FROM Utente WHERE ID_Utente = :id")
    fun getByIdFlow(id: Int): Flow<Utente?>

    @Query("SELECT * FROM Utente WHERE Email = :email")
    suspend fun getByEmail(email: String): Utente?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(utente: Utente): Long

    @Update
    suspend fun update(utente: Utente)

    @Delete
    suspend fun delete(utente: Utente)
}
