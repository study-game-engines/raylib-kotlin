package kaylibkit.kMath

import kaylibc.*
import kotlinx.cinterop.*
import kotlin.math.*

// -- Module: kMath

//=======================================================//
// VECTOR3 DATA TYPE
//=======================================================//

/**
 * Constructor function for [Vector3]
 * @return [Vector3]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kVector3(x: Float = 0F, y: Float = 0F, z: Float = 0F, allocator: AutofreeScope?): Vector3 {
    if (allocator != null) {
        return allocator.alloc<Vector3> {
            this.x = x
            this.y = y
            this.z = z
        }
    } else {
        throw IllegalArgumentException("ERROR: No allocator passed to the parameter! Unable to allocate!")
    }
}

/**
 * [Vector3] with components value 0
 * @return zero values Y, X and Z of passed [Vector3]
 */
inline fun Vector3.setZero() : Vector3 = this.apply { this.x = 0F; this.y = 0F; this.z = 0F }

/**
 * [Vector3] with components value 1
 * @return values of 1 in Y and X of passed [Vector3]
 */
inline fun Vector3.setOne() : Vector3 = this.apply { this.x = 1F; this.y = 1F; this.z = 1F }

/**
 * Calculate [Vector3] length
 */
inline fun Vector3.length() : Float = sqrt((this.x*this.x + this.y*this.y + this.z*this.z))

/**
 * Calculate [Vector3] square length
 */
inline fun Vector3.lengthSqr() = (this.x*this.x + this.y*this.y + this.z*this.z)

/**
 * Calculate two [Vector3] dot product
 */
inline fun Vector3.dot(v3: Vector3) : Float = (this.x*v3.x + this.y*v3.y + this.z*v3.z)

/**
 * Calculate distance between two [Vector3]
 */
inline fun Vector3.distance(v3: Vector3) : Float = sqrt((this.x - v3.x)*(this.x - v3.x) + (this.y - v3.y)*(this.y - v3.y) + (this.z - v3.z)*(this.z - v3.z))

/**
 * Calculate square distance between two [Vector3]
 */
inline fun Vector3.distanceSqr(v3: Vector3) : Float = ((this.x - v3.x)*(this.x - v3.x) + (this.y - v3.y)*(this.y - v3.y) + (this.z - v3.z)*(this.z - v3.z))

///**
// * Calculate angle from two [Vector3]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.angle(v3: Vector3) : Float {
//    val result: Float
//
//    val cross = kVector3(this.y*v3.z - this.z*v3.y, this.z*v3.x - this.x*v3.z, this.x*v3.y - this.y*v3.x )
//    val len = sqrt(cross.x*cross.x + cross.y*cross.y + cross.z*cross.z)
//    val dot = (this.x*v3.x + this.y*v3.y + this.z*v3.z)
//
//    result = atan2(len, dot)
//    return result
//}

