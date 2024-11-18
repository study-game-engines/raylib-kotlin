package kaylibkit.kGestures

import kaylibc.*
import kotlinx.cinterop.AutofreeScope
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.pointed

// -- Module: kGestures

//=======================================================//
// GESTURES AND TOUCH HANDLING FUNCTIONS
//=======================================================//

/**
 * Enable a set of gestures using [flags]
 */
inline fun setGesturesEnabled(flags: Gesture) {
    SetGesturesEnabled(flags)
}

/**
 * Check if a gesture have been detected
 * @return [Boolean]
 */
inline fun isGestureDetected(gesture: kaylibkit.kEnums.Gesture) : Boolean {
    return IsGestureDetected(gesture.value)
}

/**
 * Get latest detected gesture
 * @return [Int]
 */
inline fun getGestureDetected() : Int {
    return GetGestureDetected()
}

/**
 * Get gesture hold time in milliseconds
 * @return [Float]
 */
inline fun getGestureHoldDuration() : Float {
    return GetGestureHoldDuration()
}

/**
 * Get gesture drag [Vector2]
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getGestureDragVector(allocator: AutofreeScope) : Vector2 {
    return GetGestureDragVector().getPointer(allocator).pointed
}

/**
 * Get gesture drag angle
 * @return [Float]
 */
inline fun getGestureDragAngle() : Float {
    return GetGestureDragAngle()
}

/**
 * Get gesture pinch delta
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getGesturePinchVector(allocator: AutofreeScope) : Vector2 {
    return GetGesturePinchVector().getPointer(allocator).pointed
}

/**
 * Get gesture pinch angle
 * @return [Float]
 */
inline fun getGesturePinchAngle() : Float {
    return GetGesturePinchAngle()
}