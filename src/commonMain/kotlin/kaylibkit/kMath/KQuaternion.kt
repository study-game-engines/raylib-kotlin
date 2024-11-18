package kaylibkit.kMath

import kaylibc.*
import kotlinx.cinterop.*
import kotlin.math.*

// -- Module: kMath

//=======================================================//
// Quaternion DATA TYPE - Vector4 Alias
// NOTE: All functions are located in KVector4.kt
//=======================================================//

/**
 * Constructor function for [Quaternion]
 * @return [Quaternion]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kQuaternion(x: Float = 0F, y: Float = 0F, z: Float = 0F, w: Float = 0F, allocator: AutofreeScope): Quaternion {
    return allocator.alloc<Quaternion> {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
    }
}

///**
// * Calculate slerp-optimized interpolation between two [Quaternion]
// * @return [Quaternion]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Quaternion.nLerp(q2: Vector4, amount: Float): Quaternion {
//    val result: Quaternion = kQuaternion()
//
//    // QuaternionLerp(q1, q2, amount)
//
//    // QuaternionLerp(q1, q2, amount)
//    result.x = this.x + amount * (q2.x - this.x)
//    result.y = this.y + amount * (q2.y - this.y)
//    result.z = this.z + amount * (q2.z - this.z)
//    result.w = this.w + amount * (q2.w - this.w)
//
//    // QuaternionNormalize(q);
//
//    // QuaternionNormalize(q);
//    var length: Float = sqrt(result.x * result.x + result.y * result.y + result.z * result.z + result.w * result.w)
//    if (length == 0.0f) length = 1.0f
//    val ilength = 1.0f / length
//
//    result.x *= ilength
//    result.y *= ilength
//    result.z *= ilength
//    result.w *= ilength
//
//    return result
//}

///**
// * Calculates spherical linear interpolation between two [Quaternion]
// * @return [Quaternion]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Quaternion.sLerp(q2: Vector4, amount: Float): Quaternion {
//    var result: Quaternion = kQuaternion()
//
//    var cosHalfTheta: Float = this.x * q2.x + this.y * q2.y + this.z * q2.z + this.w * q2.w
//
//    if (cosHalfTheta < 0) {
//        q2.x = -q2.x
//        q2.y = -q2.y
//        q2.z = -q2.z
//        q2.w = -q2.w
//        cosHalfTheta = -cosHalfTheta
//    }
//
//    if (abs(cosHalfTheta) >= 1.0f) result = this else if (cosHalfTheta > 0.95f) result =
//        this.nLerp(q2, amount) else {
//        val halfTheta: Float = acos(cosHalfTheta)
//        val sinHalfTheta: Float = sqrt(1.0f - cosHalfTheta * cosHalfTheta)
//        if (abs(sinHalfTheta) < 0.001f) {
//            result.x = this.x * 0.5f + q2.x * 0.5f
//            result.y = this.y * 0.5f + q2.y * 0.5f
//            result.z = this.z * 0.5f + q2.z * 0.5f
//            result.w = this.w * 0.5f + q2.w * 0.5f
//        } else {
//            val ratioA: Float = sin((1 - amount) * halfTheta) / sinHalfTheta
//            val ratioB: Float = sin(amount * halfTheta) / sinHalfTheta
//            result.x = this.x * ratioA + q2.x * ratioB
//            result.y = this.y * ratioA + q2.y * ratioB
//            result.z = this.z * ratioA + q2.z * ratioB
//            result.w = this.w * ratioA + q2.w * ratioB
//        }
//    }
//
//    return result
//}

///**
// * Get the rotation angle and axis for a given [Quaternion]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Quaternion.toAxisAngle(outAxis: CPointer<Vector3>, outAngle: CPointer<FloatVar>) {
//    QuaternionToAxisAngle(this.readValue(), outAxis, outAngle)
//}
//
///**
// * Get the Euler angles equivalent to [Quaternion] (roll, pitch, yaw)
// * NOTE: Angles are returned in a Vector3 struct in radians
// * @return [Quaternion]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Quaternion.toEuler(): Vector3 {
//    val result: Vector3 = kVector3()
//
//    // Roll (x-axis rotation)
//
//    // Roll (x-axis rotation)
//    val x0: Float = 2.0f * (this.w * this.x + this.y * this.z)
//    val x1: Float = 1.0f - 2.0f * (this.x * this.x + this.y * this.y)
//    result.x = atan2(x0, x1)
//
//    // Pitch (y-axis rotation)
//
//    // Pitch (y-axis rotation)
//    var y0: Float = 2.0f * (this.w * this.y - this.z * this.x)
//    y0 = if (y0 > 1.0f) 1.0f else y0
//    y0 = if (y0 < -1.0f) -1.0f else y0
//    result.y = asin(y0)
//
//    // Yaw (z-axis rotation)
//
//    // Yaw (z-axis rotation)
//    val z0: Float = 2.0f * (this.w * this.z + this.x * this.y)
//    val z1: Float = 1.0f - 2.0f * (this.y * this.y + this.z * this.z)
//    result.z = atan2(z0, z1)
//
//    return result
//}

///**
// * Get the Euler angles equivalent to [Quaternion] (roll, pitch, yaw)
// * NOTE: Angles are returned in a [Vector3] struct in radians
// * @return [Quaternion]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Quaternion.transform(mat: Matrix): Quaternion {
//    val result: Quaternion = kQuaternion()
//
//    result.x = mat.m0 * this.x + mat.m4 * this.y + mat.m8 * this.z + mat.m12 * this.w
//    result.y = mat.m1 * this.x + mat.m5 * this.y + mat.m9 * this.z + mat.m13 * this.w
//    result.z = mat.m2 * this.x + mat.m6 * this.y + mat.m10 * this.z + mat.m14 * this.w
//    result.w = mat.m3 * this.x + mat.m7 * this.y + mat.m11 * this.z + mat.m15 * this.w
//
//    return result
//}