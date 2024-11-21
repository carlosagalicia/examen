package com.example.kotlin.examen.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.examen.domain.ConsultarEventosRequirement

class ConsultarEventosViewModel : ViewModel() {
    private val _eventos = MutableLiveData<List<Map<String, Any>>>()
    val eventos: LiveData<List<Map<String, Any>>> get() = _eventos

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val consultarEventosRequirement = ConsultarEventosRequirement()

    fun consultarEventos(page: Int) {
        consultarEventosRequirement.consultarEventos(page) { result ->
            if (result.isSuccess) {
                _eventos.postValue(result.getOrDefault(emptyList()))
            } else {
                _error.postValue(result.exceptionOrNull()?.message)
            }
        }
    }
}
