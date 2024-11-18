package kaylibkit.kEnums

/**
 * Gestures
 * It could be used as flags to enable only some gestures
 */
enum class Gesture(val value: Int) {
    NONE(0),
    TAP(1,),
    DOUBLE_TAP(2),
    HOLD(4),
    DRAG(8),
    SWIPE_RIGHT(16),
    SWIPE_LEFT(32),
    SWIPE_UP(64),
    SWIPE_DOWN(128),
    PINCH_IN(256),
    PINCH_OUT(512),
}