package org.example.estudiantes.cache

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import org.example.estudiantes.models.Estudiante
import org.lighthousegames.logging.logging
import com.github.michaelbull.result.Result
import org.example.estudiantes.cache.errors.CacheError

private val logger = logging()

/**
 * Implementación de la caché para almacenar objetos de tipo [Estudiante].
 *
 * @param size Tamaño máximo de la caché.
 * @since 1.0
 * @author Natalia Gonzalez
 */
class EstudianteCache<K,T>(
    private val size: Int
) : Cache<K, T> {
    private val cache = mutableMapOf<K, T>()

    /**
     * Recupera el valor asociado a la clave especificada desde la caché.
     *
     * @param key Clave del valor a recuperar.
     * @return [Result] que contiene el valor asociado a la clave si existe, o un [CacheError] si no se encuentra el valor en la caché.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun get(key: K): Result<T, CacheError> {
        logger.debug { "Recuperando valor de la cache..." }
        return if (cache.containsKey(key)) {
            Ok(cache.getValue(key))
        } else {
            Err(CacheError("No existe el valor en la cache"))
        }
    }

    /**
     * Almacena un valor en la caché con la clave especificada.
     *
     * @param key Clave asociada al valor.
     * @param value Valor a almacenar en la caché.
     * @return [Result] que indica si la operación fue exitosa.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun put(key: K, value: T): Result<T, Nothing> {
        logger.debug { "Guardando valor en la cache..." }
        if (cache.size >= size && !cache.containsKey(key)) {
            logger.debug { "Eliminando valor de la cache" }
            cache.remove(cache.keys.first())
        }
        cache[key] = value
        return Ok(value)
    }

    /**
     * Elimina el valor asociado a la clave especificada de la caché.
     *
     * @param key Clave del valor a eliminar.
     * @return [Result] que contiene el valor eliminado si existía, o un [CacheError] si no se encuentra el valor en la caché.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun remove(key: K): Result<T, CacheError> {
        logger.debug { "Eliminando valor de la cache..." }
        return if (cache.containsKey(key)) {
            Ok(cache.remove(key)!!)
        } else {
            Err(CacheError("No existe el valor en la cache"))
        }
    }

    /**
     * Elimina todos los valores almacenados en la caché.
     *
     * @return [Result] que indica si la operación fue exitosa.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    override fun clear(): Result<Unit, Nothing> {
        logger.debug { "Limpiando cache..." }
        cache.clear()
        return Ok(Unit)
    }
}
