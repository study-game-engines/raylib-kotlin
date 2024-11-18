package kaylibkit.kEnums

enum class ShaderUniformDataType(val value: Int) {
    /**
     * Shader uniform type: float
     */
    FLOAT(0),

    /**
     * Shader uniform type: vec2 (2 float)
     */
    VEC2(1),

    /**
     * Shader uniform type: vec3 (3 float)
     */
    VEC3(2),

    /**
     * Shader uniform type: vec4 (4 float)
     */
    VEC4(3),

    /**
     * Shader uniform type: int
     */
    INT(4),

    /**
     * Shader uniform type: ivec2 (2 int)
     */
    IVEC2(5),

    /**
     * Shader uniform type: ivec3 (3 int)
     */
    IVEC3(6),

    /**
     * Shader uniform type: ivec4 (4 int)
     */
    IVEC4(7),

    /**
     * Shader uniform type: sampler2d
     */
    SAMPLER2D(8),
}