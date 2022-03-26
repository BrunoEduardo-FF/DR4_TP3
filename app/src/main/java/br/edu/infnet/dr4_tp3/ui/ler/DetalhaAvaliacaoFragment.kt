package br.edu.infnet.dr4_tp3.ui.ler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.edu.infnet.dr4_tp3.R
import br.edu.infnet.dr4_tp3.database.avaliacoes.Avaliacao
import br.edu.infnet.dr4_tp3.util.BoolConv
import br.edu.infnet.dr4_tp3.util.Criptografador

class DetalhaAvaliacaoFragment : Fragment() {

    private lateinit var lblEmpresa: TextView
    private lateinit var lblBairro: TextView
    private lateinit var lblR1: TextView
    private lateinit var lblR2: TextView
    private lateinit var lblR3: TextView
    private lateinit var lblR4: TextView
    private lateinit var lblR5: TextView
    private lateinit var lblR6: TextView
    private val cripto = Criptografador()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalha_avaliacao, container, false)
        setupWidgets(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateWidgets()

    }

    private fun populateWidgets() {
        val boolConv = BoolConv()
        val avaliacao =
            arguments?.getSerializable(getString(R.string.ARGUMENT_AVALIACAO)) as Avaliacao
        var empresa = cripto.decipher(avaliacao.empresa)
        var bairro = cripto.decipher(avaliacao.bairro)
        var r1 = boolConv.boolToString(avaliacao.r1)
        var r2 = boolConv.boolToString(avaliacao.r2)
        var r3 = boolConv.boolToString(avaliacao.r3)
        var r4 = boolConv.boolToString(avaliacao.r4)
        var r5 = boolConv.boolToString(avaliacao.r5)
        var r6 = boolConv.boolToString(avaliacao.r6)
        lblEmpresa.text = empresa
        lblBairro.text = bairro
        lblR1.text = r1
        lblR2.text = r2
        lblR3.text = r3
        lblR4.text = r4
        lblR5.text = r5
        lblR6.text = r6
    }

    private fun setupWidgets(view: View) {
        lblEmpresa = view.findViewById(R.id.fragment_detalha_avaliacao_lbl_nome_empresa)
        lblBairro = view.findViewById(R.id.fragment_detalha_avaliacao_lbl_bairro)
        lblR1 = view.findViewById(R.id.fragment_detalha_avaliacao_lbl_resp1)
        lblR2 = view.findViewById(R.id.fragment_detalha_avaliacao_lbl_resp2)
        lblR3 = view.findViewById(R.id.fragment_detalha_avaliacao_lbl_resp3)
        lblR4 = view.findViewById(R.id.fragment_detalha_avaliacao_lbl_resp4)
        lblR5 = view.findViewById(R.id.fragment_detalha_avaliacao_lbl_resp5)
        lblR6 = view.findViewById(R.id.fragment_detalha_avaliacao_lbl_resp6)
    }

}