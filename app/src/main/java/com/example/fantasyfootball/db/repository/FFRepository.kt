package com.example.fantasyfootball.db.repository

import com.example.fantasyfootball.db.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FFRepository(
    private val db: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val utenteDao = db.utenteDao()
    private val qbDao = db.qbDao()
    private val calendarioDao = db.calendarioDao()
    private val weekStatsDao = db.weekStatsDao()
    private val formazioneDao = db.formazioneDao()

    // ---------- Utente ----------
    fun getUtenteFlow(id: Int): Flow<Utente?> = utenteDao.getByIdFlow(id)

    suspend fun getUtenteByEmail(email: String): Utente? = withContext(ioDispatcher) {
        utenteDao.getByEmail(email)
    }

    suspend fun insertUtente(utente: Utente): Long = withContext(ioDispatcher) {
        utenteDao.insert(utente)
    }

    suspend fun updateUtente(utente: Utente) = withContext(ioDispatcher) {
        utenteDao.update(utente)
    }

    suspend fun deleteUtente(utente: Utente) = withContext(ioDispatcher) {
        utenteDao.delete(utente)
    }

    // ---------- QB ----------
    fun getAllQbFlow(): Flow<List<Qb>> = qbDao.getAllFlow()

    suspend fun insertQb(qb: Qb) = withContext(ioDispatcher) {
        qbDao.insert(qb)
    }

    suspend fun insertAllQb(qbs: List<Qb>) = withContext(ioDispatcher) {
        qbDao.insertAll(qbs)
    }

    // ---------- Calendario ----------
    fun getMatchesForWeekFlow(week: Int): Flow<List<Calendario>> = calendarioDao.getByWeekFlow(week)

    suspend fun insertMatch(match: Calendario) = withContext(ioDispatcher) {
        calendarioDao.insert(match)
    }

    suspend fun getMatchByIdOnce(id: Int): Calendario? = withContext(ioDispatcher) {
        calendarioDao.getByIdOnce(id)
    }

    // ---------- WeekStats ----------
    fun getWeekStatsForPartitaFlow(partitaId: Int): Flow<List<WeekStats>> =
        weekStatsDao.getByPartitaFlow(partitaId)

    fun getWeekStatsByQbFlow(qbId: Int): Flow<List<WeekStats>> =
        weekStatsDao.getByQbFlow(qbId)

    suspend fun setWeekStat(stats: WeekStats) = withContext(ioDispatcher) {
        weekStatsDao.insert(stats)
    }

    // ---------- Formazione (regole di business) ----------
    /**
     * Crea/aggiorna la formazione per un utente/week.
     * Controlli:
     * - esattamente 3 QB
     * - no duplicati
     * - se esiste formazione lockata -> blocca la modifica
     */
    suspend fun createOrReplaceFormazione(userId: Int, week: Int, qbIds: List<Int>) = withContext(ioDispatcher) {
        require(qbIds.size == 3) { "Devi selezionare esattamente 3 QB" }
        require(qbIds.toSet().size == 3) { "QB duplicati non permessi" }

        val existing = formazioneDao.getForUserWeekOnce(userId, week)
        if (existing.any { it.locked }) {
            throw IllegalStateException("Formazione già bloccata per questa week")
        }

        // cancelliamo eventuale formazione precedente e inseriamo la nuova
        if (existing.isNotEmpty()) {
            formazioneDao.deleteFormationForUserWeek(userId, week)
        }

        val forms = qbIds.map { qbId ->
            Formazione(
                idUtente = userId,
                numeroWeek = week,
                idQb = qbId,
                punteggioQb = null,
                locked = false
            )
        }
        formazioneDao.insertAll(forms)
    }

    suspend fun getFormazioneOnce(userId: Int, week: Int) = withContext(ioDispatcher) {
        formazioneDao.getForUserWeekOnce(userId, week)
    }

    fun getFormazioneFlow(userId: Int, week: Int): Flow<List<Formazione>> =
        formazioneDao.getForUserWeekFlow(userId, week)

    suspend fun lockFormazione(userId: Int, week: Int) = withContext(ioDispatcher) {
        formazioneDao.lockFormation(userId, week)
    }

    /**
     * Calcola il punteggio totale della formazione sommando i punteggi reali (weekstats)
     */
    suspend fun calculateFormazioneScore(userId: Int, week: Int): Double = withContext(ioDispatcher) {
        val formation = formazioneDao.getForUserWeekOnce(userId, week)
        var total = 0.0
        for (f in formation) {
            val stats = weekStatsDao.getByQbOnce(f.idQb)
            val relevant = stats.filter { stat ->
                val partita = calendarioDao.getByIdOnce(stat.idPartita)
                partita?.weekNumber == week
            }
            total += relevant.sumOf { it.punteggioQb }
        }
        total
    }
}
