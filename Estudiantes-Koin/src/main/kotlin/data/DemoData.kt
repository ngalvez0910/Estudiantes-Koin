package org.example.data

import org.example.estudiantes.models.Estudiante

/**
 * Inicializa una lista de estudiantes de demostración.
 *
 * @return Lista de estudiantes de demostración.
 * @since 1.0
 * @author Natalia Gonzalez
 */
fun initDemoEstudiantes() = listOf(
    Estudiante(id = 1, nombre = "Ana García", calificacion = 7.5),
    Estudiante(id = 2, nombre = "Juan López", calificacion = 8.9),
    Estudiante(id = 3, nombre = "María Rodríguez", calificacion = 6.3),
    Estudiante(id= 4, nombre = "Pedro Martínez", calificacion = 9.1),
    Estudiante(id= 5, nombre = "Sofía González", calificacion = 5.6),
    Estudiante(id= 6, nombre = "Carlos Sánchez", calificacion = 7.8),
    Estudiante(id= 7, nombre = "Laura Pérez", calificacion = 8.2),
    Estudiante(id= 8, nombre = "Diego Ramírez", calificacion = 6.7),
    Estudiante(id= 9, nombre = "Lucía Fernández", calificacion = 9.5),
    Estudiante(id= 10, nombre = "Alejandro Díaz", calificacion = 4.9)
)
