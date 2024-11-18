package kaylibkit.kMath

import kaylibc.*
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

// -- Module: kMath

//=======================================================//
// MATH FUNCTIONS
//=======================================================//

/**
 * Clamp float value
 * @return [Float]
 */
inline fun clamp(value: Float, min: Float, max: Float) : Float = Clamp(value, min, max)

/**
 * Calculate linear interpolation between two floats
 * @return [Float]
 */
inline fun lerp(value: Float, start: Float, end: Float) : Float = Lerp(value, start, end)

/**
 * Normalize input value within input range
 * @return [Float]
 */
inline fun normalize(value: Float, start: Float, end: Float) : Float = Normalize(value, start, end)

/**
 * Remap input value within input range to output range
 * @return [Float]
 */
inline fun remap(value: Float, inputStart: Float, inputEnd: Float, outputStart: Float, outputEnd: Float): Float {

    return (value - inputStart) / (inputEnd - inputStart) * (outputEnd - outputStart) + outputStart
}

///**
// * Calculate [Quaternion] based on the rotation from one vector to another
// * @return [Quaternion]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun quaternionFromVector3toVector3(from: Vector3, to: Vector3): Quaternion {
////    val result: Quaternion = kQuaternion()
////
////    val cos2Theta = from.dot(to)
////
////    val cross: Vector3 = from.crossProduct(to)
////
////
////    result.x = cross.x
////    result.y = cross.y
////    result.z = cross.z
////    result.w = 1.0f + cos2Theta
////
////    var length: Quaternion = result.normalize()
////
////    return result
//
//    val result: Quaternion = kQuaternion()
//
//    val cos2Theta = from.x * to.x + from.y * to.y + from.z * to.z // Vector3DotProduct(from, to)
//
//    val cross: Vector3 = kVector3(
//        from.y * to.z - from.z * to.y,
//        from.z * to.x - from.x * to.z,
//        from.x * to.y - from.y * to.x
//    ) // Vector3CrossProduct(from, to)
//
//    result.x = cross.x
//    result.y = cross.y
//    result.z = cross.z
//    result.w = 1.0f + cos2Theta
//
//    // QuaternionNormalize(q);
//    // NOTE: Normalize to essentially nlerp the original and identity to 0.5
//
//    var length: Float = sqrt(result.x * result.x + result.y * result.y + result.z * result.z + result.w * result.w)
//    if (length == 0.0f) length = 1.0f
//    val ilength = 1.0f / length
//
//    result.x = result.x * ilength
//    result.y = result.y * ilength
//    result.z = result.z * ilength
//    result.w = result.w * ilength
//
//    return result
//}

///**
// * Get a [Quaternion] for a given rotation matrix
// * @return [Quaternion]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun quaternionFromMatrix(mat: Matrix): Quaternion {
//    val result: Quaternion = kQuaternion()
//
//    val fourWSquaredMinus1 = mat.m0 + mat.m5 + mat.m10
//    val fourXSquaredMinus1 = mat.m0 - mat.m5 - mat.m10
//    val fourYSquaredMinus1 = mat.m5 - mat.m0 - mat.m10
//    val fourZSquaredMinus1 = mat.m10 - mat.m0 - mat.m5
//
//    var biggestIndex = 0
//    var fourBiggestSquaredMinus1 = fourWSquaredMinus1
//    if (fourXSquaredMinus1 > fourBiggestSquaredMinus1) {
//        fourBiggestSquaredMinus1 = fourXSquaredMinus1
//        biggestIndex = 1
//    }
//
//    if (fourYSquaredMinus1 > fourBiggestSquaredMinus1) {
//        fourBiggestSquaredMinus1 = fourYSquaredMinus1
//        biggestIndex = 2
//    }
//
//    if (fourZSquaredMinus1 > fourBiggestSquaredMinus1) {
//        fourBiggestSquaredMinus1 = fourZSquaredMinus1
//        biggestIndex = 3
//    }
//
//    val biggestVal: Float = sqrt(fourBiggestSquaredMinus1 + 1.0f) * 0.5f
//    val mult = 0.25f / biggestVal
//
//    when (biggestIndex) {
//        0 -> {
//            result.w = biggestVal
//            result.x = (mat.m6 - mat.m9) * mult
//            result.y = (mat.m8 - mat.m2) * mult
//            result.z = (mat.m1 - mat.m4) * mult
//        }
//
//        1 -> {
//            result.x = biggestVal
//            result.w = (mat.m6 - mat.m9) * mult
//            result.y = (mat.m1 + mat.m4) * mult
//            result.z = (mat.m8 + mat.m2) * mult
//        }
//
//        2 -> {
//            result.y = biggestVal
//            result.w = (mat.m8 - mat.m2) * mult
//            result.x = (mat.m1 + mat.m4) * mult
//            result.z = (mat.m6 + mat.m9) * mult
//        }
//
//        3 -> {
//            result.z = biggestVal
//            result.w = (mat.m1 - mat.m4) * mult
//            result.x = (mat.m8 + mat.m2) * mult
//            result.y = (mat.m6 + mat.m9) * mult
//        }
//    }
//
//    return result
//}

