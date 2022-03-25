package br.edu.infnet.dr4_tp3.ui.cadastro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr4_tp3.database.avaliacoes.AvaliacaoDao

class CadastrarAvaliacaoViewModelFactory(private val avaliacaoDao: AvaliacaoDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CadastrarAvaliacaoViewModel::class.java)) {
            return CadastrarAvaliacaoViewModel(avaliacaoDao) as T
        }
        throw IllegalArgumentException("ViewModel incompatível")
    }
}
