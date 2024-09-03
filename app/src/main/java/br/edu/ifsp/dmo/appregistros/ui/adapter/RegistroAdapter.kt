package br.edu.ifsp.dmo.appregistros.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.dmo.appregistros.R
import br.edu.ifsp.dmo.appregistros.data.model.Registro
import br.edu.ifsp.dmo.appregistros.databinding.ItemRegistroBinding
import br.edu.ifsp.dmo.appregistros.ui.listeners.RegistroItemClickListener

class RegistroAdapter(private val listener: RegistroItemClickListener) :
    RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {
    private var dataset: List<Registro> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_registro, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val registro = dataset[position]
        holder.binding.textTitle.text = registro.title

        holder.binding.imageDone.setOnClickListener {
            listener.clickDone(position)
        }
        holder.binding.viewRegistro.setOnClickListener {
            listener.clickOpen(position)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun submitDataset(data: List<Registro>) {
        dataset = data
        this.notifyDataSetChanged()
    }

    fun getDatasetItem(position: Int): Registro {
        return dataset[position]
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemRegistroBinding = ItemRegistroBinding.bind(view)
    }
}
