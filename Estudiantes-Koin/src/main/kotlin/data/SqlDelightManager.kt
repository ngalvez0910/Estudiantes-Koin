package org.example.data

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries
import org.example.database.AppDatabase
import org.lighthousegames.logging.logging

/**
 * Administra las operaciones de la base de datos utilizando SQLDelight.
 *
 * @property databaseUrl La URL de la base de datos.
 * @property databaseInMemory Indica si se utilizará una base de datos en memoria.
 * @property databaseInitData Indica si se inicializarán datos de ejemplo en la base de datos.
 *
 * @since 1.0
 * @author Natalia Gonzalez
 */
class SqlDelightManager(
    private val databaseUrl: String,
    private val databaseInMemory: Boolean,
    private val databaseInitData: Boolean,
) {
    private val logger = logging()

    /** Consultas a la base de datos. */
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    init {
        logger.debug { "Inicializando el gestor de Bases de Datos con SQLDelight" }
        initialize()
    }

    /**
     * Inicializa las consultas a la base de datos.
     *
     * @return Las consultas a la base de datos.
     */
    private fun initQueries(): DatabaseQueries {
        return if (databaseInMemory) {
            logger.debug { "SqlDeLightClient - InMemory" }
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            logger.debug { "SqlDeLightClient - File: $databaseUrl" }
            JdbcSqliteDriver(databaseUrl)
        }.let { driver ->
            logger.debug { "Creando Tablas (si es necesario)" }
            AppDatabase.Schema.create(driver)
            AppDatabase(driver)
        }.databaseQueries
    }

    /**
     * Inicializa la base de datos, opcionalmente insertando datos de ejemplo.
     */
    fun initialize() {
        if (databaseInitData) {
            removeAllData()
            initDataExamples()
        }
    }

    /**
     * Inserta datos de ejemplo en la base de datos.
     */
    private fun initDataExamples() {
        logger.debug { "Iniciando datos de ejemplo..." }
        databaseQueries.transaction {
            demoEstudiantes()
        }
    }

    /**
     * Inserta datos de ejemplo de estudiantes en la base de datos.
     */
    private fun demoEstudiantes() {
        logger.debug { "Datos de ejemplo de Estudiantes..." }
        initDemoEstudiantes().forEach {
            databaseQueries.insertEstudiante(
                nombre = it.nombre,
                calificacion = it.calificacion,
                created_at = it.createdAt.toString(),
                updated_at = it.updatedAt.toString(),
            )
        }
    }

    /**
     * Elimina todos los datos de la base de datos.
     */
    private fun removeAllData() {
        logger.debug { "SqlDeLightClient.removeAllData()" }
        databaseQueries.transaction {
            databaseQueries.removeAllEstudiantes()
        }
    }
}
