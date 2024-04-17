package org.example.estudiantes.models

import java.time.LocalDateTime

/**
 * Clase que representa a un estudiante.
 *
 * @param id Identificador único del estudiante.
 * @param nombre Nombre del estudiante.
 * @param calificacion Calificación del estudiante.
 * @param createdAt Fecha y hora de creación del estudiante. Por defecto, se establece como la fecha y hora actual.
 * @param updatedAt Fecha y hora de la última actualización del estudiante. Por defecto, se establece como la fecha y hora actual.
 * @param isDeleted Indica si el estudiante ha sido eliminado. Por defecto, se establece como falso.
 * @since 1.0
 * @author Natalia Gonzalez
 */
data class Estudiante (
    val id : Long = -1,
    val nombre : String,
    val calificacion : Double,
    val createdAt : LocalDateTime = LocalDateTime.now(),
    val updatedAt : LocalDateTime = LocalDateTime.now(),
    val isDeleted : Boolean = false
)
