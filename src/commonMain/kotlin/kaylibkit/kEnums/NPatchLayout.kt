package kaylibkit.kEnums

/**
 * N-patch layout
 */
enum class NPatchLayout(val value: Int) {
    /**
     * Npatch layout: 3x3 tiles
     */
    NINE_PATCH(0),

    /**
     * Npatch layout: 1x3 tiles
     */
    THREE_PATCH_VERTICAL(1),

    /**
     * Npatch layout: 3x1 tiles
     */
    THREE_PATCH_HORIZONTAL(2),
}