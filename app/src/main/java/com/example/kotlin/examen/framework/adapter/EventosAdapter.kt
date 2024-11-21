package com.example.kotlin.examen.framework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen.databinding.ItemEventoBinding

class EventosAdapter : ListAdapter<Map<String, Any>, EventosAdapter.EventoViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EventoViewHolder {
        val binding = ItemEventoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: EventoViewHolder,
        position: Int,
    ) {
        val evento = getItem(position)
        holder.bind(evento)
    }

    class EventoViewHolder(
        private val binding: ItemEventoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(evento: Map<String, Any>) {
            binding.textDate.text = evento["date"]?.toString() ?: "N/A"
            binding.textDescription.text = evento["description"]?.toString() ?: "N/A"
            // Agregar más bindings según sea necesario
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Map<String, Any>>() {
        override fun areItemsTheSame(
            oldItem: Map<String, Any>,
            newItem: Map<String, Any>,
        ): Boolean {
            // Comparar por un identificador único, como objectId
            return oldItem["objectId"] == newItem["objectId"]
        }

        override fun areContentsTheSame(
            oldItem: Map<String, Any>,
            newItem: Map<String, Any>,
        ): Boolean {
            // Comparar manualmente las claves importantes
            return oldItem["date"] == newItem["date"] &&
                oldItem["description"] == newItem["description"] &&
                oldItem["lang"] == newItem["lang"] &&
                oldItem["category1"] == newItem["category1"] &&
                oldItem["category2"] == newItem["category2"] &&
                oldItem["granularity"] == newItem["granularity"] &&
                oldItem["createdAt"] == newItem["createdAt"] &&
                oldItem["updatedAt"] == newItem["updatedAt"]
        }
    }
}
