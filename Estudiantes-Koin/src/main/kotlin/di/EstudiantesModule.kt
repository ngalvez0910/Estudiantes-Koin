import org.example.estudiantes.cache.Cache
import org.example.estudiantes.cache.EstudianteCache
import org.example.estudiantes.models.Estudiante
import org.koin.dsl.module

/**
 * Este módulo proporciona la configuración de los componentes necesarios para trabajar con estudiantes.
 * Incluye la configuración de la capa de datos, el caché y los servicios relacionados con los estudiantes.
 *
 * @since 1.0
 * @author Natalia Gonzales
 */
val EstudiantesModule = module {

    /**
     * Proporciona una instancia única de [EstudianteRepository], utilizando [EstudianteRepositoryImpl].
     */
    single<EstudianteRepository> { EstudianteRepositoryImpl(get()) }

    /**
     * Proporciona una instancia única de [Cache] que almacena objetos de tipo [Estudiante].
     * El tamaño máximo del caché se establece en 7.
     */
    single<Cache<Long, Estudiante>> { EstudianteCache(6) }

    /**
     * Proporciona una fábrica para crear instancias de [EstudianteService], utilizando [EstudianteServiceImpl].
     * Requiere [EstudianteRepository], [Cache] y [Logger] como dependencias.
     */
    factory<EstudianteService> { EstudianteServiceImpl(get(), get(), get()) }
}
