package br.edu.infnet.dr4_tp3.ui.cadastro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.infnet.dr4_tp3.database.avaliacoes.Avaliacao
import br.edu.infnet.dr4_tp3.database.avaliacoes.AvaliacaoDao
import br.edu.infnet.dr4_tp3.util.Criptografador
import kotlinx.coroutines.launch

class CadastrarAvaliacaoViewModel(private val avaliacaoDao: AvaliacaoDao) : ViewModel() {
    private val criptografador: Criptografador = Criptografador()

    fun cadastrarAvaliacao(
        userId: String, nomeEmpresa: String, bairro: String,
        r1: Boolean, r2: Boolean, r3: Boolean,
        r4: Boolean, r5: Boolean, r6: Boolean
    ) {
        val nomeEmpresaCripto = criptografador.cipher(nomeEmpresa)
        val bairroCripto = criptografador.cipher(bairro)
        viewModelScope.launch {
            avaliacaoDao.insert(Avaliacao(userId, nomeEmpresaCripto, bairroCripto, r1, r2, r3, r4, r5, r6))
        }
    }


}