package kaylibkit.kEnums

enum class TraceLogLevel(val level: Int) {
    /**
     * Display all logs
     */
    ALL(0),

    /**
     * Trace logging, intended for internal use only
     */
    TRACE(1),

    /**
     * Debug logging, used for internal debugging, it should be disabled on release builds
     */
    DEBUG(2),

    /**
     * Info logging, used for program execution info
     */
    INFO(3),

    /**
     * Warning logging, used on recoverable failures
     */
    WARNING(4),

    /**
     * Error logging, used on unrecoverable failures
     */
    ERROR(5),

    /**
     * Fatal logging, used to abort program: exit(EXIT_FAILURE)
     */
    FATAL(6),

    /**
     * Disable logging
     */
    NONE(7)
}