package kaylibkit.kEnums

/**
 * Cubemap layouts
 */
enum class CubemapLayout(val value: Int) {
    /**
     * Automatically detect layout type
     */
    AUTO_DETECT(0),

    /**
     * Layout is defined by a vertical line with faces
     */
    LINE_VERTICAL(1),

    /**
     * Layout is defined by a horizontal line with faces
     */
    LINE_HORIZONTAL(2),

    /**
     * Layout is defined by a 3x4 cross with cubemap face
     */
    CROSS_THREE_BY_FOUR(3),

    /**
     * Layout is defined by a 4x3 cross with cubemap face
     */
    CROSS_FOUR_BY_THREE(4),

    /**
     * Layout is defined by a panorama image (equirectangular map)
     */
    PANORAMA(5),
}