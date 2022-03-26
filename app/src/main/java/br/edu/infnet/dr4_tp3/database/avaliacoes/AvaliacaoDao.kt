package br.edu.infnet.dr4_tp3.database.avaliacoes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AvaliacaoDao {

    @Insert
    suspend fun insert(avaliacao: Avaliacao): Long

    @Query("SELECT * FROM avaliacao WHERE idAvaliador == :idAvaliador")
    suspend fun listFromUser(idAvaliador: String): List<Avaliacao>

    @Query("SELECT * FROM avaliacao")
    suspend fun list(): List<Avaliacao>

}