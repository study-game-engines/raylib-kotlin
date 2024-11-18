package kaylibkit.kMath

import kaylibc.*
import kotlinx.cinterop.*
import platform.posix.tan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

// -- Module: kMath

//=======================================================//
// Matrix DATA TYPE
//=======================================================//

/**
 * Constructor function for [Matrix]
 * @return [Matrix]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kMatrix(
    m0: Float = 0F, m4: Float = 0F, m8: Float = 0F, m12: Float = 0F, m1: Float = 0F, m5: Float = 0F, m9: Float = 0F, m13: Float = 0F, m2: Float = 0F, m6: Float = 0F, m10: Float = 0F, m14: Float = 0F, m3: Float = 0F, m7: Float = 0F, m11: Float = 0F, m15: Float = 0F, allocator: AutofreeScope): Matrix {
    return allocator.alloc<Matrix> {
        this.m0 = m0
        this.m4 = m4
        this.m8 = m8
        this.m12 = m12
        this.m1 = m1
        this.m5 = m5
        this.m9 = m9
        this.m13 = m13
        this.m2 = m2
        this.m6 = m6
        this.m10 = m10
        this.m14 = m14
        this.m3 = m3
        this.m7 = m7
        this.m11 = m11
        this.m15 = m15
    }
}

/**
 * Compute [Matrix] determinant
 * @return [Float]
 */
inline fun Matrix.determinant(): Float {
    var result: Float

    // Cache the matrix values (speed optimization)
    val a00: Float = this.m0
    val a01: Float = this.m1
    val a02: Float = this.m2
    val a03: Float = this.m3
    val a10: Float = this.m4
    val a11: Float = this.m5
    val a12: Float = this.m6
    val a13: Float = this.m7
    val a20: Float = this.m8
    val a21: Float = this.m9
    val a22: Float = this.m10
    val a23: Float = this.m11
    val a30: Float = this.m12
    val a31: Float = this.m13
    val a32: Float = this.m14
    val a33: Float = this.m15

    result = a30 * a21 * a12 * a03 - a20 *
            a31 * a12 * a03 - a30 * a11 *
            a22 * a03 + a10 * a31 * a22 *
            a03 + a20 * a11 * a32 *
            a03 - a10 * a21 * a32 *
            a03 - a30 * a21 * a02 *
            a13 + a20 * a31 * a02 *
            a13 + a30 * a01 * a22 *
            a13 - a00 * a31 * a22 *
            a13 - a20 * a01 * a32 *
            a13 + a00 * a21 * a32 *
            a13 + a30 * a11 * a02 *
            a23 - a10 * a31 * a02 *
            a23 - a30 * a01 * a12 *
            a23 + a00 * a31 * a12 *
            a23 + a10 * a01 * a32 *
            a23 - a00 * a11 * a32 *
            a23 - a20 * a11 * a02 *
            a33 + a10 * a21 * a02 *
            a33 + a20 * a01 * a12 *
            a33 - a00 * a21 * a12 *
            a33 - a10 * a01 * a22 *
            a33 + a00 * a11 * a22 * a33

    return result
}

/**
 * Get the trace of the [Matrix] (sum of the values along the diagonal)
 * @return [Float]
 */
inline fun Matrix.trace(): Float {

    return this.m0 + this.m5 + this.m10 + this.m15
}

