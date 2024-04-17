package org.example.config

import org.lighthousegames.logging.logging
import java.util.*

private val logger = logging()

object Config {
    var databaseUrl: String = "jdbc:sqlite:productos.db"
        private set
    var databaseInitTables: Boolean = false
        private set
    var databaseInitData: Boolean = false
        private set
    var databaseInMemory: Boolean = false
        private set
    var cacheSize: Int = 7
        private set

    init {
        try {
            logger.debug { "Cargando configuración" }
            val properties = Properties()
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
            databaseUrl = properties.getProperty("database.url", this.databaseUrl)
            databaseInitTables =
                properties.getProperty("database.init.tables", this.databaseInitTables.toString()).toBoolean()
            databaseInitData =
                properties.getProperty("database.init.data", this.databaseInitData.toString()).toBoolean()
            databaseInMemory =
                properties.getProperty("database.inmemory", this.databaseInMemory.toString()).toBoolean()
            cacheSize = properties.getProperty("cache.size", this.cacheSize.toString()).toInt()
            logger.debug { "Configuración cargada correctamente" }

        } catch (e: Exception) {
            logger.error { "Error cargando configuración: ${e.message}" }
            logger.error { "Usando valores por defecto" }
        }

    }
}