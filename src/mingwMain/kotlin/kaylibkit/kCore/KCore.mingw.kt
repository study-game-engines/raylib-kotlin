package kaylibkit.kCore
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.pin
import kotlinx.cinterop.toKString
import platform.windows.*

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
            val buffer = ByteArray(MAX_PATH) // Create a buffer to store the path to the executable file.
            val pathLength = GetModuleFileNameA(null, buffer.pin().addressOf(0), MAX_PATH.toUInt()) // Retrieves the fully qualified path for the executable

            // Check if the path length is less than or equal to zero, indicating an error. Throw exception if error is found
            if (pathLength.toInt() == 0) throw ResourcePathException("Failed to retrieve executable path.")

            val executablePath = buffer.toKString() // Convert the buffer to a Kotlin string to get the full path to the executable.

            // Find the index of the last slash character ('\\') in the executable path, which separates the directory from the executable file name.
            val lastBackslashIndex = executablePath.lastIndexOf('\\')
            if (lastBackslashIndex == -1) throw ResourcePathException("Invalid executable path format: $executablePath.")

            // Extract the directory portion of the executable path
            val executableDir = executablePath.substring(0, lastBackslashIndex + 1)
            val resourceDir = executableDir + "resources" // Return the directory path of the executable file along with the "resources" subdirectory.

            // Check if the "resources" directory exists
            if (GetFileAttributesW(resourceDir) == INVALID_FILE_ATTRIBUTES) {
                throw ResourcePathException("The 'resources' directory does not exist at: $resourceDir, unable to load resources.")
            }

            return resourceDir
        } catch (e: ResourcePathException) {
            println("ResourcePathException: ${e.message}")
            return null
        }
    }