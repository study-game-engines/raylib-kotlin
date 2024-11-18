package kaylibkit.kCamera

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kCamera

//=======================================================//
// CAMERA SYSTEM FUNCTIONS
//=======================================================//

/**
 * Returns the [camera] forward [Vector3] (normalized)
 * @return [Vector3]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getCameraForward(camera: Camera, allocator: AutofreeScope) : Vector3 {
    return GetCameraForward(camera.ptr).getPointer(allocator).pointed
}

/**
 * Returns the [camera] up [Vector3] (normalized)
 * Note: The up vector might not be perpendicular to the forward vector
 * @return [Vector3]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getCameraUp(camera: Camera, allocator: AutofreeScope) : Vector3 {
    return GetCameraUp(camera.ptr).getPointer(allocator).pointed
}

/**
 * Returns the [camera] right [Vector3] (normalized)
 * @return [Vector3]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getCameraRight(camera: Camera, allocator: AutofreeScope) : Vector3 {
    return GetCameraRight(camera.ptr).getPointer(allocator).pointed
}

/**
 * Moves the camera in its forward direction
 */
@OptIn(ExperimentalForeignApi::class)
inline fun cameraMoveForward(camera: Camera, distance: Float, moveInWorldPlane: Boolean) {
    CameraMoveForward(camera.ptr, distance, moveInWorldPlane)
}

/**
 * Moves the camera in its up direction
 */
@OptIn(ExperimentalForeignApi::class)
inline fun cameraMoveUp(camera: Camera, distance: Float) {
    CameraMoveUp(camera.ptr, distance)
}

/**
 * Moves the camera target in its current right direction
 */
@OptIn(ExperimentalForeignApi::class)
inline fun cameraMoveRight(camera: Camera, distance: Float, moveInWorldPlane: Boolean) {
    CameraMoveRight(camera.ptr, distance, moveInWorldPlane)
}

/**
 * Moves the camera position closer/farther to/from the camera target
 */
@OptIn(ExperimentalForeignApi::class)
inline fun cameraMoveToTarget(camera: Camera, delta: Float) {
    CameraMoveToTarget(camera.ptr, delta)
}

/**
 * Rotates the [camera] around its up vector
 * Yaw is "looking left and right"
 * If [rotateAroundTarget] is false, the [camera] rotates around its position
 * Note: [angle] must be provided in radians
 */
@OptIn(ExperimentalForeignApi::class)
inline fun cameraYaw(camera: Camera, angle: Float, rotateAroundTarget: Boolean) {
    CameraYaw(camera.ptr, angle, rotateAroundTarget)
}

/**
 * Rotates the [camera] around its right vector, pitch is "looking up and down"
 * lockView prevents [camera] overrotation (aka "somersaults")
 * [rotateAroundTarget] defines if rotation is around target or around its position
 * [rotateUp] rotates the up direction as well (typically only usefull in CAMERA_FREE)
 * NOTE: [angle] must be provided in radians
 */
@OptIn(ExperimentalForeignApi::class)
inline fun cameraPitch(camera: Camera, angle: Float, lockView: Boolean, rotateAroundTarget: Boolean, rotateUp: Boolean) {
    CameraPitch(camera.ptr, angle, lockView, rotateAroundTarget, rotateUp)
}

/**
 * Rotates the [camera] around its forward vector
 * Roll is "turning your head sideways to the left or right"
 * Note: [angle] must be provided in radians
 */
@OptIn(ExperimentalForeignApi::class)
inline fun cameraRoll(camera: Camera, angle: Float) {
    CameraRoll(camera.ptr, angle)
}

/**
 * Returns the [camera] view [Matrix]
 * @return [Matrix]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getCameraViewMatrix(camera: Camera, allocator: AutofreeScope) : Matrix {
    return GetCameraViewMatrix(camera.ptr).getPointer(allocator).pointed
}

/**
 * Returns the [camera] projection [Matrix]
 * @return [Matrix]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getCameraProjectionMatrix(camera: Camera, aspect: Float, allocator: AutofreeScope) : Matrix {
    return GetCameraProjectionMatrix(camera.ptr, aspect).getPointer(allocator).pointed
}

/**
 * Update [camera] position for selected [mode]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun updateCamera(camera: Camera, mode: kaylibkit.kEnums.CameraMode) {
    UpdateCamera(camera.ptr, mode.value)
}

/**
 * Update [camera] movement, [movement]/[rotation] values should be provided by user
 */
@OptIn(ExperimentalForeignApi::class)
inline fun updateCameraPro(camera: Camera, movement: Vector3, rotation: Vector3, zoom: Float) {
    UpdateCameraPro(camera.ptr, movement.readValue(), rotation.readValue(), zoom)
}