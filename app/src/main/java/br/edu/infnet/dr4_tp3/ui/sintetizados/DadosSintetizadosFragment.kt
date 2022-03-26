package br.edu.infnet.dr4_tp3.ui.sintetizados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.edu.infnet.dr4_tp3.R
import br.edu.infnet.dr4_tp3.database.AppDatabase

class DadosSintetizadosFragment : Fragment() {

    private lateinit var viewModel: DadosSintetizadosViewModel
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dados_sintetizados, container, false)
        setupViewModel()
        listView = view.findViewById(R.id.fragment_dados_sintetizados_listview)

        return view
    }

    private fun setupViewModel() {
        val appDatabase = AppDatabase.getInstance(requireActivity().applicationContext)
        viewModel = ViewModelProvider(
            this,
            DadosSintetizadosViewModelFactory(appDatabase.avaliacaoDao())
        )
            .get(DadosSintetizadosViewModel::class.java)
        viewModel.dadosAgrupados.observe(viewLifecycleOwner) {
            if(!it.isNullOrEmpty()) {
                listView.adapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_list_item_1, viewModel.listDadosAgrupadosPorBairro() )
            }
        }
    }


}