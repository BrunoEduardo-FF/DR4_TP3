package br.edu.infnet.dr4_tp3.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.edu.infnet.dr4_tp3.R

class DashboardFragment : Fragment() {

    private lateinit var btnCadastrar: Button
    private lateinit var btnListar: Button
    private lateinit var btnDados: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        setupWidgets(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        btnCadastrar.setOnClickListener {
            navController.navigate(R.id.cadastrarAvaliacaoFragment)
        }
        btnListar.setOnClickListener {

        }
        btnDados.setOnClickListener {

        }

    }

    private fun setupWidgets(view: View) {
        btnCadastrar = view.findViewById(R.id.dashboard_fragment_btn_nova_avaliacao)
        btnListar = view.findViewById(R.id.dashboard_fragment_btn_avaliacoes_efetuadas)
        btnDados = view.findViewById(R.id.dashboard_fragment_btn_dados)
    }



}