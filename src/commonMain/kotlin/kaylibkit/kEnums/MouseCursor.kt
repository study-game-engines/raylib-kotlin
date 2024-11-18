package kaylibkit.kEnums

/**
 * Mouse Cursors
 */
enum class MouseCursor(val value: Int) {
    /**
     * Default pointer shape
     */
    DEFAULT(0),

    /**
     * Arrow shape
     */
    ARROW(1),

    /**
     * Text writing cursor shape
     */
    IBEAM(2),

    /**
     * Cross shape
     */
    CROSSHAIR(3),

    /**
     * Pointing hand cursor
     */
    POINTINGHAND(4),

    /**
     * The horizontal resize/move arrow shape
     */
    RESIZE_EW(5),

    /**
     * The vertical resize/move arrow shape
     */
    RESIZE_NS(6),

    /**
     * The top-left to bottom-right diagonal resize/move arrow shape
     */
    RESIZE_NWSE(7),

    /**
     * The top-right to bottom-left diagonal resize/move arrow shape
     */
    RESIZENESW(8),

    /**
     * The omni-directional resize/move cursor shape
     */
    RESIZE_ALL(9),

    /**
     * The operation-not-allowed shape
     */
    NOT_ALLOWED(10),
}