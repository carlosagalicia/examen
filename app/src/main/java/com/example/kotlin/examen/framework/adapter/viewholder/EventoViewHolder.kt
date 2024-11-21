package com.example.kotlin.examen.framework.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examen.databinding.ItemEventoBinding

class EventoViewHolder(
    private val binding: ItemEventoBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(evento: Map<String, Any>) {
        binding.textDate.text = evento["date"]?.toString() ?: "N/A"
        binding.textDescription.text = evento["description"]?.toString() ?: "N/A"
        binding.textLang.text = evento["lang"]?.toString() ?: "N/A"
        binding.textCategory1.text = evento["category1"]?.toString() ?: "N/A"
        binding.textCategory2.text = evento["category2"]?.toString() ?: "N/A"
        binding.textGranularity.text = evento["granularity"]?.toString() ?: "N/A"
        binding.textCreatedAt.text = evento["createdAt"]?.toString() ?: "N/A"
        binding.textUpdatedAt.text = evento["updatedAt"]?.toString() ?: "N/A"
        binding.textObjectId.text = evento["objectId"]?.toString() ?: "N/A"
        binding.textClassName.text = evento["className"]?.toString() ?: "N/A"
    }
}
