package kaylibkit.kEnums

/**
 * Camera system modes
 */
enum class CameraMode(val value: Int) {
    /**
     * Custom camera
     */
    CUSTOM(0),

    /**
     * Free camera
     */
    FREE(1),

    /**
     * Orbital camera
     */
    ORBITAL(2),

    /**
     * First person camera
     */
    FIRST_PERSON(3),

    /**
     * Third person camera
     */
    THIRD_PERSON(4),
}