package br.edu.infnet.dr4_tp3.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.infnet.dr4_tp3.R

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel
    private lateinit var txtEmail: EditText
    private lateinit var txtSenha: EditText
    private lateinit var btnAcessar: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        inicializaWidgets(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAcessar.setOnClickListener {
            findNavController().navigate(R.id.dashboardFragment)
        }
    }

    private fun inicializaWidgets(view: View) {
        txtEmail = view.findViewById(R.id.sign_in_fragment_txt_email)
        txtSenha = view.findViewById(R.id.sign_in_fragment_txt_senha)
        btnAcessar = view.findViewById(R.id.sign_in_fragment_btn_acessar)
    }


}