package org.example.estudiantes.cache

import com.github.michaelbull.result.Result
import org.example.estudiantes.cache.errors.CacheError

/**
 * Interfaz que define las operaciones básicas de una caché genérica.
 *
 * @param T Tipo de valor almacenado en la caché.
 * @param K Tipo de datos de la clave utilizada para acceder a los valores en la caché.
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface Cache<K, T> {
    /**
     * Almacena un valor en la caché con la clave especificada.
     *
     * @param key Clave asociada al valor.
     * @param value Valor a almacenar en la caché.
     * @return [Result] que indica si la operación fue exitosa o si ocurrió un error.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun put(key: K, value: T): Result<T, Nothing>

    /**
     * Recupera el valor asociado a la clave especificada desde la caché.
     *
     * @param key Clave del valor a recuperar.
     * @return [Result] que contiene el valor asociado a la clave si existe, o un [CacheError] si ocurrió un error.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun get(key: K): Result<T, CacheError>

    /**
     * Elimina el valor asociado a la clave especificada de la caché.
     *
     * @param key Clave del valor a eliminar.
     * @return [Result] que contiene el valor asociado a la clave antes de eliminarlo si existe, o un [CacheError] si ocurrió un error.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun remove(key: K): Result<T, CacheError>

    /**
     * Elimina todos los valores almacenados en la caché.
     * @return [Result] que indica si la operación fue exitosa o si ocurrió un error.
     * @since 1.0
     * @author Natalia Gonzalez
     */
    fun clear(): Result<Unit, Nothing>
}
