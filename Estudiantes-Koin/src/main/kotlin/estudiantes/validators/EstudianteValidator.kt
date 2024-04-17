import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.estudiantes.errors.EstudianteError
import org.example.estudiantes.models.Estudiante

/**
 * Validador para los objetos de tipo Estudiante.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
class EstudianteValidator {
    /**
     * Valida un estudiante.
     *
     * @param estudiante Estudiante a validar.
     * @return Resultado encapsulando el estudiante si es válido, o un error [EstudianteError] si es inválido.
     */
    fun validate(estudiante: Estudiante): Result<Estudiante, EstudianteError> {
        return when {
            estudiante.nombre.isBlank() -> Err(EstudianteError.EstudianteNoValido("El nombre no puede estar vacío"))
            estudiante.calificacion !in 0.0..10.0 -> Err(EstudianteError.EstudianteNoValido("La calificación es inválida"))
            else -> Ok(estudiante)
        }
    }
}
