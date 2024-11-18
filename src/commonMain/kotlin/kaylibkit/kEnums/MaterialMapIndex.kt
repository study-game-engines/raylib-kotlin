package kaylibkit.kEnums

enum class MaterialMapIndex(val value: Int) {
    /**
     * Albedo material (same as: MATERIAL_MAP_DIFFUSE)
     */
    ALBEDO(0),

    /**
     * Metalness material (same as: MATERIAL_MAP_SPECULAR)
     */
    METALNESS(1),

    /**
     * Normal material
     */
    NORMAL(2),

    /**
     * Roughness material
     */
    ROUGHNESS(3),

    /**
     * Ambient occlusion material
     */
    OCCLUSION(4),

    /**
     * Emission material
     */
    EMISSION(5),

    /**
     * Heightmap material
     */
    HEIGHT(6),

    /**
     * Cubemap material (NOTE: Uses GL_TEXTURE_CUBE_MAP)
     */
    CUBEMAP(7),

    /**
     * Irradiance material (NOTE: Uses GL_TEXTURE_CUBE_MAP)
     */
    IRRADIANCE(8),

    /**
     * Prefilter material (NOTE: Uses GL_TEXTURE_CUBE_MAP)
     */
    PREFILTER(9),

    /**
     * Brdf material
     */
    BRDF(10),


    DIFFUSE(0),

    SPECULAR(1),
}