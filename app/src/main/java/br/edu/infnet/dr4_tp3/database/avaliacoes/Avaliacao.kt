package br.edu.infnet.dr4_tp3.database.avaliacoes

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Avaliacao(
    @NonNull val idAvaliador: String,
    @NonNull val empresa: String,
    @NonNull val bairro: String,
    @NonNull val r1: Boolean,
    @NonNull val r2: Boolean,
    @NonNull val r3: Boolean,
    @NonNull val r4: Boolean,
    @NonNull val r5: Boolean,
    @NonNull val r6: Boolean,
    @PrimaryKey val id: Long? = null
): Serializable
