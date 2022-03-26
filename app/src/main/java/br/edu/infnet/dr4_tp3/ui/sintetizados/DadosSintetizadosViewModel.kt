package br.edu.infnet.dr4_tp3.ui.sintetizados

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.infnet.dr4_tp3.database.avaliacoes.Avaliacao
import br.edu.infnet.dr4_tp3.database.avaliacoes.AvaliacaoDao
import br.edu.infnet.dr4_tp3.util.BoolConv
import br.edu.infnet.dr4_tp3.util.Criptografador
import kotlinx.coroutines.launch

class DadosSintetizadosViewModel(private val avaliacaoDao: AvaliacaoDao) : ViewModel() {
    private val _dados = MutableLiveData<List<Avaliacao>>()
    private val _dadosAgrupados = MutableLiveData<List<String>>()
    val dadosAgrupados: LiveData<List<String>> = _dadosAgrupados
    private val boolConv = BoolConv()
    private val criptografador = Criptografador()

    init {
        viewModelScope.launch {
            _dados.value = avaliacaoDao.list()
            _dadosAgrupados.value = listDadosAgrupadosPorBairro()
        }
    }

    fun getBairrosList(): List<String> {
        val bairros: MutableList<String> = mutableListOf()
        _dados.value?.forEach {
            if (criptografador.decipher(it.bairro) !in bairros) {
                bairros.add(criptografador.decipher(it.bairro))
            }
        }
        return bairros
    }

    fun listDadosAgrupadosPorBairro(): List<String> {
        var listaResultado: MutableList<String> = mutableListOf()
        getBairrosList().forEach { bairro ->
            var r1Total: Int = 0
            var r2Total: Int = 0
            var r3Total: Int = 0
            var r4Total: Int = 0
            var r5Total: Int = 0
            var r6Total: Int = 0
            var totAvaBairro: Int = 0
            _dados.value?.forEach { avaliacao ->
                if(bairro == criptografador.decipher(avaliacao.bairro)) {
                    r1Total += boolConv.boolToInt(avaliacao.r1)
                    r2Total += boolConv.boolToInt(avaliacao.r2)
                    r3Total += boolConv.boolToInt(avaliacao.r3)
                    r4Total += boolConv.boolToInt(avaliacao.r4)
                    r5Total += boolConv.boolToInt(avaliacao.r5)
                    r6Total += boolConv.boolToInt(avaliacao.r6)
                    totAvaBairro += 1
                }
            }
            var dadosString = "${bairro}\n\nPergunta 1:    $r1Total de $totAvaBairro        Pergunta 2:    $r2Total de $totAvaBairro\nPergunta 3:    $r3Total de $totAvaBairro" +
                    "        Pergunta 4:    $r4Total de $totAvaBairro\nPergunta 5:    $r5Total de $totAvaBairro        Pergunta 6:    $r6Total de $totAvaBairro"
            listaResultado.add(dadosString)
        }
        return listaResultado
    }


}