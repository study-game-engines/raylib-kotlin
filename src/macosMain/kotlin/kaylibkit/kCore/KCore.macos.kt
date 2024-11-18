package kaylibkit.kCore

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.osx.proc_pidpath
import platform.posix.F_OK
import platform.posix.PATH_MAX
import platform.posix.access
import platform.posix.getpid

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
            val pid = getpid() // Get the process ID of the current running process.
            val buffer = ByteArray(PATH_MAX) // Create a buffer to store the path to the executable file.

            // Use the proc_pidpath function to obtain the path to the executable file.
            // The result is the length of the path.
            val pathLength = proc_pidpath(pid, buffer.refTo(0), buffer.size.convert())

            // Check if the path length is less than or equal to zero, indicating an error. Throw exception if error is found
            if (pathLength <= 0) throw ResourcePathException("Failed to retrieve executable path.")
            val executablePath = buffer.toKString() // Convert the buffer to a Kotlin string to get the full path to the executable.

            // Find the index of the last slash character ('/') in the executable path, which separates the directory from the executable file name.
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