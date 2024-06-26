plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("publish")
}

kotlin {
    jvmToolchain(JAVA_TARGET_V_int)
    jvm()
    sourceSets {
        named("jvmMain") {
            dependencies {
                compileOnly(project(":telegram-bot"))
                api(libs.ktor.server.core)
                api(libs.ktor.server.netty)
                implementation(libs.ssl.utils)
            }
        }
    }
}

libraryData {
    name.set("Ktor starter")
    description.set("Ktor webhook starter for KtGram.")
}
