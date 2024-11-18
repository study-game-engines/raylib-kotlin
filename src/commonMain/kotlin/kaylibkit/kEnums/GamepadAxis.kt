package kaylibkit.kEnums

enum class GamepadAxis(val value: Int) {
    /**
     * Gamepad left stick X axis
     */
    LEFT_X(0),

    /**
     * Gamepad left stick Y axis
     */
    LEFT_Y(1),

    /**
     * Gamepad right stick X axis
     */
    RIGHT_X(2),

    /**
     * Gamepad right stick Y axis
     */
    RIGHT_Y(3),

    /**
     * Gamepad back trigger left, pressure level: [1..-1]
     */
    LEFT_TRIGGER(4),

    /**
     * Gamepad back trigger right, pressure level: [1..-1]
     */
    RIGHT_TRIGGER(5),
}