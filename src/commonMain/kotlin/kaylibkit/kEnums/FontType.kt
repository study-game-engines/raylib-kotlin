package kaylibkit.kEnums

/**
 * Font type defines generation method
 */
enum class FontType(val value: Int) {
    /**
     * Default font generation, anti-aliased
     */
    DEFAULT(0),

    /**
     * Bitmap font generation, no anti-aliasing
     */
    BITMAP(1),

    /**
     * SDF Font generation, requires external shader
     */
    SDF(2),
}