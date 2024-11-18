package kaylibkit.kMath

import kaylibc.*
import kotlinx.cinterop.*
import kotlin.math.*

// -- Module: kMath

//=======================================================//
// Vector4 DATA TYPE
//=======================================================//

/**
 * Constructor function for [Vector4]
 * @return [Vector4]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kVector4(x: Float = 0F, y: Float = 0F, z: Float = 0F, w: Float = 0F, allocator: AutofreeScope): Vector4 {
    return allocator.alloc<Vector4> {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
    }
}

/**
 * Add [Vector4] and float value
 */
inline fun Vector4.addValue(add: Float)  {
    this.x += add
    this.y += add
    this.z += add
    this.w += add
}

/**
 * Subtract two [Vector4]
 */
inline fun Vector4.subtract(v4: Vector4) {
    this.x -= v4.x
    this.y -= v4.y
    this.z -= v4.z
}

/**
 * Subtract [Vector4] by float value
 */
inline fun Vector4.subtractValue(sub: Float)  {
    this.x -= sub
    this.y -= sub
    this.z -= sub
    this.w -= sub
}


/**
 * Negate [Vector4]
 */
inline fun Vector4.negate() {
    this.x = this.x.unaryMinus()
    this.y = this.y.unaryMinus()
    this.z = this.z.unaryMinus()
    this.w = this.w.unaryMinus()
}

/**
 * Compute the length of a [Vector4]
 * @return [Vector4]
 */
inline fun Vector4.length(): Float { return sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w) }

/**
 * Normalize provided [Vector4]
 * @return [Vector4]
 */
inline fun Vector4.normalize() : Vector4 {
    val result = this
    var length = sqrt(this.x*this.x + this.y*this.y + this.z*this.z + this.w*this.w)

    if (length == 0F) { length = 1F }

    val iLength = 1F/length
    result.x = this.x*iLength
    result.y = this.y*iLength
    result.z = this.z*iLength
    result.w = this.w*iLength
    return result
}

/**
 * Invert provided [Quaternion]/[Vector4]
 * @return [Vector4]
 */
inline fun Vector4.invert(): Vector4 {
    val result: Vector4 = this

    val lengthSq: Float = this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w

    if (lengthSq != 0.0F) {
        val invLength = 1.0F / lengthSq
        result.x *= -invLength
        result.y *= -invLength
        result.z *= -invLength
        result.w *= invLength
    }

    return result
}

/**
 * Deconstruct this [Vector4].
 */
inline operator fun Vector4.component1(): Float = x

/**
 * Deconstruct this [Vector4].
 */
inline operator fun Vector4.component2(): Float = y

/**
 * Deconstruct this [Vector4].
 */
inline operator fun Vector4.component3(): Float = z

/**
 * Deconstruct this [Vector4].
 */
inline operator fun Vector4.component4(): Float = w

/**
 * Divide vector by [Vector4]
 */
inline operator fun Vector4.divAssign(v4: Vector4) {
    this.x /= v4.x
    this.y /= v4.y
    this.z /= v4.z
    this.w /= v4.w
}

/**
 * Divide both [Vector4] values by scalar.
 */
inline operator fun Vector4.divAssign(scalar: Float) {
    this.x /= scalar
    this.y /= scalar
    this.z /= scalar
    this.w /= scalar
}

/**
 * Divide both [Vector4] values by scalar.
 */
inline operator fun Vector4.divAssign(scalar: Int) {
    this.x /= scalar
    this.y /= scalar
    this.z /= scalar
    this.w /= scalar
}

/**
 * Multiply both [Vector4] values
 */
inline operator fun Vector4.timesAssign(scalar: Int) {
    this.x *= scalar.toFloat()
    this.y *= scalar.toFloat()
    this.z *= scalar.toFloat()
    this.w *= scalar.toFloat()
}

/**
 * Multiply both [Vector4] values
 */
inline operator fun Vector4.timesAssign(scalar: Float) {
    this.x *= scalar
    this.y *= scalar
    this.z *= scalar
    this.w *= scalar
}
/**
 * Multiply both [Vector4] values
 */
inline operator fun Vector4.timesAssign(v4: Vector4) {
    this.x *= v4.x
    this.y *= v4.y
    this.z *= v4.z
    this.w *= v4.w
}

/**
 * Add two [Vector4]
 */
inline fun Vector4.add(v4: Vector4) {
    this.x += v4.x
    this.y += v4.y
    this.z += v4.z
    this.w += v4.w
}

/**
 * Add two [Vector4]
 */
inline fun Vector4.plus(v4: Vector4) {
    this.x += v4.x
    this.y += v4.y
    this.z += v4.z
    this.w += v4.w
}

///**
// * Calculate linear interpolation between two [Vector4]
// * @return [Vector4]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector4.lerp(v4: Vector4, amount: Float): Vector4 {
//    val result: Vector4 = kVector4()
//
//    result.x = this.x + amount * (v4.x - this.x)
//    result.y = this.y + amount * (v4.y - this.y)
//    result.z = this.z + amount * (v4.z - this.z)
//    result.w = this.w + amount * (v4.w - this.w)
//
//    return result
//}

