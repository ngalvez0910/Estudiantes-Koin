package org.example.estudiantes.errors

/**
 * Clase sellada que representa los posibles errores relacionados con operaciones de estudiantes.
 *
 * @param message Mensaje descriptivo del error.
 * @since 1.0
 * @author Natalia Gonzalez
 */
sealed class EstudianteError(val message: String) {
    /**
     * Error que indica que el estudiante no fue encontrado.
     *
     * @param message Mensaje descriptivo del error.
     * @since 1.0
     */
    class EstudianteNoEncontrado(message: String) : EstudianteError(message)

    /**
     * Error que indica que el estudiante no es válido.
     *
     * @param message Mensaje descriptivo del error.
     * @since 1.0
     */
    class EstudianteNoValido(message: String) : EstudianteError(message)

    /**
     * Error que indica que no se pudo actualizar el estudiante.
     *
     * @param message Mensaje descriptivo del error.
     * @since 1.0
     */
    class EstudianteNoActualizado(message: String) : EstudianteError(message)

    /**
     * Error que indica que no se pudo eliminar el estudiante.
     *
     * @param message Mensaje descriptivo del error.
     * @since 1.0
     */
    class EstudianteNoEliminado(message: String) : EstudianteError(message)
}
