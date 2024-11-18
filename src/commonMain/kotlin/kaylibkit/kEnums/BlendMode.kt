package kaylibkit.kEnums

/**
 * Color blending modes (pre-defined)
 */
enum class BlendMode(val value: Int) {
    /**
     * Blend textures considering alpha (default)
     */
    ALPHA(0),

    /**
     * Blend textures adding colors
     */
    ADDITIVE(1),

    /**
     * Blend textures multiplying colors
     */
    MULTIPLIED(2),

    /**
     * Blend textures adding colors (alternative)
     */
    ADDCOLORS(3),

    /**
     * Blend textures subtracting colors (alternative)
     */
    SUBTRACT_COLORS(4),

    /**
     * Blend premultiplied textures considering alpha
     */
    ALPHA_PRE_MULTIPLIED(5),

    /**
     * Blend textures using custom src/dst factors (use rlSetBlendMode())
     */
    CUSTOM(6,)
}
