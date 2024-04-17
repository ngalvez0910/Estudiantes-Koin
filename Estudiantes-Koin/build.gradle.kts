plugins {
    kotlin("jvm") version "1.9.21"
    id("org.jetbrains.dokka") version "1.9.10"
    id("app.cash.sqldelight") version "2.0.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Log
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    // SQLDelight para SQLite
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")

    // Result ROP
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")

    //Koin
    implementation(platform("io.insert-koin:koin-bom:3.5.3"))
    implementation("io.insert-koin:koin-core")
    implementation("io.insert-koin:koin-test")

    //mybatis
    implementation("org.mybatis:mybatis:3.5.13")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("org.example.database")
        }
    }
}