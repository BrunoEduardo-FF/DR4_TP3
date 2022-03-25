package br.edu.infnet.dr4_tp3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.infnet.dr4_tp3.database.avaliacoes.Avaliacao
import br.edu.infnet.dr4_tp3.database.avaliacoes.AvaliacaoDao

@Database(entities = arrayOf(Avaliacao::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun avaliacaoDao(): AvaliacaoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}