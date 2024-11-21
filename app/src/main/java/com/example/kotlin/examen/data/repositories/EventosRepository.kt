package com.example.kotlin.examen.data.repositories

import android.util.Log
import com.example.kotlin.examen.data.network.NetworkModuleDI
import com.parse.ParseObject

class EventosRepository {
    fun consultarEventos(
        page: Int,
        callback: (Result<List<Map<String, Any>>>) -> Unit,
    ) {
        val parametros = hashMapOf<String, Any>("page" to page)

        NetworkModuleDI.callCloudFunction<HashMap<String, Any>>("hello", parametros) { result, e ->
            if (e == null) {
                try {
                    val parseObjects = result?.get("data") as? List<ParseObject> ?: emptyList()
                    val data = parseObjects.map { it.toMap() }
                    callback(Result.success(data))
                } catch (ex: Exception) {
                    Log.e("EventosRepository", "Error al procesar la respuesta: ${ex.message}")
                    callback(Result.failure(ex))
                }
            } else {
                val errorMessage = "Error obteniendo los datos: ${e.localizedMessage}"
                Log.e("EventosRepository", errorMessage, e)
                callback(Result.failure(Exception(errorMessage)))
            }
        }
    }

    private fun ParseObject.toMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        for (key in keySet()) {
            map[key] = get(key) ?: ""
        }
        return map
    }
}
