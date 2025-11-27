package com.example.fantasyfootball.db


import androidx.room.*
import com.example.fantasyfootball.db.Qb
import kotlinx.coroutines.flow.Flow

@Dao
interface QbDao {
    @Query("SELECT * FROM Qb ORDER BY Nome")
    fun getAllFlow(): Flow<List<Qb>>

    @Query("SELECT * FROM Qb WHERE ID_QB = :id")
    suspend fun getById(id: Int): Qb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qb: Qb): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(qbs: List<Qb>)

    @Delete
    suspend fun delete(qb: Qb)
}
