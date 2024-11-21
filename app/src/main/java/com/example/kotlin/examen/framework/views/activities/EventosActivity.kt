package com.example.kotlin.examen.framework.views.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.examen.databinding.ActivityEventosBinding
import com.example.kotlin.examen.framework.adapter.EventosAdapter
import com.example.kotlin.examen.framework.viewmodel.ConsultarEventosViewModel

class EventosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventosBinding
    private lateinit var viewModel: ConsultarEventosViewModel
    private lateinit var adapter: EventosAdapter

    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupViewModel()
        setupPaginationControls()

        // Cargar la primera página
        loadPage(currentPage)
    }

    private fun setupRecyclerView() {
        adapter = EventosAdapter()
        binding.recyclerViewEventos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEventos.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[ConsultarEventosViewModel::class.java]

        viewModel.eventos.observe(this) { eventos ->
            adapter.submitList(eventos) // Actualizar el adaptador con los datos actuales
            binding.textPageNumber.text = "Página $currentPage"
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupPaginationControls() {
        binding.buttonPrevious.setOnClickListener {
            if (currentPage > 1) {
                currentPage--
                loadPage(currentPage)
            }
        }

        binding.buttonNext.setOnClickListener {
            currentPage++
            loadPage(currentPage)
        }
    }

    private fun loadPage(page: Int) {
        viewModel.consultarEventos(page)
        binding.buttonPrevious.isEnabled = page > 1
    }
}