///**
// * Get a [Matrix] for a given [Quaternion]
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun quaternionToMatrix(q: Quaternion): Matrix {
//    val result: Matrix = kMatrix(
//        1.0f, 0.0f, 0.0f, 0.0f,
//        0.0f, 1.0f, 0.0f, 0.0f,
//        0.0f, 0.0f, 1.0f, 0.0f,
//        0.0f, 0.0f, 0.0f, 1.0f
//    ) // MatrixIdentity()
//
//
//    val a2: Float = q.x * q.x
//    val b2: Float = q.y * q.y
//    val c2: Float = q.z * q.z
//    val ac: Float = q.x * q.z
//    val ab: Float = q.x * q.y
//    val bc: Float = q.y * q.z
//    val ad: Float = q.w * q.x
//    val bd: Float = q.w * q.y
//    val cd: Float = q.w * q.z
//
//    result.m0 = 1 - 2 * (b2 + c2)
//    result.m1 = 2 * (ab + cd)
//    result.m2 = 2 * (ac - bd)
//
//    result.m4 = 2 * (ab - cd)
//    result.m5 = 1 - 2 * (a2 + c2)
//    result.m6 = 2 * (bc + ad)
//
//    result.m8 = 2 * (ac + bd)
//    result.m9 = 2 * (bc - ad)
//    result.m10 = 1 - 2 * (a2 + b2)
//
//    return result
//}

///**
// * Get rotation [Quaternion] for an [angle] and [axis]
// *
// * NOTE: [angle] must be provided in radians
// * @return [Quaternion]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun quaternionFromAxisAngle(axis: Vector3, angle: Float): Quaternion {
//    var ang = angle
//    val result: Quaternion = kQuaternion(0.0f, 0.0f, 0.0f, 1.0f)
//
//    val axisLength: Float = sqrt(axis.x * axis.x + axis.y * axis.y + axis.z * axis.z)
//
//    if (axisLength != 0.0f) {
//        ang *= 0.5f
//        var length: Float
//        var ilength: Float
//
//        // Vector3Normalize(axis)
//        length = sqrt(axis.x * axis.x + axis.y * axis.y + axis.z * axis.z)
//        if (length == 0.0f) length = 1.0f
//        ilength = 1.0f / length
//        axis.x *= ilength
//        axis.y *= ilength
//        axis.z *= ilength
//        val sinres: Float = sin(angle)
//        val cosres: Float = cos(angle)
//        result.x = axis.x * sinres
//        result.y = axis.y * sinres
//        result.z = axis.z * sinres
//        result.w = cosres
//
//        // QuaternionNormalize(q);
//        length = sqrt(result.x * result.x + result.y * result.y + result.z * result.z + result.w * result.w)
//        if (length == 0.0f) length = 1.0f
//        ilength = 1.0f / length
//        result.x = result.x * ilength
//        result.y = result.y * ilength
//        result.z = result.z * ilength
//        result.w = result.w * ilength
//    }
//
//    return result
//}

///**
// * Get the [Quaternion] equivalent to Euler angles
// *
// * NOTE: Rotation order is ZYX
// * @return [Quaternion]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun quaternionFromEuler(pitch: Float, yaw: Float, roll: Float): Quaternion {
//    val result: Quaternion = kQuaternion()
//
//    val x0: Float = cos(pitch * 0.5f)
//    val x1: Float = sin(pitch * 0.5f)
//    val y0: Float = cos(yaw * 0.5f)
//    val y1: Float = sin(yaw * 0.5f)
//    val z0: Float = cos(roll * 0.5f)
//    val z1: Float = sin(roll * 0.5f)
//
//    result.x = x1 * y0 * z0 - x0 * y1 * z1
//    result.y = x0 * y1 * z0 + x1 * y0 * z1
//    result.z = x0 * y0 * z1 - x1 * y1 * z0
//    result.w = x0 * y0 * z0 + x1 * y1 * z1
//
//    return result
//}
