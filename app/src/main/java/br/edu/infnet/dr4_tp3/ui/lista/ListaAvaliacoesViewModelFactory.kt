package br.edu.infnet.dr4_tp3.ui.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr4_tp3.database.avaliacoes.AvaliacaoDao

class ListaAvaliacoesViewModelFactory(private val avaliacaoDao: AvaliacaoDao, private val idUser: String)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListaAvaliacoesViewModel::class.java)) {
            return ListaAvaliacoesViewModel(avaliacaoDao, idUser) as T
        }
        throw IllegalArgumentException("ViewModel incompatível")
    }
}