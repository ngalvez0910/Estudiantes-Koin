package org.example.estudiantes.mappers

import database.EstudianteEntity
import org.example.estudiantes.models.Estudiante
import java.time.LocalDateTime

fun EstudianteEntity.toEstudiante(): Estudiante {
    return Estudiante(
        id = this.id,
        nombre = this.nombre,
        calificacion = this.calificacion!!.toDouble(),
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at),
        isDeleted = this.is_deleted.toInt() == 1
    )
}