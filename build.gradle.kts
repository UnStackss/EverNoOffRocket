
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.internal.classpath.Instrumented.systemProperty


plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.shadowJar)
}

group = "dev.unstackss"
version = "1.8-1.21.1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        content {
            includeGroup("org.bukkit")
            includeGroup("org.spigotmc")
        }
    }
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    maven { url = uri("https://oss.sonatype.org/content/repositories/central") }
    maven { url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/") }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
    implementation(libs.bStats)
}

val targetJavaVersion = 20
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    if (targetJavaVersion >= 17 || JavaVersion.current().isJava10Compatible) {
        kotlinOptions.jvmTarget = targetJavaVersion.toString()
    }

}

tasks {

    val shadowJar = named<ShadowJar>("shadowJar")

    shadowJar.configure {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveBaseName.set("EverNoOffRocket")
        relocate("org.bstats", "dev.unstackss.evernooffrocket.bstats")

        listOf(
            "org.bstats"
        ).forEach {
            relocate(it, "libs.$it")
        }

        exclude("META-INF/**")
        exclude("com/google/**")
        exclude("mojang-translations/**")
        exclude("org/apache/**")
        exclude("org/joml/**")
        exclude("org/json/**")
        exclude("_COROUTINE/**")
        exclude("org/checkerframework/**")
        exclude("DebugProbesKt.bin")

        mergeServiceFiles()

        from("src/main/resources") {
            include("**/*")
        }

        destinationDirectory.set(file("target"))

        archiveFileName.set("EverNoOffRocket.jar")

        doFirst {
            logger.quiet("""
            Building EverNoOffRocket....
            """.trimIndent())
        }

        logging.captureStandardOutput(LogLevel.LIFECYCLE)
        outputs.upToDateWhen { false }
    }

    named("build") {
        dependsOn(shadowJar)
    }


    kotlin {
        sourceSets.all {
            languageSettings {
                languageVersion = "2.0"
            }
        }
        sourceSets {
            named("main") {
                kotlin.srcDir("src/main/kotlin")
            }
        }

        compilerOptions {
            freeCompilerArgs = listOf(
                "-Xuse-k2",
                "-Xno-call-assertions",
                "-Xopt-in=kotlin.ExperimentalStdlibApi"
            )
        }
    }
}

tasks.withType<ShadowJar>().configureEach {
    systemProperty("org.gradle.warning.mode", "none")
}