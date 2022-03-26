package br.edu.infnet.dr4_tp3.ui.cadastro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr4_tp3.R
import br.edu.infnet.dr4_tp3.database.AppDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastrarAvaliacaoFragment : Fragment() {

    private lateinit var viewModel: CadastrarAvaliacaoViewModel
    private lateinit var nomeEmpresa: EditText
    private lateinit var bairro: EditText
    private lateinit var btnCadastrar: Button
    private lateinit var rdgrp1: RadioGroup
    private lateinit var rdgrp2: RadioGroup
    private lateinit var rdgrp3: RadioGroup
    private lateinit var rdgrp4: RadioGroup
    private lateinit var rdgrp5: RadioGroup
    private lateinit var rdgrp6: RadioGroup
    private lateinit var rdBtnP1O1: RadioButton
    private lateinit var rdBtnP1O2: RadioButton
    private lateinit var rdBtnP2O1: RadioButton
    private lateinit var rdBtnP2O2: RadioButton
    private lateinit var rdBtnP3O1: RadioButton
    private lateinit var rdBtnP3O2: RadioButton
    private lateinit var rdBtnP4O1: RadioButton
    private lateinit var rdBtnP4O2: RadioButton
    private lateinit var rdBtnP5O1: RadioButton
    private lateinit var rdBtnP5O2: RadioButton
    private lateinit var rdBtnP6O1: RadioButton
    private lateinit var rdBtnP6O2: RadioButton
    private lateinit var userId: String
    private var user: FirebaseUser? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cadastrar_avaliacao, container, false)
        setupUser()
        setupViewModel()
        setupWidgets(view)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCadastrar.setOnClickListener {
            cadastramento()
        }
    }

    private fun cadastramento() {
        if (!haCampoVazio()) {
            var r1 = false
            var r2 = false
            var r3 = false
            var r4 = false
            var r5 = false
            var r6 = false
            if (rdgrp1.checkedRadioButtonId == rdBtnP1O1.id) r1 = true
            if (rdgrp2.checkedRadioButtonId == rdBtnP2O1.id) r2 = true
            if (rdgrp3.checkedRadioButtonId == rdBtnP3O1.id) r3 = true
            if (rdgrp4.checkedRadioButtonId == rdBtnP4O1.id) r4 = true
            if (rdgrp5.checkedRadioButtonId == rdBtnP5O1.id) r5 = true
            if (rdgrp6.checkedRadioButtonId == rdBtnP6O1.id) r6 = true

            //Log.d("Teste de avaliacao",userId)
            viewModel.cadastrarAvaliacao(userId,
                nomeEmpresa.text.toString(),
                bairro.text.toString(), r1, r2, r3, r4, r5, r6)
                makeToast(getString(R.string.avaliacao_cadastrada))

        } else {
            makeToast(getString(R.string.ha_campos_vazios))
        }
    }

    private fun haCampoVazio(): Boolean {
        if (nomeEmpresa.text.isNullOrBlank()) return true
        if (bairro.text.isNullOrBlank()) return true
        if (userId.isNullOrBlank()) return true
        if (rdgrp1.checkedRadioButtonId == -1) return true
        if (rdgrp2.checkedRadioButtonId == -1) return true
        if (rdgrp3.checkedRadioButtonId == -1) return true
        if (rdgrp4.checkedRadioButtonId == -1) return true
        if (rdgrp5.checkedRadioButtonId == -1) return true
        if (rdgrp6.checkedRadioButtonId == -1) return true
        return false
    }

    private fun setupViewModel() {
        val appDatabase = AppDatabase.getInstance(requireContext().applicationContext)
        val avaliacaoDao = appDatabase.avaliacaoDao()
        val vmFactory = CadastrarAvaliacaoViewModelFactory(avaliacaoDao)
        viewModel = ViewModelProvider(this, vmFactory).get(CadastrarAvaliacaoViewModel::class.java)
    }

    private fun setupWidgets(view: View) {
        nomeEmpresa = view.findViewById(R.id.fragment_cadastrar_avaliacao_txt_nome_empresa)
        bairro = view.findViewById(R.id.fragment_cadastrar_avaliacao_txt_bairro)
        btnCadastrar = view.findViewById(R.id.fragment_cadastrar_avaliacao_btn_cadastrar)
        rdgrp1 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdgroup1)
        rdgrp2 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdgroup2)
        rdgrp3 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdgroup3)
        rdgrp4 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdgroup4)
        rdgrp5 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdgroup5)
        rdgrp6 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdgroup6)
        rdBtnP1O1 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta1_opcao1)
        rdBtnP1O2 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta1_opcao2)
        rdBtnP2O1 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta2_opcao1)
        rdBtnP2O2 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta2_opcao2)
        rdBtnP3O1 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta3_opcao1)
        rdBtnP3O2 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta3_opcao2)
        rdBtnP4O1 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta4_opcao1)
        rdBtnP4O2 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta4_opcao2)
        rdBtnP5O1 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta5_opcao1)
        rdBtnP5O2 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta5_opcao2)
        rdBtnP6O1 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta6_opcao1)
        rdBtnP6O2 = view.findViewById(R.id.fragment_cadastrar_avaliacao_rdbtn_pergunta6_opcao2)
    }

    private fun setupUser() {
        user = Firebase.auth.currentUser
        userId = user?.uid.toString()
    }

    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}