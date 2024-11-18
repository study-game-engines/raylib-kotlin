package kaylibkit.kEnums

/**
 * System/Window config flags
 * NOTE: Every bit registers one state (use it with bit masks)
 * By default all flags are set to 0
 */
enum class ConfigFlag(val value: UInt) {
    /**
     * Set to try enabling V-Sync on GPU
     */
    VSYNC_HINT(0x00000040u),

    /**
     * Set to run program in fullscreen
     */
    FULLSCREEN_MODE(0x00000002u),

    /**
     * Set to allow resizable window
     */
    WINDOW_RESIZABLE(0x00000004u),

    /**
     * Set to disable window decoration (frame and buttons)
     */
    WINDOW_UNDECORATED(0x00000008u),

    /**
     * Set to hide window
     */
    WINDOW_HIDDEN(0x00000080u),

    /**
     * Set to minimize window (iconify)
     */
    WINDOW_MINIMIZED(0x00000200u),

    /**
     * Set to maximize window (expanded to monitor)
     */
    WINDOW_MAXIMIZED(0x00000400u),

    /**
     * Set to window non focused
     */
    WINDOW_UNFOCUSED(0x00000800u),

    /**
     * Set to window always on top
     */
    WINDOW_TOPMOST(0x00001000u),

    /**
     * Set to allow windows running while minimized
     */
    WINDOW_ALWAYS_RUN(0x00000100u),

    /**
     * Set to allow transparent framebuffer
     */
    WINDOW_TRANSPARENT(0x00000010u),

    /**
     * Set to support HighDPI
     */
    WINDOW_HIGHDPI(0x00002000u),

    /**
     * Set to support mouse passthrough, only supported when FLAG_WINDOW_UNDECORATED
     */
    WINDOW_MOUSE_PASSTHROUGH(0x00004000u),

    /**
     * Set to try enabling MSAA 4X
     */
    MSAA_4X_HINT(0x00000020u),

    /**
     * Set to try enabling interlaced video format (for V3D)
     */
    INTERLACED_HINT(0x00010000u)
}