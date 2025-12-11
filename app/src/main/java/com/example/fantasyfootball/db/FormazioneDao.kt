package com.example.fantasyfootball.db

import androidx.room.*
import com.example.fantasyfootball.db.Formazione
import kotlinx.coroutines.flow.Flow

@Dao
interface FormazioneDao {
    @Query("SELECT * FROM Formazione WHERE ID_Utente = :userId AND Numero_Week = :week")
    fun getForUserWeekFlow(userId: Int, week: Int): Flow<List<Formazione>>

    @Query("SELECT COUNT(*) FROM Formazione WHERE ID_Utente = :userId AND Numero_Week = :week")
    suspend fun countForUserWeek(userId: Int, week: Int): Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(formazione: Formazione)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(formazioni: List<Formazione>)

    @Delete
    suspend fun delete(formazione: Formazione)

    @Query("UPDATE Formazione SET Locked = 1 WHERE ID_Utente = :userId AND Numero_Week = :week")
    suspend fun lockFormation(userId: Int, week: Int)

    @Query("DELETE FROM Formazione WHERE ID_Utente = :userId AND Numero_Week = :week")
    suspend fun deleteFormationForUserWeek(userId: Int, week: Int)

    // metodi "once" (suspend) per leggere snapshot
    @Query("SELECT * FROM Formazione WHERE ID_Utente = :userId AND Numero_Week = :week")
    suspend fun getForUserWeekOnce(userId: Int, week: Int): List<Formazione>
}