///**
// * Get the trace of the [Matrix] (sum of the values along the diagonal)
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.transpose(): Matrix {
//    val result: Matrix = kMatrix()
//
//    result.m0 = this.m0
//    result.m1 = this.m4
//    result.m2 = this.m8
//    result.m3 = this.m12
//    result.m4 = this.m1
//    result.m5 = this.m5
//    result.m6 = this.m9
//    result.m7 = this.m13
//    result.m8 = this.m2
//    result.m9 = this.m6
//    result.m10 = this.m10
//    result.m11 = this.m14
//    result.m12 = this.m3
//    result.m13 = this.m7
//    result.m14 = this.m11
//    result.m15 = this.m15
//
//    return result
//}
//
///**
// * Invert provided [Matrix]
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.invert(): Matrix {
//    val result: Matrix = kMatrix()
//
//    // Cache the matrix values (speed optimization)
//    val a00: Float = this.m0
//    val a01: Float = this.m1
//    val a02: Float = this.m2
//    val a03: Float = this.m3
//    val a10: Float = this.m4
//    val a11: Float = this.m5
//    val a12: Float = this.m6
//    val a13: Float = this.m7
//    val a20: Float = this.m8
//    val a21: Float = this.m9
//    val a22: Float = this.m10
//    val a23: Float = this.m11
//    val a30: Float = this.m12
//    val a31: Float = this.m13
//    val a32: Float = this.m14
//    val a33: Float = this.m15
//
//    val b00 = a00 * a11 - a01 * a10
//    val b01 = a00 * a12 - a02 * a10
//    val b02 = a00 * a13 - a03 * a10
//    val b03 = a01 * a12 - a02 * a11
//    val b04 = a01 * a13 - a03 * a11
//    val b05 = a02 * a13 - a03 * a12
//    val b06 = a20 * a31 - a21 * a30
//    val b07 = a20 * a32 - a22 * a30
//    val b08 = a20 * a33 - a23 * a30
//    val b09 = a21 * a32 - a22 * a31
//    val b10 = a21 * a33 - a23 * a31
//    val b11 = a22 * a33 - a23 * a32
//
//    // Calculate the invert determinant (inlined to avoid double-caching)
//
//    // Calculate the invert determinant (inlined to avoid double-caching)
//    val invDet = 1.0f / (b00 * b11 - b01 * b10 + b02 * b09 + b03 * b08 - b04 * b07 + b05 * b06)
//
//    result.m0 = (a11 * b11 - a12 * b10 + a13 * b09) * invDet
//    result.m1 = (-a01 * b11 + a02 * b10 - a03 * b09) * invDet
//    result.m2 = (a31 * b05 - a32 * b04 + a33 * b03) * invDet
//    result.m3 = (-a21 * b05 + a22 * b04 - a23 * b03) * invDet
//    result.m4 = (-a10 * b11 + a12 * b08 - a13 * b07) * invDet
//    result.m5 = (a00 * b11 - a02 * b08 + a03 * b07) * invDet
//    result.m6 = (-a30 * b05 + a32 * b02 - a33 * b01) * invDet
//    result.m7 = (a20 * b05 - a22 * b02 + a23 * b01) * invDet
//    result.m8 = (a10 * b10 - a11 * b08 + a13 * b06) * invDet
//    result.m9 = (-a00 * b10 + a01 * b08 - a03 * b06) * invDet
//    result.m10 = (a30 * b04 - a31 * b02 + a33 * b00) * invDet
//    result.m11 = (-a20 * b04 + a21 * b02 - a23 * b00) * invDet
//    result.m12 = (-a10 * b09 + a11 * b07 - a12 * b06) * invDet
//    result.m13 = (a00 * b09 - a01 * b07 + a02 * b06) * invDet
//    result.m14 = (-a30 * b03 + a31 * b01 - a32 * b00) * invDet
//    result.m15 = (a20 * b03 - a21 * b01 + a22 * b00) * invDet
//
//    return result
//}
//
///**
// * Get identity [Matrix]
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.identity(): Matrix {
//    return kMatrix(
//        1.0F, 0.0F, 0.0F, 0.0F,
//        0.0F, 1.0F, 0.0F, 0.0F,
//        0.0F, 0.0F, 1.0F, 0.0F,
//        0.0F, 0.0F, 0.0F, 1.0F
//    )
//}

/**
 * Add two matrices
 */
inline operator fun Matrix.plus(m: Matrix) {
    this.m0 + m.m0
    this.m1 + m.m1
    this.m2 + m.m2
    this.m3 + m.m3
    this.m4 + m.m4
    this.m5 + m.m5
    this.m6 + m.m6
    this.m7 + m.m7
    this.m8 + m.m8
    this.m9 + m.m9
    this.m10 + m.m10
    this.m11 + m.m11
    this.m12 + m.m12
    this.m13 + m.m13
    this.m14 + m.m14
    this.m15 + m.m15
}

/**
 * Subtract two matrices
 */
inline operator fun Matrix.minus(m: Matrix) {
    this.m0 - m.m0
    this.m1 - m.m1
    this.m2 - m.m2
    this.m3 - m.m3
    this.m4 - m.m4
    this.m5 - m.m5
    this.m6 - m.m6
    this.m7 - m.m7
    this.m8 - m.m8
    this.m9 - m.m9
    this.m10 - m.m10
    this.m11 - m.m11
    this.m12 - m.m12
    this.m13 - m.m13
    this.m14 - m.m14
    this.m15 - m.m15
}

/**
 * Multiply two matrices
 */
inline operator fun Matrix.times(m: Matrix) {
    this.m0 * m.m0
    this.m1 * m.m1
    this.m2 * m.m2
    this.m3 * m.m3
    this.m4 * m.m4
    this.m5 * m.m5
    this.m6 * m.m6
    this.m7 * m.m7
    this.m8 * m.m8
    this.m9 * m.m9
    this.m10 * m.m10
    this.m11 * m.m11
    this.m12 * m.m12
    this.m13 * m.m13
    this.m14 * m.m14
    this.m15 * m.m15
}

///**
// * Get translation [Matrix]
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.translate(x: Float, y: Float, z: Float): Matrix {
//
//    return kMatrix(
//        1.0f, 0.0f, 0.0f, x,
//        0.0f, 1.0f, 0.0f, y,
//        0.0f, 0.0f, 1.0f, z,
//        0.0f, 0.0f, 0.0f, 1.0f
//    )
//}

///**
// * Create rotation [Matrix] from [axis] and [angle]
// * NOTE: [angle] should be provided in radians
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.rotate(axis: Vector3, angle: Float): Matrix {
//    val result: Matrix = kMatrix()
//
//    var x = axis.x
//    var y = axis.y
//    var z = axis.z
//
//    val lengthSquared = x * x + y * y + z * z
//
//    if (lengthSquared != 1.0f && lengthSquared != 0.0f) {
//        val ilength: Float = 1.0f / sqrt(lengthSquared)
//        x *= ilength
//        y *= ilength
//        z *= ilength
//    }
//
//    val sinres: Float = sin(angle)
//    val cosres: Float = cos(angle)
//    val t = 1.0f - cosres
//
//    result.m0 = x * x * t + cosres
//    result.m1 = y * x * t + z * sinres
//    result.m2 = z * x * t - y * sinres
//    result.m3 = 0.0f
//
//    result.m4 = x * y * t - z * sinres
//    result.m5 = y * y * t + cosres
//    result.m6 = z * y * t + x * sinres
//    result.m7 = 0.0f
//
//    result.m8 = x * z * t + y * sinres
//    result.m9 = y * z * t - x * sinres
//    result.m10 = z * z * t + cosres
//    result.m11 = 0.0f
//
//    result.m12 = 0.0f
//    result.m13 = 0.0f
//    result.m14 = 0.0f
//    result.m15 = 1.0f
//
//    return result
//}

///**
// * Get x-rotation [Matrix]
// * NOTE: [angle] must be provided in radians
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.rotateX(angle: Float): Matrix {
//    val result: Matrix = kMatrix(
//        1.0f, 0.0f, 0.0f, 0.0f,
//        0.0f, 1.0f, 0.0f, 0.0f,
//        0.0f, 0.0f, 1.0f, 0.0f,
//        0.0f, 0.0f, 0.0f, 1.0f
//    ) // MatrixIdentity()
//
//
//    val cosres: Float = cos(angle)
//    val sinres: Float = sin(angle)
//
//    result.m5 = cosres
//    result.m6 = sinres
//    result.m9 = -sinres
//    result.m10 = cosres
//
//    return result
//}
//
///**
// * Get y-rotation [Matrix]
// * NOTE: [angle] must be provided in radians
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.rotateY(angle: Float): Matrix {
//    val result: Matrix = kMatrix(
//        1.0f, 0.0f, 0.0f, 0.0f,
//        0.0f, 1.0f, 0.0f, 0.0f,
//        0.0f, 0.0f, 1.0f, 0.0f,
//        0.0f, 0.0f, 0.0f, 1.0f
//    ) // MatrixIdentity()
//
//
//    val cosres: Float = cos(angle)
//    val sinres: Float = sin(angle)
//
//    result.m0 = cosres
//    result.m2 = -sinres
//    result.m8 = sinres
//    result.m10 = cosres
//
//    return result
//}

///**
// * Get z-rotation [Matrix]
// * NOTE: [angle] must be provided in radians
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.rotateZ(angle: Float): Matrix {
//    val result: Matrix = kMatrix(
//        1.0F, 0.0F, 0.0F, 0.0F,
//        0.0F, 1.0F, 0.0F, 0.0F,
//        0.0F, 0.0F, 1.0F, 0.0F,
//        0.0F, 0.0F, 0.0F, 1.0F
//    ) // MatrixIdentity()
//
//
//    val cosres: Float = cos(angle)
//    val sinres: Float = sin(angle)
//
//    result.m0 = cosres
//    result.m1 = sinres
//    result.m4 = -sinres
//    result.m5 = cosres
//
//    return result
//}

///**
// * Get XYZ-rotation [Matrix]
// * NOTE: [angle] must be provided in radians
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.rotateXYZ(angle: Vector3): Matrix {
//    val result: Matrix = kMatrix(
//        1.0F, 0.0F, 0.0F, 0.0F,
//        0.0F, 1.0F, 0.0F, 0.0F,
//        0.0F, 0.0F, 1.0F, 0.0F,
//        0.0F, 0.0F, 0.0F, 1.0F
//    ) // MatrixIdentity()
//
//
//    val cosz: Float = cos(-angle.z)
//    val sinz: Float = sin(-angle.z)
//    val cosy: Float = cos(-angle.y)
//    val siny: Float = sin(-angle.y)
//    val cosx: Float = cos(-angle.x)
//    val sinx: Float = sin(-angle.x)
//
//    result.m0 = cosz * cosy
//    result.m1 = cosz * siny * sinx - sinz * cosx
//    result.m2 = cosz * siny * cosx + sinz * sinx
//
//    result.m4 = sinz * cosy
//    result.m5 = sinz * siny * sinx + cosz * cosx
//    result.m6 = sinz * siny * cosx - cosz * sinx
//
//    result.m8 = -siny
//    result.m9 = cosy * sinx
//    result.m10 = cosy * cosx
//
//    return result
//}
//
///**
// * Get ZYX-rotation [Matrix]
// * NOTE: [angle] must be provided in radians
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.rotateZYX(angle: Vector3): Matrix {
//    val result: Matrix = kMatrix()
//
//    val cz: Float = cos(angle.z)
//    val sz: Float = sin(angle.z)
//    val cy: Float = cos(angle.y)
//    val sy: Float = sin(angle.y)
//    val cx: Float = cos(angle.x)
//    val sx: Float = sin(angle.x)
//
//    result.m0 = cz * cy
//    result.m4 = cz * sy * sx - cx * sz
//    result.m8 = sz * sx + cz * cx * sy
//    result.m12 = 0F
//
//    result.m1 = cy * sz
//    result.m5 = cz * cx + sz * sy * sx
//    result.m9 = cx * sz * sy - cz * sx
//    result.m13 = 0F
//
//    result.m2 = -sy
//    result.m6 = cy * sx
//    result.m10 = cy * cx
//    result.m14 = 0F
//
//    result.m3 = 0F
//    result.m7 = 0F
//    result.m11 = 0F
//    result.m15 = 1F
//
//    return result
//}

///**
// * Get scaling [Matrix]
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.scale(x: Float, y: Float, z: Float): Matrix {
//
//    return kMatrix(
//        x, 0.0F, 0.0F, 0.0F,
//        0.0F, y, 0.0F, 0.0F,
//        0.0F, 0.0F, z, 0.0F,
//        0.0F, 0.0F, 0.0F, 1.0F
//    )
//}
//
///**
// * Get perspective projection [Matrix]
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.frustum(left: Double, right: Double,bottom: Double, top: Double, near: Double, far: Double): Matrix {
//    val result: Matrix = kMatrix()
//
//    val rl = (right - left).toFloat()
//    val tb = (top - bottom).toFloat()
//    val fn = (far - near).toFloat()
//
//    result.m0 = near.toFloat() * 2.0f / rl
//    result.m1 = 0.0f
//    result.m2 = 0.0f
//    result.m3 = 0.0f
//
//    result.m4 = 0.0f
//    result.m5 = near.toFloat() * 2.0f / tb
//    result.m6 = 0.0f
//    result.m7 = 0.0f
//
//    result.m8 = (right.toFloat() + left.toFloat()) / rl
//    result.m9 = (top.toFloat() + bottom.toFloat()) / tb
//    result.m10 = -(far.toFloat() + near.toFloat()) / fn
//    result.m11 = -1.0f
//
//    result.m12 = 0.0f
//    result.m13 = 0.0f
//    result.m14 = -(far.toFloat() * near.toFloat() * 2.0f) / fn
//    result.m15 = 0.0f
//
//    return result
//}

///**
// * Get perspective projection [Matrix]
// * NOTE: [fovy] angle must be provided in radians
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.perspective(fovy: Double, aspect: Double, near: Double, far: Double): Matrix {
//    val result: Matrix = kMatrix()
//
//    val top: Double = near * tan(fovy * 0.5)
//    val bottom = -top
//    val right = top * aspect
//    val left = -right
//
//    // MatrixFrustum(-right, right, -top, top, near, far);
//
//    // MatrixFrustum(-right, right, -top, top, near, far);
//    val rl = (right - left).toFloat()
//    val tb = (top - bottom).toFloat()
//    val fn = (far - near).toFloat()
//
//    result.m0 = near.toFloat() * 2.0f / rl
//    result.m5 = near.toFloat() * 2.0f / tb
//    result.m8 = (right.toFloat() + left.toFloat()) / rl
//    result.m9 = (top.toFloat() + bottom.toFloat()) / tb
//    result.m10 = -(far.toFloat() + near.toFloat()) / fn
//    result.m11 = -1.0f
//    result.m14 = -(far.toFloat() * near.toFloat() * 2.0f) / fn
//
//    return result
//}

///**
// * Get orthographic projection [Matrix]
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.ortho(left: Double, right: Double,bottom: Double, top: Double, near: Double, far: Double): Matrix {
//    val result: Matrix = kMatrix()
//
//    val rl = (right - left).toFloat()
//    val tb = (top - bottom).toFloat()
//    val fn = (far - near).toFloat()
//
//    result.m0 = 2.0f / rl
//    result.m1 = 0.0f
//    result.m2 = 0.0f
//    result.m3 = 0.0f
//    result.m4 = 0.0f
//    result.m5 = 2.0f / tb
//    result.m6 = 0.0f
//    result.m7 = 0.0f
//    result.m8 = 0.0f
//    result.m9 = 0.0f
//    result.m10 = -2.0f / fn
//    result.m11 = 0.0f
//    result.m12 = -(left.toFloat() + right.toFloat()) / rl
//    result.m13 = -(top.toFloat() + bottom.toFloat()) / tb
//    result.m14 = -(far.toFloat() + near.toFloat()) / fn
//    result.m15 = 1.0f
//
//    return result
//}

///**
// * Get camera look-at [Matrix] (view matrix)
// * @return [Matrix]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.lookAt(eye: Vector3, target: Vector3, up: Vector3): Matrix {
//
//    val result: Matrix = kMatrix()
//
//    var length: Float
//    var ilength: Float
//
//    // Vector3Subtract(eye, target)
//
//    // Vector3Subtract(eye, target)
//    val vz: Vector3 = kVector3(eye.x - target.x, eye.y - target.y, eye.z - target.z)
//
//    // Vector3Normalize(vz)
//
//    // Vector3Normalize(vz)
//    var v = vz
//    length = sqrt(v.x * v.x + v.y * v.y + v.z * v.z)
//    if (length == 0.0f) length = 1.0f
//    ilength = 1.0f / length
//    vz.x *= ilength
//    vz.y *= ilength
//    vz.z *= ilength
//
//    // Vector3CrossProduct(up, vz)
//
//    // Vector3CrossProduct(up, vz)
//    val vx: Vector3 = kVector3(up.y * vz.z - up.z * vz.y, up.z * vz.x - up.x * vz.z, up.x * vz.y - up.y * vz.x)
//
//    // Vector3Normalize(x)
//
//    // Vector3Normalize(x)
//    v = vx
//    length = sqrt(v.x * v.x + v.y * v.y + v.z * v.z)
//    if (length == 0.0f) length = 1.0f
//    ilength = 1.0f / length
//    vx.x *= ilength
//    vx.y *= ilength
//    vx.z *= ilength
//
//    // Vector3CrossProduct(vz, vx)
//
//    // Vector3CrossProduct(vz, vx)
//    val vy: Vector3 = kVector3(vz.y * vx.z - vz.z * vx.y, vz.z * vx.x - vz.x * vx.z, vz.x * vx.y - vz.y * vx.x)
//
//    result.m0 = vx.x
//    result.m1 = vy.x
//    result.m2 = vz.x
//    result.m3 = 0.0f
//    result.m4 = vx.y
//    result.m5 = vy.y
//    result.m6 = vz.y
//    result.m7 = 0.0f
//    result.m8 = vx.z
//    result.m9 = vy.z
//    result.m10 = vz.z
//    result.m11 = 0.0f
//    result.m12 = -(vx.x * eye.x + vx.y * eye.y + vx.z * eye.z) // Vector3DotProduct(vx, eye)
//
//    result.m13 = -(vy.x * eye.x + vy.y * eye.y + vy.z * eye.z) // Vector3DotProduct(vy, eye)
//
//    result.m14 = -(vz.x * eye.x + vz.y * eye.y + vz.z * eye.z) // Vector3DotProduct(vz, eye)
//
//    result.m15 = 1.0f
//
//    return result
//}

///**
// * Get float array of [Matrix] data
// * @return [float16]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Matrix.toFloatV(): float16 {
//    return MatrixToFloatV(this.readValue()).getPointer(MemScope()).pointed
//}

/**
 * Set value of a Matrix with another provided value.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Matrix.set(other: Matrix) {
    this.m0 - other.m0
    this.m1 - other.m1
    this.m2 - other.m2
    this.m3 - other.m3
    this.m4 - other.m4
    this.m5 - other.m5
    this.m6 - other.m6
    this.m7 - other.m7
    this.m8 - other.m8
    this.m9 - other.m9
    this.m10 - other.m10
    this.m11 - other.m11
    this.m12 - other.m12
    this.m13 - other.m13
    this.m14 - other.m14
    this.m15 - other.m15
}