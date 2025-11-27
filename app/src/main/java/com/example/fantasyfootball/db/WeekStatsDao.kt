package com.example.fantasyfootball.db

import androidx.room.*
import com.example.fantasyfootball.db.WeekStats
import kotlinx.coroutines.flow.Flow

@Dao
interface WeekStatsDao {
    @Query("SELECT * FROM Weekstats WHERE ID_Partita = :partitaId")
    fun getByPartitaFlow(partitaId: Int): Flow<List<WeekStats>>

    @Query("SELECT * FROM Weekstats WHERE ID_QB = :qbId")
    fun getByQbFlow(qbId: Int): Flow<List<WeekStats>>

    @Query("SELECT * FROM Weekstats WHERE ID_QB = :qbId")
    suspend fun getByQbOnce(qbId: Int): List<WeekStats>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stats: WeekStats): Long

    @Update
    suspend fun update(stats: WeekStats)
}
