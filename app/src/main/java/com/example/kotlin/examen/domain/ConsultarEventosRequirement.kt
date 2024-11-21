package com.example.kotlin.examen.domain

import com.example.kotlin.examen.data.repositories.EventosRepository

class ConsultarEventosRequirement {
    private val repository: EventosRepository = EventosRepository()

    fun consultarEventos(
        page: Int,
        callback: (Result<List<Map<String, Any>>>) -> Unit,
    ) {
        repository.consultarEventos(page) { result ->
            if (result.isSuccess) {
                callback(Result.success(result.getOrDefault(emptyList())))
            } else {
                callback(Result.failure(result.exceptionOrNull() ?: Exception("Error desconocido")))
            }
        }
    }
}
