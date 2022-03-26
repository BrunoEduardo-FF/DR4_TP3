package br.edu.infnet.dr4_tp3.ui.lista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.dr4_tp3.R
import br.edu.infnet.dr4_tp3.adapter.AvaliacoesAdapter
import br.edu.infnet.dr4_tp3.database.AppDatabase
import br.edu.infnet.dr4_tp3.database.avaliacoes.Avaliacao
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ListaAvaliacoesFragment : Fragment() {

    private lateinit var viewModel: ListaAvaliacoesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var idUser: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_avaliacoes, container, false)
        idUser = Firebase.auth.currentUser!!.uid

        recyclerView = view.findViewById(R.id.fragment_lista_avaliacoes_recyclerview)

        setupViewModel()
        return view
    }

    private fun setupViewModel() {
        val appDatabase = AppDatabase.getInstance(requireActivity().applicationContext)
        viewModel = ViewModelProvider(
            this,
            ListaAvaliacoesViewModelFactory(appDatabase.avaliacaoDao(), idUser)
        )
            .get(ListaAvaliacoesViewModel::class.java)

        viewModel.avaliacoes.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty())
                recyclerView.adapter = AvaliacoesAdapter(it, this::detalhaAvaliacao)
        }
    }

    fun detalhaAvaliacao(avaliacao: Avaliacao) {
        findNavController().navigate(
            R.id.detalhaAvaliacaoFragment,
            bundleOf(getString(R.string.ARGUMENT_AVALIACAO) to avaliacao)
        )
    }

}