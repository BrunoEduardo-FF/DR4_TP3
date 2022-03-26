package br.edu.infnet.dr4_tp3.ui.sintetizados

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr4_tp3.database.avaliacoes.AvaliacaoDao

class DadosSintetizadosViewModelFactory(private val avaliacaoDao: AvaliacaoDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DadosSintetizadosViewModel::class.java)) {
            return DadosSintetizadosViewModel(avaliacaoDao) as T
        }
        throw IllegalArgumentException("ViewModel incompatível")
    }
}