///**
// * Calculate two [Vector3] cross product
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.crossProduct(v2: Vector3): Vector3 {
//    return kVector3(this.y * v2.z - this.z * v2.y, this.z * v2.x - this.x * v2.z, this.x * v2.y - this.y * v2.x)
//}

/**
 * Scale [Vector3] (multiply by value)
 */
inline fun Vector3.times(scale: Float) {
    this.x *= scale
    this.y *= scale
    this.z *= scale
}

/**
 * Add two [Vector3]
 */
inline fun Vector3.add(v3: Vector3) {
    this.x += v3.x
    this.y += v3.y
    this.z += v3.z
}

/**
 * Multiply [Vector3] by [Vector3]
 */
inline fun Vector3.multiply(v3: Vector3) {
    this.x *= v3.x
    this.y *= v3.y
    this.z *= v3.z
}

/**
 * Add [Vector3] and float value
 */
inline fun Vector3.addValue(add: Float)  {
    this.x += add
    this.y += add
    this.z += add
}

/**
 * Subtract two [Vector3]
 */
inline fun Vector3.subtract(v3: Vector3) {
    this.x -= v3.x
    this.y -= v3.y
    this.z -= v3.z
}

/**
 * Subtract [Vector3] by float value
 */
inline fun Vector3.subtractValue(sub: Float)  {
    this.x -= sub
    this.y -= sub
    this.z -= sub
}

/**
 * Get reminder of [Vector3]
 */
inline fun Vector3.rem(v3: Vector3)  {
    this.x %= v3.x
    this.y %= v3.y
    this.z %= v3.z
}

/**
 * Negate [Vector3]
 */
inline fun Vector3.negate() {
    this.x = this.x.unaryMinus()
    this.y = this.y.unaryMinus()
    this.z = this.z.unaryMinus()
}

/**
 * Normalize provided [Vector3]
 */
inline fun Vector3.normalize() : Vector3 {
    val result = this
    var length = sqrt(this.x*this.x + this.y*this.y + this.z*this.z)

    if (length == 0F) { length = 1F }

    val iLength = 1F/length
    result.x = this.x*iLength
    result.y = this.y*iLength
    result.z = this.z*iLength
    return result
}

/**
 * Transforms a [Vector3] by a given [Matrix]
 */
inline fun Vector3.transform(mat: Matrix) : Vector3 {
    val result = this

    val x = this.x
    val y = this.y
    val z = this.z

    result.x = mat.m0*x + mat.m4*y + mat.m8*z + mat.m12
    result.y = mat.m1*x + mat.m5*y + mat.m9*z + mat.m13
    result.z = mat.m2*x + mat.m6*y + mat.m10*z + mat.m14;

    return result
}

///**
// * Transform a [Vector3] by [Quaternion] rotation
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.rotateByQuaternion(q: Quaternion): Vector3 {
//    val result: Vector3 = kVector3()
//
//    result.x =
//        this.x * (q.x * q.x + q.w * q.w - q.y * q.y - q.z * q.z) + this.y * (2 * q.x * q.y - 2 * q.w * q.z) + this.z * (2 * q.x * q.z + 2 * q.w * q.y)
//    result.y =
//        this.x * (2 * q.w * q.z + 2 * q.x * q.y) + this.y * (q.w * q.w - q.x * q.x + q.y * q.y - q.z * q.z) + this.z * (-2 * q.w * q.x + 2 * q.y * q.z)
//    result.z =
//        this.x * (-2 * q.w * q.y + 2 * q.x * q.z) + this.y * (2 * q.w * q.x + 2 * q.y * q.z) + this.z * (q.w * q.w - q.x * q.x - q.y * q.y + q.z * q.z)
//
//    return result
//}

///**
// * Rotates a [Vector3] around an [axis]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.rotateByAxisAngle(axis: Vector3, angle: Float): Vector3 {
//    // Using Euler-Rodrigues Formula
//    // Ref.: https://en.wikipedia.org/w/index.php?title=Euler%E2%80%93Rodrigues_formula
//
//    // Using Euler-Rodrigues Formula
//    // Ref.: https://en.wikipedia.org/w/index.php?title=Euler%E2%80%93Rodrigues_formula
//    val result: Vector3 = this
//    var ang = angle
//
//    // Vector3Normalize(axis);
//
//    // Vector3Normalize(axis);
//    var length: Float = sqrt(axis.x * axis.x + axis.y * axis.y + axis.z * axis.z)
//    if (length == 0.0f) length = 1.0f
//    val ilength = 1.0f / length
//    axis.x *= ilength
//    axis.y *= ilength
//    axis.z *= ilength
//
//    ang /= 2.0f
//    var a: Float = sin(angle)
//    val b = axis.x * a
//    val c = axis.y * a
//    val d = axis.z * a
//    a = cos(angle)
//    val w: Vector3 = kVector3(b, c, d)
//
//    // Vector3CrossProduct(w, v)
//
//    // Vector3CrossProduct(w, v)
//    val wv: Vector3 = kVector3(w.y * this.z - w.z * this.y, w.z * this.x - w.x * this.z, w.x * this.y - w.y * this.x)
//
//    // Vector3CrossProduct(w, wv)
//
//    // Vector3CrossProduct(w, wv)
//    val wwv: Vector3 = kVector3(w.y * wv.z - w.z * wv.y, w.z * wv.x - w.x * wv.z, w.x * wv.y - w.y * wv.x)
//
//    // Vector3Scale(wv, 2 * a)
//
//    // Vector3Scale(wv, 2 * a)
//    a *= 2f
//    wv.x *= a
//    wv.y *= a
//    wv.z *= a
//
//    // Vector3Scale(wwv, 2)
//
//    // Vector3Scale(wwv, 2)
//    wwv.x *= 2
//    wwv.y *= 2
//    wwv.z *= 2
//
//    result.x += wv.x
//    result.y += wv.y
//    result.z += wv.z
//
//    result.x += wwv.x
//    result.y += wwv.y
//    result.z += wwv.z
//
//    return result
//}

/**
 * Calculate linear interpolation between two [Vector3]
 */
inline fun Vector3.lerp(v3: Vector3, amount: Float) : Vector3 {
    val result = this

    result.x = this.x + amount*(v3.x - this.x)
    result.y = this.y + amount*(v3.y - this.y)
    result.z = this.z + amount*(v3.z - this.z)

    return result
}

/**
 * Orthonormalize provided [Vector3]
 * Makes vectors normalized and orthogonal to each other
 * Gram-Schmidt function implementation
 */
@OptIn(ExperimentalForeignApi::class)
inline fun Vector3.orthoNormalize(v3: CPointer<Vector3>) {
    return Vector3OrthoNormalize(this.ptr, v3)
}


///**
// * Calculate reflected [Vector3] to normal
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.reflect(normal: Vector3) : Vector3 {
//    val result: Vector3 = kVector3()
//
//    // I is the original vector
//    // N is the normal of the incident plane
//    // R = I - (2*N*(DotProduct[I, N]))
//
//
//    // I is the original vector
//    // N is the normal of the incident plane
//    // R = I - (2*N*(DotProduct[I, N]))
//    val dotProduct: Float = this.x * normal.x + this.y * normal.y + this.z * normal.z
//
//    result.x = this.x - 2.0f * normal.x * dotProduct
//    result.y = this.y - 2.0f * normal.y * dotProduct
//    result.z = this.z - 2.0f * normal.z * dotProduct
//
//    return result
//}

///**
// * Calculate one [Vector3] perpendicular vector
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.perpendicular() : Vector3 {
//    val result: Vector3 = kVector3()
//
//    var min = abs(this.x)
//    var cardinalAxis: Vector3 = kVector3(1.0f, 0.0f, 0.0f)
//
//    if (abs(this.y) < min) {
//        min = abs(this.y)
//        val tmp: Vector3 = kVector3(0.0f, 1.0f, 0.0f)
//        cardinalAxis = tmp
//    }
//
//    if (abs(this.z) < min) {
//        val tmp: Vector3 = kVector3(0.0f, 0.0f, 1.0f)
//        cardinalAxis = tmp
//    }
//
//    // Cross product between vectors
//
//    // Cross product between vectors
//    result.x = this.y * cardinalAxis.z - this.z * cardinalAxis.y
//    result.y = this.z * cardinalAxis.x - this.x * cardinalAxis.z
//    result.z = this.x * cardinalAxis.y - this.y * cardinalAxis.x
//
//    return result
//}

///**
// * Invert the given [Vector3]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.invert(): Vector3 {
//
//    return kVector3(1.0f / this.x, 1.0f / this.y, 1.0f / this.z)
//}


///**
// * Get min value for each pair of components
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.min(v3: Vector3) : Vector3 {
//    val result: Vector3 = kVector3()
//
//    result.x = min(this.x, v3.x)
//    result.y = min(this.y, v3.y)
//    result.z = min(this.z, v3.z)
//
//    return result
//}

///**
// * Get max value for each pair of components
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.max(v3: Vector3) : Vector3 {
//    val result: Vector3 = kVector3()
//
//    result.x = max(this.x, v3.x)
//    result.y = max(this.y, v3.y)
//    result.z = max(this.z, v3.z)
//
//    return result
//}

///**
// * Get max value for each pair of components
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.barycenter(a: Vector3, b: Vector3, c: Vector3) : Vector3 {
//    val result: Vector3 = kVector3()
//
//    val v0: Vector3 = kVector3(b.x - a.x, b.y - a.y, b.z - a.z) // Vector3Subtract(b, a)
//
//    val v1: Vector3 = kVector3(c.x - a.x, c.y - a.y, c.z - a.z) // Vector3Subtract(c, a)
//
//    val v2: Vector3 = kVector3(this.x - a.x, this.y - a.y, this.z - a.z) // Vector3Subtract(p, a)
//
//    val d00 = v0.x * v0.x + v0.y * v0.y + v0.z * v0.z // Vector3DotProduct(v0, v0)
//
//    val d01 = v0.x * v1.x + v0.y * v1.y + v0.z * v1.z // Vector3DotProduct(v0, v1)
//
//    val d11 = v1.x * v1.x + v1.y * v1.y + v1.z * v1.z // Vector3DotProduct(v1, v1)
//
//    val d20 = v2.x * v0.x + v2.y * v0.y + v2.z * v0.z // Vector3DotProduct(v2, v0)
//
//    val d21 = v2.x * v1.x + v2.y * v1.y + v2.z * v1.z // Vector3DotProduct(v2, v1)
//
//
//    val denom = d00 * d11 - d01 * d01
//
//    result.y = (d11 * d20 - d01 * d21) / denom
//    result.z = (d00 * d21 - d01 * d20) / denom
//    result.x = 1.0f - (result.z + result.y)
//
//    return result
//}

///**
// * Projects a [Vector3] from screen space into object space
// * NOTE: We are avoiding calling other raymath functions despite available
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.unproject(projection: Matrix, view: Matrix) : Vector3 {
//    val result: Vector3 = kVector3()
//
//    // Calculate unproject matrix (multiply view patrix by projection matrix) and invert it
//
//    // Calculate unproject matrix (multiply view patrix by projection matrix) and invert it
//    val matViewProj: Matrix = kMatrix( // MatrixMultiply(view, projection);
//        view.m0 * projection.m0 + view.m1 * projection.m4 + view.m2 * projection.m8 + view.m3 * projection.m12,
//        view.m0 * projection.m1 + view.m1 * projection.m5 + view.m2 * projection.m9 + view.m3 * projection.m13,
//        view.m0 * projection.m2 + view.m1 * projection.m6 + view.m2 * projection.m10 + view.m3 * projection.m14,
//        view.m0 * projection.m3 + view.m1 * projection.m7 + view.m2 * projection.m11 + view.m3 * projection.m15,
//        view.m4 * projection.m0 + view.m5 * projection.m4 + view.m6 * projection.m8 + view.m7 * projection.m12,
//        view.m4 * projection.m1 + view.m5 * projection.m5 + view.m6 * projection.m9 + view.m7 * projection.m13,
//        view.m4 * projection.m2 + view.m5 * projection.m6 + view.m6 * projection.m10 + view.m7 * projection.m14,
//        view.m4 * projection.m3 + view.m5 * projection.m7 + view.m6 * projection.m11 + view.m7 * projection.m15,
//        view.m8 * projection.m0 + view.m9 * projection.m4 + view.m10 * projection.m8 + view.m11 * projection.m12,
//        view.m8 * projection.m1 + view.m9 * projection.m5 + view.m10 * projection.m9 + view.m11 * projection.m13,
//        view.m8 * projection.m2 + view.m9 * projection.m6 + view.m10 * projection.m10 + view.m11 * projection.m14,
//        view.m8 * projection.m3 + view.m9 * projection.m7 + view.m10 * projection.m11 + view.m11 * projection.m15,
//        view.m12 * projection.m0 + view.m13 * projection.m4 + view.m14 * projection.m8 + view.m15 * projection.m12,
//        view.m12 * projection.m1 + view.m13 * projection.m5 + view.m14 * projection.m9 + view.m15 * projection.m13,
//        view.m12 * projection.m2 + view.m13 * projection.m6 + view.m14 * projection.m10 + view.m15 * projection.m14,
//        view.m12 * projection.m3 + view.m13 * projection.m7 + view.m14 * projection.m11 + view.m15 * projection.m15
//    )
//
//    // Calculate inverted matrix -> MatrixInvert(matViewProj);
//    // Cache the matrix values (speed optimization)
//
//    // Calculate inverted matrix -> MatrixInvert(matViewProj);
//    // Cache the matrix values (speed optimization)
//    val a00 = matViewProj.m0
//    val a01 = matViewProj.m1
//    val a02 = matViewProj.m2
//    val a03 = matViewProj.m3
//    val a10 = matViewProj.m4
//    val a11 = matViewProj.m5
//    val a12 = matViewProj.m6
//    val a13 = matViewProj.m7
//    val a20 = matViewProj.m8
//    val a21 = matViewProj.m9
//    val a22 = matViewProj.m10
//    val a23 = matViewProj.m11
//    val a30 = matViewProj.m12
//    val a31 = matViewProj.m13
//    val a32 = matViewProj.m14
//    val a33 = matViewProj.m15
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
//    val matViewProjInv: Matrix = kMatrix(
//        (a11 * b11 - a12 * b10 + a13 * b09) * invDet,
//        (-a01 * b11 + a02 * b10 - a03 * b09) * invDet,
//        (a31 * b05 - a32 * b04 + a33 * b03) * invDet,
//        (-a21 * b05 + a22 * b04 - a23 * b03) * invDet,
//        (-a10 * b11 + a12 * b08 - a13 * b07) * invDet,
//        (a00 * b11 - a02 * b08 + a03 * b07) * invDet,
//        (-a30 * b05 + a32 * b02 - a33 * b01) * invDet,
//        (a20 * b05 - a22 * b02 + a23 * b01) * invDet,
//        (a10 * b10 - a11 * b08 + a13 * b06) * invDet,
//        (-a00 * b10 + a01 * b08 - a03 * b06) * invDet,
//        (a30 * b04 - a31 * b02 + a33 * b00) * invDet,
//        (-a20 * b04 + a21 * b02 - a23 * b00) * invDet,
//        (-a10 * b09 + a11 * b07 - a12 * b06) * invDet,
//        (a00 * b09 - a01 * b07 + a02 * b06) * invDet,
//        (-a30 * b03 + a31 * b01 - a32 * b00) * invDet,
//        (a20 * b03 - a21 * b01 + a22 * b00) * invDet
//    )
//
//    // Create quaternion from source point
//
//    // Create quaternion from source point
//    val quat: Quaternion = kQuaternion(this.x, this.y, this.z, 1.0f)
//
//    // Multiply quat point by unproject matrix
//
//    // Multiply quat point by unproject matrix
//    val qtransformed: Quaternion = kQuaternion( // QuaternionTransform(quat, matViewProjInv)
//        matViewProjInv.m0 * quat.x + matViewProjInv.m4 * quat.y + matViewProjInv.m8 * quat.z + matViewProjInv.m12 * quat.w,
//        matViewProjInv.m1 * quat.x + matViewProjInv.m5 * quat.y + matViewProjInv.m9 * quat.z + matViewProjInv.m13 * quat.w,
//        matViewProjInv.m2 * quat.x + matViewProjInv.m6 * quat.y + matViewProjInv.m10 * quat.z + matViewProjInv.m14 * quat.w,
//        matViewProjInv.m3 * quat.x + matViewProjInv.m7 * quat.y + matViewProjInv.m11 * quat.z + matViewProjInv.m15 * quat.w
//    )
//
//    // Normalized world points in vectors
//
//    // Normalized world points in vectors
//    result.x = qtransformed.x / qtransformed.w
//    result.y = qtransformed.y / qtransformed.w
//    result.z = qtransformed.z / qtransformed.w
//
//    return result
//}

///**
// * Get [Vector3] as float array
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.toFloatV() : float3 {
//    return Vector3ToFloatV(this.readValue()).getPointer(MemScope()).pointed
//}

///**
// * Clamp the components of the [Vector3] between
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.clamp(min: Vector3, max: Vector3) : Vector3 {
//    val result: Vector3 = kVector3()
//
//    result.x = min(max.x, max(min.x, this.x))
//    result.y = min(max.y, max(min.y, this.y))
//    result.z = min(max.z, max(min.z, this.z))
//
//    return result
//}

@Deprecated("Use Kotlin's internal coerceIn function", ReplaceWith("coerceIn()"))
/**
 * Clamp the magnitude of the vector between two values
 */
@OptIn(ExperimentalForeignApi::class)
inline fun Vector3.clampValue(min: Float, max: Float) : Vector3 {
    return Vector3ClampValue(this.readValue(), min, max).getPointer(MemScope()).pointed
}

/**
 * Due to inability to override equals operator, this is the current workaround checking for equality of the values of a [Vector3]
 * @return String values of [Vector3]
 */
inline fun Vector3.equalsTo(v: Vector3): Boolean {

    return (abs(this.x - v.x) <= EPSILON * max(
        1.0f,
        max(abs(this.x), abs(v.x))
    ) && abs(this.y - v.y) <= EPSILON * max(
        1.0f,
        max(abs(this.y), abs(v.y))
    ) && abs(this.z - v.z) <= EPSILON * max(1.0f, max(abs(this.z), abs(v.z))))
}

///**
// * Compute the direction of a refracted ray where v specifies the
// * normalized direction of the incoming ray, n specifies the
// * normalized normal vector of the interface of two optical media,
// * and r specifies the ratio of the refractive index of the medium
// * from where the ray comes to the refractive index of the medium
// * on the other side of the surface
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector3.refract(n: Vector3, r: Float) : Vector3 {
//    var result: Vector3 = kVector3()
//
//    val dot: Float = this.x * n.x + this.y * n.y + this.z * n.z
//    var d = 1.0f - r * r * (1.0f - dot * dot)
//
//    if (d >= 0.0f) {
//        d = sqrt(d)
//        this.x = r * this.x - (r * dot + d) * n.x
//        this.y = r * this.y - (r * dot + d) * n.y
//        this.z = r * this.z - (r * dot + d) * n.z
//        result = this
//    }
//
//    return result
//}

/**
 * Divide vector by [Vector3]
 */
inline operator fun Vector3.div(v3: Vector3) {
    this.x /= v3.x
    this.y /= v3.y
    this.z /= v3.z
}

/**
 * Deconstruct this [Vector3].
 */
inline operator fun Vector3.component1(): Float = x

/**
 * Deconstruct this [Vector3].
 */
inline operator fun Vector3.component2(): Float = y

/**
 * Deconstruct this [Vector3].
 */
inline operator fun Vector3.component3(): Float = z

/**
 * Divide vector by [Vector3]
 */
inline operator fun Vector3.divAssign(v3: Vector3) {
    this.x /= v3.x
    this.y /= v3.y
    this.z /= v3.z
}

/**
 * Divide both [Vector3] values by scalar.
 */
inline operator fun Vector3.divAssign(scalar: Float) {
    this.x /= scalar
    this.y /= scalar
    this.z /= scalar
}

/**
 * Divide both [Vector3] values by scalar.
 */
inline operator fun Vector3.divAssign(scalar: Int) {
    this.x /= scalar
    this.y /= scalar
    this.z /= scalar
}

/**
 * Multiply both [Vector3] values
 */
inline operator fun Vector3.timesAssign(scalar: Int) {
    this.x *= scalar.toFloat()
    this.y *= scalar.toFloat()
    this.z *= scalar.toFloat()
}

/**
 * Multiply both [Vector3] values
 */
inline operator fun Vector3.timesAssign(scalar: Float) {
    this.x *= scalar
    this.y *= scalar
    this.z *= scalar
}
/**
 * Multiply both [Vector3] values
 */
inline operator fun Vector3.timesAssign(v3: Vector3) {
    this.x *= v3.x
    this.y *= v3.y
    this.z *= v3.z
}

/**
 * Add two [Vector3]
 */
inline operator fun Vector3.plusAssign(v3: Vector3) {
    this.add(v3)
}

/**
 * Add two [Vector3]
 */
inline operator fun Vector3.plus(v3: Vector3) {
    this.add(v3)
}

/**
 * Add two [Vector3] to both x, y and z of the vector.
 */
inline operator fun Vector3.plusAssign(add: Float) {
    this.x += add
    this.y += add
    this.z += add
}

/**
 * Add two [Vector3] to both x, y and z of the vector.
 */
inline operator fun Vector3.plusAssign(addend: Int) {
    plusAssign(addend.toFloat())
}

/**
 * Subtract a [Vector3] by another Vector3
 */
inline operator fun Vector3.minusAssign(v3: Vector3) {
    this.x -= v3.x
    this.y -= v3.y
    this.z -= v3.z
}

/**
 * Subtract a [Vector3] by another Vector3
 */
inline operator fun Vector3.minus(v3: Vector3) {
    this.x -= v3.x
    this.y -= v3.y
    this.z -= v3.z
}

/**
 * Value will be subtracted from both x, y and z of the [Vector3].
 */
inline operator fun Vector3.minusAssign(value: Float) {
    this.x -= value
    this.y -= value
    this.z -= value
}

/**
 * Value will be subtracted from both x, y and z of the [Vector3].
 */
inline operator fun Vector3.minusAssign(value: Int) {
    minusAssign(value.toFloat())
}

/**
 * Value will be multiplied by a [Vector3]
 */
inline operator fun Vector3.times(v3: Vector3) {
    this.x *= v3.x
    this.y *= v3.y
    this.z *= v3.z
}

/**
 * Value will be compared with another [Vector3]
 */
inline operator fun Vector3.compareTo(other: Vector3): Int = when {
    this.y != other.y -> (this.y - other.y).toInt()
    this.z != other.z -> (this.z - other.z).toInt()
    else -> (this.x - other.x).toInt()
}

/**
 * Due to inability to override toString function, this is the current workaround printing the values of a [Vector3]
 * @return [Vector3]
 */
inline fun Vector3.asString(): String {
    return "X: ${x.toString()}\nY: ${y.toString()}\nZ: ${z.toString()}"
}

/**
 * Set value of a [Vector3] with another provided value.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Vector3.set(other: Vector3) {
    this.x = other.x
    this.y = other.y
    this.z = other.z
}