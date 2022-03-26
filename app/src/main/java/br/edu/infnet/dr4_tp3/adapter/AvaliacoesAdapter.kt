package br.edu.infnet.dr4_tp3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.dr4_tp3.R
import br.edu.infnet.dr4_tp3.database.avaliacoes.Avaliacao
import br.edu.infnet.dr4_tp3.util.Criptografador

class AvaliacoesAdapter (val avaliacoes: List<Avaliacao>,
                         val detalhaAvaliacao: (Avaliacao) -> Unit)
    : RecyclerView.Adapter<AvaliacoesAdapter.AvaliacoesAdapterViewHolder>() {

    private val criptografador = Criptografador()

    class AvaliacoesAdapterViewHolder(itemView: View)
        :RecyclerView.ViewHolder(itemView) {
            val lblNomeEmpresa = itemView
                .findViewById<TextView>(R.id.recycler_avaliacoes_item_lbl_nome_empresa)
            val lblBairro = itemView
                .findViewById<TextView>(R.id.recycler_avaliacoes_item_lbl_bairro)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : AvaliacoesAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_avaliacoes_item, parent, false)

        return AvaliacoesAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvaliacoesAdapterViewHolder, position: Int) {
        val avaliacao = avaliacoes[position]
        holder.lblNomeEmpresa.text = criptografador.decipher(avaliacao.empresa)
        holder.lblBairro.text = criptografador.decipher(avaliacao.bairro)
        holder.itemView.setOnClickListener {
            detalhaAvaliacao(avaliacao)
        }
    }

    override fun getItemCount(): Int = avaliacoes.size
}