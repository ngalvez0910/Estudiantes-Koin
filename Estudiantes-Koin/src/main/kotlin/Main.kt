package org.example

import EstudiantesModule
import org.koin.core.context.GlobalContext.startKoin
import org.koin.fileProperties
import org.koin.test.verify.verify

fun main() {
    startKoin {
        printLogger()
        fileProperties("/config.properties")
        EstudiantesModule.verify()
        modules(EstudiantesModule)
    }

    val app = EstudiantesApp()
    app.run()
}