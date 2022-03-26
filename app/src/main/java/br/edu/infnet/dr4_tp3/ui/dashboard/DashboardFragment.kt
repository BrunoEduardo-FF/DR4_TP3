package br.edu.infnet.dr4_tp3.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import br.edu.infnet.dr4_tp3.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {

    private lateinit var btnCadastrar: Button
    private lateinit var btnListar: Button
    private lateinit var btnDados: Button
    private lateinit var btnSair: Button
    private lateinit var navController: NavController
    private var auth = Firebase.auth

    override fun onStart() {
        super.onStart()
        exitIfNoUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        setupWidgets(view)
        navController = findNavController()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        btnCadastrar.setOnClickListener {
            navController.navigate(R.id.cadastrarAvaliacaoFragment)
        }
        btnListar.setOnClickListener {
            navController.navigate(R.id.listaAvaliacoesFragment)
        }
        btnDados.setOnClickListener {
            navController.navigate(R.id.dadosSintetizadosFragment)
        }
        btnSair.setOnClickListener {
            auth.signOut()
            exitIfNoUser()
        }
    }

    private fun setupWidgets(view: View) {
        btnCadastrar = view.findViewById(R.id.dashboard_fragment_btn_nova_avaliacao)
        btnListar = view.findViewById(R.id.dashboard_fragment_btn_avaliacoes_efetuadas)
        btnDados = view.findViewById(R.id.dashboard_fragment_btn_dados)
        btnSair = view.findViewById(R.id.dashboard_fragment_btn_sair)
    }

    private fun exitIfNoUser() {
        if(auth.currentUser == null) {
            navController.navigate(R.id.signInFragment)
        }
    }

}