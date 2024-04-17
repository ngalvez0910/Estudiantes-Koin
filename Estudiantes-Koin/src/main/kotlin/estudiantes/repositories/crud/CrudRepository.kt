/**
 * Interfaz genérica que define las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) básicas para un repositorio.
 *
 * @param T Tipo del objeto que se maneja en el repositorio.
 * @param ID Tipo del identificador del objeto.
 * @since 1.0
 * @author Natalia Gonzalez
 */
interface CrudRepository<T, ID> {
    /**
     * Retorna todos los elementos del repositorio.
     *
     * @return Lista de todos los elementos.
     */
    fun findAll(): List<T>

    /**
     * Retorna el elemento correspondiente al ID proporcionado.
     *
     * @param id Identificador del elemento a buscar.
     * @return El elemento correspondiente al ID, o null si no se encuentra.
     */
    fun findById(id: ID): T?

    /**
     * Guarda el elemento proporcionado en el repositorio.
     *
     * @param item Elemento a guardar.
     * @return El elemento guardado.
     */
    fun save(item: T): T

    /**
     * Actualiza el elemento correspondiente al ID proporcionado con los datos del elemento proporcionado.
     *
     * @param id Identificador del elemento a actualizar.
     * @param item Elemento con los datos actualizados.
     * @return El elemento actualizado, o null si el elemento no se encuentra.
     */
    fun update(id: ID, item: T): T?

    /**
     * Elimina el elemento correspondiente al ID proporcionado del repositorio.
     *
     * @param id Identificador del elemento a eliminar.
     * @param logical Indica si se debe realizar una eliminación lógica (marcado como eliminado) en lugar de una eliminación física.
     * @return El elemento eliminado, o null si el elemento no se encuentra.
     */
    fun delete(id: ID, logical: Boolean = false): T?
}
