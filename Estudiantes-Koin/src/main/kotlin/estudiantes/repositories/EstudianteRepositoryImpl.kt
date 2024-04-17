import org.example.data.SqlDelightManager
import org.example.estudiantes.mappers.toEstudiante
import org.example.estudiantes.models.Estudiante
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

private val logger = logging()

/**
 * Implementación del repositorio de estudiantes que utiliza SQLDelight para el acceso a la base de datos.
 *
 * @param dbManager Gestor de SQLDelight para la conexión y operaciones con la base de datos.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
class EstudianteRepositoryImpl (
    private val dbManager: SqlDelightManager
) : EstudianteRepository {

    private val db = dbManager.databaseQueries

    /**
     * Recupera todos los estudiantes de la base de datos.
     *
     * @return Una lista de todos los estudiantes.
     */
    override fun findAll(): List<Estudiante> {
        logger.debug { "Recuperando todos los estudiantes..." }
        return db.selectAllEstudiantes()
            .executeAsList()
            .map { it.toEstudiante() }
    }

    /**
     * Recupera un estudiante por su ID.
     *
     * @param id El ID del estudiante a recuperar.
     * @return El estudiante correspondiente al ID, o null si no se encuentra.
     */
    override fun findById(id: Long): Estudiante? {
        logger.debug { "Recuperando estudiante por id: $id..." }
        return db.selectEstudianteById(id)
            .executeAsOneOrNull()
            ?.toEstudiante()
    }

    /**
     * Recupera estudiantes por su nombre.
     *
     * @param nombre El nombre del estudiante a buscar.
     * @return Una lista de estudiantes con el nombre especificado.
     */
    override fun findByNombre(nombre: String): List<Estudiante> {
        logger.debug { "Recuperando estudiante por nombre: $nombre..." }
        return db.selectAllEstudiantesByNombre(nombre)
            .executeAsList()
            .map { it.toEstudiante() }
    }

    /**
     * Recupera estudiantes por su calificación.
     *
     * @param calificacion La calificación de los estudiantes a buscar.
     * @return Una lista de estudiantes con la calificación especificada.
     */
    override fun findByCalificacion(calificacion: Double): List<Estudiante> {
        logger.debug { "Recuperando estudiantes por calificación: $calificacion..." }
        return db.selectAllEstudiantesByCalificacion(calificacion)
            .executeAsList()
            .map { it.toEstudiante() }
    }

    /**
     * Guarda un nuevo estudiante en la base de datos.
     *
     * @param estudiante El estudiante a guardar.
     * @return El estudiante guardado, incluido su ID asignado por la base de datos.
     */
    override fun save(estudiante: Estudiante): Estudiante {
        logger.debug { "Guardando estudiante: $estudiante..." }

        val timeStamp = LocalDateTime.now().toString()

        db.transaction {
            db.insertEstudiante(
                nombre = estudiante.nombre,
                calificacion = estudiante.calificacion.toDouble(),
                created_at = timeStamp,
                updated_at = timeStamp
            )
        }
        return db.selectEstudianteLastInserted()
            .executeAsOne()
            .toEstudiante()
    }

    /**
     * Actualiza un estudiante existente en la base de datos.
     *
     * @param id El ID del estudiante a actualizar.
     * @param estudiante Los nuevos datos del estudiante.
     * @return El estudiante actualizado, o null si el estudiante no se encuentra.
     */
    override fun update(id: Long, estudiante: Estudiante): Estudiante? {
        logger.debug { "Actualizando estudiante por id: $id..." }
        var result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()

        result = result.copy(
            nombre = estudiante.nombre,
            calificacion = estudiante.calificacion,
            updatedAt = timeStamp
        )

        db.updateEstudiante(
            nombre = result.nombre,
            calificacion = result.calificacion,
            updated_at = timeStamp.toString(),
            is_deleted = if (result.isDeleted) 1 else 0,
            id = result.id
        )
        return result
    }

    /**
     * Elimina un estudiante de la base de datos.
     *
     * @param id El ID del estudiante a eliminar.
     * @param logical Indica si se realizará un borrado lógico (marcando como eliminado) o físico.
     * @return El estudiante eliminado, o null si el estudiante no se encuentra.
     */
    override fun delete(id: Long, logical: Boolean): Estudiante? {
        logger.debug { "Borrando estudiante por id: $id..." }
        val result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()
        db.updateEstudiante(
            nombre = result.nombre,
            calificacion = result.calificacion,
            is_deleted = 1,
            updated_at = timeStamp.toString(),
            id = result.id,
        )
        return result.copy(isDeleted = true, updatedAt = timeStamp)
    }
}