/**
 * Due to inability to override equals operator, this is the current workaround checking for equality of the values of a [Vector4]
 * @return [Boolean]
 */
inline fun Vector4.equalsTo(v: Vector4): Boolean {

    return (abs(this.x - v.x) <= EPSILON * max(
        1.0f,
        max(abs(this.x), abs(v.x))
    ) && abs(this.y - v.y) <= EPSILON * max(
        1.0f,
        max(abs(this.y), abs(v.y))
    ) && abs(this.z - v.z) <= EPSILON * max(
        1.0f,
        max(abs(this.z), abs(v.z))
    ) && abs(this.w - v.w) <= EPSILON * max(
        1.0f,
        max(abs(this.w), abs(v.w))
    ) || abs(this.x + v.x) <= EPSILON * max(
        1.0f,
        max(abs(this.x), abs(v.x))
    ) && abs(this.y + v.y) <= EPSILON * max(
        1.0f,
        max(abs(this.y), abs(v.y))
    ) && abs(this.z + v.z) <= EPSILON * max(
        1.0f,
        max(abs(this.z), abs(v.z))
    ) && abs(this.w + v.w) <= EPSILON * max(1.0f, max(abs(this.w), abs(v.w))))
}

/**
 * Scale [Vector4] by float value
 */
inline fun Vector4.scale(mul: Float) {
    this.x *= mul
    this.y *= mul
    this.z *= mul
    this.w *= mul
}

/**
 * Add two [Vector4]
 */
inline operator fun Vector4.plusAssign(v4: Vector4) {
    this.add(v4)
}

/**
 * Divide [Vector4] by [Vector4]
 */
inline operator fun Vector4.div(v4: Vector4) {
    this.x /= v4.x
    this.y /= v4.y
    this.z /= v4.z
    this.w /= v4.w
}

/**
 * Add two [Vector4] to both x, y, z and w of the [Vector4].
 */
inline operator fun Vector4.plusAssign(add: Float) {
    this.x += add
    this.y += add
    this.z += add
    this.w += add
}

/**
 * Add two [Vector4] to both x, y, z and w of the [Vector4].
 */
inline operator fun Vector4.plusAssign(addend: Int) {
    plusAssign(addend.toFloat())
}

/**
 * Subtract a [Vector4] by another [Vector4]
 */
inline operator fun Vector4.minusAssign(v4: Vector4) {
    this.x -= v4.x
    this.y -= v4.y
    this.z -= v4.z
    this.w -= v4.w
}

/**
 * Subtract a [Vector4] by another [Vector4]
 */
inline operator fun Vector4.minus(v4: Vector4) {
    this.x -= v4.x
    this.y -= v4.y
    this.z -= v4.z
    this.w -= v4.w
}

/**
 * Value will be subtracted from both x, y, z and w of the [Vector4].
 */
inline operator fun Vector4.minusAssign(value: Float) {
    this.x -= value
    this.y -= value
    this.z -= value
    this.w -= value
}

/**
 * Value will be subtracted from both x, y, z and w of the [Vector4].
 */
inline operator fun Vector4.minusAssign(value: Int) {
    minusAssign(value.toFloat())
}

///**
// * Calculate two [Vector4] multiplication
// * @return [Vector4]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline operator fun Vector4.times(v4: Vector4): Vector4 {
//    val result: Vector4 = kVector4()
//
//    val qax: Float = this.x
//    val qay: Float = this.y
//    val qaz: Float = this.z
//    val qaw: Float = this.w
//    val qbx: Float = v4.x
//    val qby: Float = v4.y
//    val qbz: Float = v4.z
//    val qbw: Float = v4.w
//
//    result.x = qax * qbw + qaw * qbx + qay * qbz - qaz * qby
//    result.y = qay * qbw + qaw * qby + qaz * qbx - qax * qbz
//    result.z = qaz * qbw + qaw * qbz + qax * qby - qay * qbx
//    result.w = qaw * qbw - qax * qbx - qay * qby - qaz * qbz
//
//    return result
//}

/**
 * Value will be compared with another [Vector4]
 */
inline operator fun Vector4.compareTo(other: Vector4): Int = when {
    this.y != other.y -> (this.y - other.y).toInt()
    this.z != other.z -> (this.z - other.z).toInt()
    this.w != other.w -> (this.w - other.w).toInt()
    else -> (this.x - other.x).toInt()
}

/**
 * Due to inability to override toString function, this is the current workaround printing the values of a [Vector4]
 * @return [String] values of [Vector4]
 */
inline fun Vector4.asString(): String {
    return "X: ${x.toString()}\nY: ${y.toString()}\nZ: ${z.toString()}\nW: ${w.toString()}"
}

/**
 * Set value of a [Quaternion]/[Vector4] with another provided value.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Vector4.set(other: Quaternion) {
    this.x = other.x
    this.y = other.y
    this.z = other.z
    this.w = other.w
}