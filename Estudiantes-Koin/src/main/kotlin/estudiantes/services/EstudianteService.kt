import com.github.michaelbull.result.Result
import org.example.estudiantes.errors.EstudianteError
import org.example.estudiantes.models.Estudiante

/**
 * Interfaz que define las operaciones disponibles para el servicio de gestión de estudiantes.
 *
 * Todas las operaciones retornan un resultado encapsulado en un objeto [Result].
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface EstudianteService {
    /**
     * Retorna todos los estudiantes.
     *
     * @return Resultado encapsulando una lista de estudiantes si la operación fue exitosa, o un error [EstudianteError] si falla.
     */
    fun findAll(): Result<List<Estudiante>, EstudianteError>

    /**
     * Retorna el estudiante correspondiente al ID proporcionado.
     *
     * @param id Identificador del estudiante.
     * @return Resultado encapsulando el estudiante si la operación fue exitosa, o un error [EstudianteError] si falla.
     */
    fun findById(id: Long): Result<Estudiante, EstudianteError>

    /**
     * Busca estudiantes por su nombre.
     *
     * @param nombre Nombre del estudiante.
     * @return Resultado encapsulando una lista de estudiantes si la operación fue exitosa, o un error [EstudianteError] si falla.
     */
    fun findByNombre(nombre: String): Result<List<Estudiante>, EstudianteError>

    /**
     * Busca estudiantes por su calificación.
     *
     * @param calificacion Calificación del estudiante.
     * @return Resultado encapsulando una lista de estudiantes si la operación fue exitosa, o un error [EstudianteError] si falla.
     */
    fun findByCalificacion(calificacion: Double): Result<List<Estudiante>, EstudianteError>

    /**
     * Guarda un nuevo estudiante.
     *
     * @param estudiante Estudiante a guardar.
     * @return Resultado encapsulando el estudiante guardado si la operación fue exitosa, o un error [EstudianteError] si falla.
     */
    fun save(estudiante: Estudiante): Result<Estudiante, EstudianteError>

    /**
     * Actualiza el estudiante correspondiente al ID proporcionado con los datos proporcionados.
     *
     * @param id Identificador del estudiante a actualizar.
     * @param estudiante Datos actualizados del estudiante.
     * @return Resultado encapsulando el estudiante actualizado si la operación fue exitosa, o un error [EstudianteError] si falla.
     */
    fun update(id: Long, estudiante: Estudiante): Result<Estudiante, EstudianteError>

    /**
     * Elimina el estudiante correspondiente al ID proporcionado.
     *
     * @param id Identificador del estudiante a eliminar.
     * @param logical Indica si se debe realizar una eliminación lógica (marcado como eliminado) en lugar de una eliminación física.
     * @return Resultado encapsulando el estudiante eliminado si la operación fue exitosa, o un error [EstudianteError] si falla.
     */
    fun delete(id: Long, logical: Boolean = false): Result<Estudiante, EstudianteError>
}
