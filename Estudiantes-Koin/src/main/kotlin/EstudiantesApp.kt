package org.example

import EstudianteService
import org.example.estudiantes.models.Estudiante
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Clase principal de la aplicación para gestionar estudiantes.
 * Esta clase utiliza inyección de dependencias a través de Koin.
 * @since 1.0
 * @author Natalia Gonzalez
 */
class EstudiantesApp : KoinComponent {
    /** Servicio de Estudiante inyectado por Koin */
    val estudianteService: EstudianteService by inject()

    /**
     * Método principal para ejecutar la aplicación.
     * Guarda un estudiante en el servicio y luego imprime todos los estudiantes.
     */
    fun run() {
        estudianteService.save(
            Estudiante(
                id = 1,
                nombre = "Ana García",
                calificacion = 7.5
            )
        )

        val lista = estudianteService.findAll().value
        lista.forEach { println(it) }
    }
}
