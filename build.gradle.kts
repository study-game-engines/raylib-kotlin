import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.9.10"
    id("maven-publish")
}

group = "com.prism-architect"
version = "1.0.5"

// Codeberg repository properties providers
val tokenProvider: Provider<String> = providers.gradleProperty("KaylibKitToken")
val mavenUrlProvider: Provider<String> = providers.gradleProperty("KaylibKitMavenUrl")

val raylibVersion = "4.5.0"
val raylibMakeVersion = "VERSION=$raylibVersion"

val raylibChecksumNum = "18a36b3e066f5743757cfa9ecbe784bbe20d529e"
val raylibChecksum = "CHECKSUM=$raylibChecksumNum"

repositories {
    mavenCentral()
    mavenLocal()

    extensions.configure<PublishingExtension> {
        repositories {
            if (tokenProvider.isPresent && mavenUrlProvider.isPresent) {
                maven(mavenUrlProvider) {
                    name = "CodebergPackages"

                    credentials(HttpHeaderCredentials::class) {
                        name = "Authorization"
                        value = "token ${tokenProvider.get()}"
                    }
                    authentication.create<HttpHeaderAuthentication>("header")
                }
            }
        }
    }

}

@Suppress("UNUSED_VARIABLE")
kotlin {
    linuxX64()
    mingwX64()
    macosX64()
    macosArm64()

    targets.withType<KotlinNativeTarget> {
        compilations.getByName("main") {
            cinterops {
                val kaylibc by creating
            }
        }
        binaries {
            binaries.staticLib()
            binaries.RELEASE
        }
    }

    sourceSets {
        val commonMain by getting

        val macosMain by creating { dependsOn(commonMain) }
        val macosX64Main by getting { dependsOn(macosMain) }
        val macosArm64Main by getting { dependsOn(macosMain) }

        val linuxMain by creating { dependsOn(commonMain) }
        val linuxX64Main by getting { dependsOn(linuxMain) }

        val mingwMain by creating { dependsOn(commonMain) }
        val mingwX64Main by getting { dependsOn(mingwMain) }
    }
}

val sourceRaylib by tasks.registering(Exec::class) {
    group = project.name
    description = "Run Make to download static libraries and headers"
    workingDir = file("src/nativeInterop/cinterop")
    commandLine("make", raylibMakeVersion, raylibChecksum)
}

val cleanRaylib by tasks.registering(Exec::class) {
    group = project.name
    description = "Run Make to cleanup static libraries and headers (Useful for version update)"
    workingDir = file("src/nativeInterop/cinterop")
    commandLine("make", "clean")
}