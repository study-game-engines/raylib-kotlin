package kaylibkit.kEnums

/**
 * Pixel formats
 * Support depends on OpenGL version and platform
 */
enum class PixelFormat(val value: Int) {
    /**
     * 8 bit per pixel (no alpha)
     */
    UNCOMPRESSED_GRAYSCALE(1),

    /**
     * 8*2 bpp (2 channels)
     */
    UNCOMPRESSED_GRAYALPHA(2),

    /**
     * 16 bpp
     */
    UNCOMPRESSED_R5G6B5(3),

    /**
     * 24 bpp
     */
    UNCOMPRESSED_R8G8B8(4),

    /**
     * 16 bpp (1 bit alpha)
     */
    UNCOMPRESSED_R5G5B5A1(5),

    /**
     * 16 bpp (4 bit alpha)
     */
    UNCOMPRESSED_R4G4B4A4(6),

    /**
     *32 bpp
     */
    UNCOMPRESSED_R8G8B8A8(7),

    /**
     * 32 bpp (1 channel - float)
     */
    UNCOMPRESSED_R32(8),

    /**
     * 32*3 bpp (3 channels - float)
     */
    UNCOMPRESSED_R32G32B32(9),

    /**
     * 32*4 bpp (4 channels - float)
     */
    UNCOMPRESSED_R32G32B32A32(10),

    /**
     * 4 bpp (no alpha)
     */
    COMPRESSED_DXT_1RGB(11),

    /**
     * 4 bpp (1 bit alpha)
     */
    COMPRESSED_DXT1_RGBA(12),

    /**
     * 8 bpp
     */
    COMPRESSED_DXT5_RGBA(13),

    /**
     * 4 bpp
     */
    COMPRESSED_ETC1_RGB(14),

    /**
     * 4 bpp
     */
    COMPRESSED_ETC2_RGB(15),

    /**
     * 8 bpp
     */
    COMPRESSED_ETC2EAC_RGBA(16),

    /**
     * 4 bpp
     */
    COMPRESSED_PVRT_RGB(17),

    /**
     * 4 bpp
     */
    COMPRESSED_PVRT_RGBA(18),

    /**
     * 8 bpp
     */
    COMPRESSED_ASTC4X4_RGBA(19),

    /**
     * 2 bpp
     */
    COMPRESSED_ASTC8X8_RGBA(20),
}