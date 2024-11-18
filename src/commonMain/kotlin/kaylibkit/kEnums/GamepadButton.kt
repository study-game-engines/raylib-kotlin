package kaylibkit.kEnums

enum class GamepadButton(val value: Int) {
    /**
     * Unknown button, just for error checking
     */
    UNKNOWN(0),

    /**
     * Gamepad left DPAD up button
     */
    LEFT_FACE_UP(1),

    /**
     * Gamepad left DPAD right button
     */
    LEFT_FACE_RIGHT(2),

    /**
     * Gamepad left DPAD down button
     */
    LEFT_FACE_DOWN(3),

    /**
     * Gamepad left DPAD left button
     */
    LEFT_FACE_LEFT(4),

    /**
     * Gamepad right button up (i.e. PS3: Triangle, Xbox: Y)
     */
    RIGHT_FACE_UP(5),

    /**
     * Gamepad right button right (i.e. PS3: Square, Xbox: X)
     */
    RIGHT_FACE_RIGHT(6),

    /**
     * Gamepad right button down (i.e. PS3: Cross, Xbox: A)
     */
    RIGHT_FACE_DOWN(7),

    /**
     * Gamepad right button left (i.e. PS3: Circle, Xbox: B)
     */
    RIGHT_FACE_LEFT(8),

    /**
     * Gamepad top/back trigger left (first), it could be a trailing button
     */
    LEFT_TRIGGER1(9),

    /**
     * Gamepad top/back trigger left (second), it could be a trailing button
     */
    LEFT_TRIGGER2(10),

    /**
     * Gamepad top/back trigger right (one), it could be a trailing button
     */
    RIGHT_TRIGGER1(11),

    /**
     * Gamepad top/back trigger right (second), it could be a trailing button
     */
    RIGHT_TRIGGER2(12),

    /**
     * Gamepad center buttons, left one (i.e. PS3: Select)
     */
    MIDDLE_LEFT(13),

    /**
     * Gamepad center buttons, middle one (i.e. PS3: PS, Xbox: XBOX)
     */
    MIDDLE(14),

    /**
     * Gamepad center buttons, right one (i.e. PS3: Start)
     */
    MIDDLE_RIGHT(15),

    /**
     * Gamepad joystick pressed button left
     */
    LEFT_THUMB(16),

    /**
     * Gamepad joystick pressed button right
     */
    RIGHT_THUMB(17),
}