import org.example.estudiantes.models.Estudiante

/**
 * Interfaz que define las operaciones específicas para el manejo de estudiantes en el repositorio.
 *
 * Extiende [CrudRepository] con el tipo [Estudiante] y el tipo de ID [Long].
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface EstudianteRepository : CrudRepository<Estudiante, Long> {
    /**
     * Busca estudiantes por su nombre.
     *
     * @param nombre Nombre del estudiante a buscar.
     * @return Lista de estudiantes con el nombre especificado.
     */
    fun findByNombre(nombre: String): List<Estudiante>

    /**
     * Busca estudiantes por su calificación.
     *
     * @param calificacion Calificación del estudiante a buscar.
     * @return Lista de estudiantes con la calificación especificada.
     */
    fun findByCalificacion(calificacion: Double): List<Estudiante>
}
