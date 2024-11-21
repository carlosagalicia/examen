package com.example.kotlin.examen.framework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.kotlin.examen.databinding.ItemEventoBinding
import com.example.kotlin.examen.framework.adapter.viewholder.EventoViewHolder

class EventosAdapter : ListAdapter<Map<String, Any>, EventoViewHolder>(DiffCallback()) {
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
        holder.bind(evento) // Aquí se usa la función "bind"
    }

    // DiffCallback para comparar elementos
    class DiffCallback : DiffUtil.ItemCallback<Map<String, Any>>() {
        override fun areItemsTheSame(
            oldItem: Map<String, Any>,
            newItem: Map<String, Any>,
        ): Boolean = oldItem["objectId"] == newItem["objectId"]

        override fun areContentsTheSame(
            oldItem: Map<String, Any>,
            newItem: Map<String, Any>,
        ): Boolean =
            oldItem["date"] == newItem["date"] &&
                oldItem["description"] == newItem["description"] &&
                oldItem["lang"] == newItem["lang"] &&
                oldItem["category1"] == newItem["category1"] &&
                oldItem["category2"] == newItem["category2"] &&
                oldItem["granularity"] == newItem["granularity"] &&
                oldItem["createdAt"] == newItem["createdAt"] &&
                oldItem["updatedAt"] == newItem["updatedAt"] &&
                oldItem["className"] == newItem["className"]
    }
}
