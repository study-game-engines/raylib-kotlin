package kaylibkit.kCore

import kaylibc.TraceLogCallback
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.posix.*

@OptIn(ExperimentalForeignApi::class)
actual fun setTraceLogCallbackInternal(callback: TraceLogCallback) {
    kaylibc.SetTraceLogCallback(callback)
}

/**
 * Returns the absolute path to the 'resources' folder located at the same location as your executable.
 *
 * This property provides the path to the 'resources' folder associated with your executable.
 * Ensure that a folder named 'resources' is present in the same directory as your executable.
 *
 * @return The absolute path to the 'resources' folder, or null if it doesn't exist or cannot be determined.
 */
    @OptIn(ExperimentalForeignApi::class)
    actual inline val resourcePath: String?
        get() {
            try {
                val pid = getpid() // Get the process ID (PID) of the current process
                val buffer = ByteArray(PATH_MAX) // Create a buffer to store the path
                val procPath = "/proc/$pid/exe" // Build the path to the '/proc' directory for the process

                // Use the realpath function to get the canonicalized absolute pathname of the executable
                if (realpath(procPath, buffer.refTo(0)) == null) throw ResourcePathException("Failed to retrieve executable path.")
                val executablePath = buffer.toKString() // Convert the buffer, which contains the absolute path, to a Kotlin string

                // Find the last '/' character to extract the directory path
                val lastSlashIndex = executablePath.lastIndexOf('/')
                if (lastSlashIndex == -1) throw ResourcePathException("Invalid executable path format: $executablePath.")

                val executableDir = executablePath.substring(0, lastSlashIndex + 1) // Extract the directory portion of the executable path, including the trailing slash.
                val resourceDir = executableDir + "resources" // Return the directory path of the executable file along with the "resources" subdirectory.

                // Check if the "resources" directory exists
                if (access(resourceDir, F_OK) != 0) throw ResourcePathException("The 'resources' directory does not exist at: $resourceDir, unable to load resources.")

                return resourceDir
            } catch (e: ResourcePathException) {
                println("ResourcePathException: ${e.message}")
                return null
            }
        }