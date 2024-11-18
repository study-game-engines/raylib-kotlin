package kaylibkit.kEnums

/**
 * Shader location index
 */
enum class ShaderLocationIndex(val value: Int) {

    /**
     * Shader location: vertex attribute: position
     */
    VERTEX_POSITION_0(0),

    /**
     * Shader location: vertex attribute: texcoord01
     */
    VERTEX_TEXCOORD01_1(1),

    /**
     * Shader location: vertex attribute: texcoord02
     */
    VERTEX_TEXCOORD02_2(2),

    /**
     * Shader location: vertex attribute: normal
     */
    VERTEX_NORMAL(3),

    /**
     * Shader location: vertex attribute: tangent
     */
    VERTEX_TANGENT(4),

    /**
     * Shader location: vertex attribute: tangent
     */
    VERTEX_COLOR(5),

    /**
     * Shader location: matrix uniform: model-view-projection
     */
    MATRIX_MVP(6),

    /**
     * Shader location: matrix uniform: view (camera transform)
     */
    MATRIX_VIEW(7),

    /**
     * Shader location: matrix uniform: projection
     */
    MATRIX_PROJECTION(8),

    /**
     * Shader location: matrix uniform: model (transform)
     */
    MATRIX_MODEL(9),

    /**
     * Shader location: matrix uniform: normal
     */
    MATRIX_NORMAL(10),

    /**
     * Shader location: vector uniform: view
     */
    VECTOR_VIEW(11),

    /**
     * Shader location: vector uniform: diffuse color
     */
    COLOR_DIFFUSE(12),

    /**
     * Shader location: vector uniform: specular color
     */
    COLOR_SPECULAR(13),

    /**
     * Shader location: vector uniform: ambient color
     */
    COLOR_AMBIENT(14),

    /**
     * Shader location: sampler2d texture: albedo (same as: SHADER_LOC_MAP_DIFFUSE)
     */
    MAP_ALBEDO(15),

    /**
     * Shader location: sampler2d texture: metalness (same as: SHADER_LOC_MAP_SPECULAR)
     */
    MAP_METALNESS(16),

    /**
     * Shader location: sampler2d texture: normal
     */
    MAP_NORMAL(17),

    /**
     * Shader location: sampler2d texture: roughness
     */
    MAP_ROUGHNESS(18),

    /**
     * Shader location: sampler2d texture: occlusion
     */
    MAP_OCCLUSION(19),

    /**
     * Shader location: sampler2d texture: emission
     */
    MAP_EMISSION(20),

    /**
     * Shader location: sampler2d texture: height
     */
    MAP_HEIGHT(21),

    /**
     * Shader location: samplerCube texture: cubemap
     */
    MAP_CUBEMAP(22),

    /**
     * Shader location: samplerCube texture: irradiance
     */
    MAP_IRRADIANCE(23),

    /**
     * Shader location: samplerCube texture: prefilter
     */
    MAP_PREFILTER(24),

    /**
     * Shader location: sampler2d texture: brdf
     */
    MAP_BRDF(25),
}