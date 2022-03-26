package br.edu.infnet.dr4_tp3.ui.lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.infnet.dr4_tp3.database.avaliacoes.Avaliacao
import br.edu.infnet.dr4_tp3.database.avaliacoes.AvaliacaoDao
import kotlinx.coroutines.launch

class ListaAvaliacoesViewModel(private val avaliacaoDao: AvaliacaoDao, private val idUser: String)
    : ViewModel() {
    private val _avaliacoes = MutableLiveData<List<Avaliacao>>()
    val avaliacoes: LiveData<List<Avaliacao>> = _avaliacoes

    init {
        viewModelScope.launch {
            _avaliacoes.value = avaliacaoDao.listFromUser(idUser)
        }
    }




}