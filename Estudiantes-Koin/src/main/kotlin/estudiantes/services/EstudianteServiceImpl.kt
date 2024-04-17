import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import org.example.estudiantes.cache.EstudianteCache
import org.example.estudiantes.errors.EstudianteError
import org.example.estudiantes.models.Estudiante
import org.lighthousegames.logging.logging

private val logger = logging()

/**
 * Implementación del servicio de gestión de estudiantes.
 *
 * @param estudianteRepository Repositorio de estudiantes para acceder a los datos.
 * @param estudianteCache Caché de estudiantes para mejorar el rendimiento de acceso a los datos.
 * @param estudianteValidator Validador de estudiantes para validar los datos antes de realizar operaciones.
 * @since 1.0
 * @author Natalia Gonzalez
 */
class EstudianteServiceImpl(
    private val estudianteRepository: EstudianteRepository,
    private val estudianteCache: EstudianteCache<Long,Estudiante>,
    private val estudianteValidator: EstudianteValidator
): EstudianteService {
    override fun findAll(): Result<List<Estudiante>, EstudianteError> {
        logger.debug { "Recuperando todos los estudiantes..." }
        return Ok(estudianteRepository.findAll())
    }

    override fun findById(id: Long): Result<Estudiante, EstudianteError> {
        logger.debug { "Recuperando estudiante por id: $id..." }
        return estudianteRepository.findById(id)
            ?.let { Ok(it) }
            ?: Err(EstudianteError.EstudianteNoEncontrado("Estudiante no encontrado con id: $id"))
    }

    override fun findByNombre(nombre: String): Result<List<Estudiante>, EstudianteError> {
        logger.debug { "Recuperando estudiantes por nombre: $nombre" }
        return Ok(estudianteRepository.findByNombre(nombre))
    }

    override fun findByCalificacion(calificacion: Double): Result<List<Estudiante>, EstudianteError> {
        logger.debug { "Recuperando estudiantes por calificacion: $calificacion" }
        return Ok(estudianteRepository.findByCalificacion(calificacion))
    }

    override fun save(estudiante: Estudiante): Result<Estudiante, EstudianteError> {
        logger.debug { "Guardando estudiante: $estudiante" }
        return estudianteValidator.validate(estudiante).andThen {
            Ok(estudianteRepository.save(it))
        }
    }

    override fun update(id: Long, estudiante: Estudiante): Result<Estudiante, EstudianteError> {
        logger.debug { "Actualizando estudiante por id: $id..." }
        return estudianteValidator.validate(estudiante).andThen { p ->
            estudianteRepository.update(id, p)
                ?.let { Ok(it) }
                ?: Err(EstudianteError.EstudianteNoActualizado("Estudiante no actualizado con id: $id"))
        }
    }

    override fun delete(id: Long, logical: Boolean): Result<Estudiante, EstudianteError> {
        logger.debug { "Borrando estudiante por id: $id..." }
        return estudianteRepository.delete(id)
            ?.let { Ok(it) }
            ?: Err(EstudianteError.EstudianteNoEliminado("Estudiante no eliminado con id: $id"))
    }
}
