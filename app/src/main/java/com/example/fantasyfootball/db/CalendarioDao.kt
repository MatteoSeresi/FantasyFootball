package com.example.fantasyfootball.db


import androidx.room.*
import com.example.fantasyfootball.db.Calendario
import kotlinx.coroutines.flow.Flow

@Dao
interface CalendarioDao {
    @Query("SELECT * FROM Calendario WHERE Week_Number = :week ORDER BY ID_Partita")
    fun getByWeekFlow(week: Int): Flow<List<Calendario>>

    @Query("SELECT * FROM Calendario WHERE ID_Partita = :id")
    suspend fun getByIdOnce(id: Int): Calendario?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(match: Calendario): Long

    @Update
    suspend fun update(match: Calendario)
}
