package kaylibkit.kMath

import kaylibc.*
import kotlinx.cinterop.*
import kotlin.math.*

// -- Module: kMath

//=======================================================//
// VECTOR2 DATA TYPE
//=======================================================//

/**
 * Constructor function for [Vector2]
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kVector2(x: Float = 0F, y: Float = 0F, allocator: AutofreeScope?): Vector2 {
    if (allocator != null) {
        return allocator.alloc<Vector2> {
            this.x = x
            this.y = y
        }
    } else {
        throw IllegalArgumentException("ERROR: No allocator passed to the parameter! Unable to allocate!")
    }
}

/**
 * [Vector2] with components value 0
 * @return zero values Y and X of passed [Vector2]
 */
inline fun Vector2.setZero() : Vector2 = this.apply { this.x = 0F; this.y = 0F }

/**
 * [Vector2] with components value 1
 * @return values of 1 in Y and X of passed [Vector2]
 */
inline fun Vector2.setOne() : Vector2 = this.apply { this.x = 1F; this.y = 1F }

/**
 * Calculate [Vector2] length
 * @return [Float]
 */
inline fun Vector2.length() : Float = sqrt((this.x*this.x) + (this.y*this.y))

/**
 * Calculate [Vector2] square length
 * @return [Float]
 */
inline fun Vector2.lengthSqr() = (this.x*this.x) + (this.y*this.y)

/**
 * Calculate two [Vector2] dot product
 * @return [Float]
 */
inline fun Vector2.dot(v2: Vector2) : Float = (this.x*v2.x + this.y*v2.y)

/**
 * Calculate distance between two [Vector2]
 * @return [Float]
 */
inline fun Vector2.distance(v2: Vector2) : Float = sqrt((this.x - v2.x)*(this.x - v2.x) + (this.y - v2.y)*(this.y - v2.y))

/**
 * Calculate square distance between two [Vector2]
 * @return [Float]
 */
inline fun Vector2.distanceSqr(v2: Vector2) : Float = ((this.x - v2.x)*(this.x - v2.x) + (this.y - v2.y)*(this.y - v2.y))

/**
 * Calculate angle from two [Vector2]
 * @return [Float]
 */
inline fun Vector2.angle(v2: Vector2) : Float = atan2(v2.y - this.y, v2.x - this.x)

/**
 * Scale [Vector2] (multiply by value)
 */
inline fun Vector2.times(scale: Float) {
    this.x *= scale
    this.y *= scale
}

/**
 * Add two [Vector2]
 */
inline fun Vector2.add(v2: Vector2) {
    this.x += v2.x
    this.y += v2.y
}

/**
 * Add two [Vector2]
 */
inline operator fun Vector2.plus(v2: Vector2) {
    this.x += v2.x
    this.y += v2.y
}

/**
 * Multiply [Vector2] by [Vector2]
 */
inline fun Vector2.multiply(v2: Vector2) {
    this.x *= v2.x
    this.y *= v2.y
}

/**
 * Add [Vector2] and float value
 */
inline fun Vector2.addValue(add: Float)  {
    this.x += add
    this.y += add
}

/**
 * Subtract two [Vector2]
 */
inline fun Vector2.subtract(v2: Vector2) {
    this.x -= v2.x
    this.y -= v2.y
}

/**
 * Subtract [Vector2] by float value
 */
inline fun Vector2.subtractValue(sub: Float)  {
    this.x -= sub
    this.y -= sub
}

/**
 * Negate [Vector2]
 */
inline fun Vector2.negate() {
    this.x = this.x.unaryMinus()
    this.y = this.y.unaryMinus()
}

/**
 * Normalize provided [Vector2]
 * @return [Vector2]
 */
inline fun Vector2.normalize() : Vector2 {
    val result = this
    val length = sqrt((this.x*this.x) + (this.y*this.y))

    if (length > 0) {
        val iLength = 1F/length
        result.x = this.x*iLength
        result.y = this.y*iLength
    }
    return result
}

/**
 * Transforms a [Vector2] by a given [Matrix]
 * @return [Vector2]
 */
inline fun Vector2.transform(mat: Matrix) : Vector2 {
    val result = this

    val x = this.x
    val y = this.y
    val z = 0

    result.x = mat.m0*x + mat.m4*y + mat.m8*z + mat.m12
    result.y = mat.m1*x + mat.m5*y + mat.m9*z + mat.m13

    return result
}

/**
 * Calculate linear interpolation between two [Vector2]
 * @return [Vector2]
 */
inline fun Vector2.lerp(v2: Vector2, amount: Float) : Vector2 {
    val result = this

    result.x = this.x + amount*(v2.x - this.x)
    result.y = this.y + amount*(v2.y - this.y)

    return result
}

///**
// * Calculate reflected [Vector2] to normal
// *  * @return [Vector2]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector2.reflect(normal: Vector2) : Vector2 {
//    val result: Vector2 = kVector2()
//
//    val dotProduct: Float = this.x * normal.x + this.y * normal.y // Dot product
//
//
//    result.x = this.x - 2.0f * normal.x * dotProduct
//    result.y = this.y - 2.0f * normal.y * dotProduct
//
//    return result
//}

///**
// * Rotate [Vector2] by [angle]
// * @return [Vector2]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector2.rotate(angle: Float) : Vector2 {
//    val result: Vector2 = kVector2()
//
//    val cosres: Float = cos(angle)
//    val sinres: Float = sin(angle)
//
//    result.x = this.x * cosres - this.y * sinres
//    result.y = this.x * sinres + this.y * cosres
//
//    return result
//}

///**
// * Move [Vector2] towards target
// * @return [Vector2]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector2.moveTowards(target: Vector2, maxDistance: Float) : Vector2 {
//    val result: Vector2 = kVector2()
//
//    val dx: Float = target.x - this.x
//    val dy: Float = target.y - this.y
//    val value = dx * dx + dy * dy
//
//    if (value == 0f || maxDistance >= 0 && value <= maxDistance * maxDistance) return target
//
//    val dist: Float = sqrt(value)
//
//    result.x = this.x + dx / dist * maxDistance
//    result.y = this.y + dy / dist * maxDistance
//
//    return result
//}

///**
// * Invert the given [Vector2]
// * @return [Vector2]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector2.invert(): Vector2 { return memScoped { kVector2(1.0f / this@invert.x, 1.0f / this@invert.y, this) } }

///**
// * Clamp the components of the [Vector2] between
// * @return [Vector2]
// */
//@OptIn(ExperimentalForeignApi::class)
//inline fun Vector2.clamp(min: Vector2, max: Vector2) : Vector2 {
//    val result: Vector2 = kVector2()
//
//    result.x = min(max.x, max(min.x, this.x))
//    result.y = min(max.y, max(min.y, this.y))
//
//    return result
//}

@Deprecated("Use Kotlin's internal coerceIn function", ReplaceWith("coerceIn()"))
/**
 * Clamp the magnitude of the vector between two values
 * @return [Unit]
 */
inline fun Vector2.clampValue() {}

/**
 * Calculate angle defined by a two vectors line
 * NOTE: Parameters need to be normalized
 * Current implementation should be aligned with glm::angle
 * @return [Vector2]
 */
inline fun Vector2.lineAngle(start: Vector2, end: Vector2) : Float {
    val result: Float

    val dot: Float = start.x * end.x + start.y * end.y // Dot product
    var dotClamp = if (dot < -1.0f) -1.0f else dot // Clamp
    if (dotClamp > 1.0f) dotClamp = 1.0f
    result = acos(dotClamp)

    return result
}

/**
 * Deconstruct this [Vector2].
 */
inline operator fun Vector2.component1(): Float = x

/**
 * Deconstruct this [Vector2].
 */
inline operator fun Vector2.component2(): Float = y

/**
 * Divide vector by [Vector2]
 */
inline operator fun Vector2.divAssign(v2: Vector2) {
    this.x /= v2.x
    this.y /= v2.y
}

/**
 * Divide vector by [Vector2]
 */
inline operator fun Vector2.div(v2: Vector2) {
    this.x /= v2.x
    this.y /= v2.y
}


/**
 * Divide both [Vector2] values by scalar.
 */
inline operator fun Vector2.divAssign(scalar: Float) {
    this.x /= scalar
    this.y /= scalar
}

/**
 * Divide both [Vector2] values by scalar.
 */
inline operator fun Vector2.divAssign(scalar: Int) {
    this.x /= scalar
    this.y /= scalar
}

/**
 * Multiply both [Vector2] values
 */
inline operator fun Vector2.timesAssign(scalar: Int) {
    this.x *= scalar.toFloat()
    this.y *= scalar.toFloat()
}

/**
 * Multiply both [Vector2] values
 */
inline operator fun Vector2.timesAssign(scalar: Float) {
    this.x *= scalar
    this.y *= scalar
}
/**
 * Multiply both [Vector2] values
 */
inline operator fun Vector2.timesAssign(v2: Vector2) {
    this.x *= v2.x
    this.y *= v2.y
}

/**
 * Add two [Vector2]
 */
inline operator fun Vector2.plusAssign(v2: Vector2) {
    this.add(v2)
}

/**
 * Add two [Vector2] to both x and y of the [Vector2].
 */
inline operator fun Vector2.plusAssign(add: Float) {
    this.x += add
    this.y += add
}

/**
 * Add two [Vector2] to both x and y of the [Vector2].
 */
inline operator fun Vector2.plusAssign(addend: Int) {
    plusAssign(addend.toFloat())
}

/**
 * Subtract a [Vector2] by another [Vector2]
 */
inline operator fun Vector2.minusAssign(v2: Vector2) {
    this.x -= v2.x
    this.y -= v2.y
}

/**
 * Value will be subtracted from both x and y of the [Vector2].
 */
inline operator fun Vector2.minusAssign(value: Float) {
    this.x -= value
    this.y -= value
}

/**
 * Value will be subtracted from both x and y of the [Vector2].
 */
inline operator fun Vector2.minusAssign(value: Int) {
    minusAssign(value.toFloat())
}

/**
 * Subtract a [Vector2] by another [Vector2]
 */
inline operator fun Vector2.minus(v2: Vector2) {
    this.x -= v2.x
    this.y -= v2.y
}

/**
 * Value will be multiplied by a [Vector2]
 */
inline operator fun Vector2.times(v2: Vector2) {
    this.x *= v2.x
    this.y *= v2.y
}

/**
 * Get a reminder of [Vector2]
 */
inline operator fun Vector2.rem(v2: Vector2) {
    this.x %= v2.x
    this.y %= v2.y
}

/**
 * Value will be compared with another [Vector2]
 */
inline operator fun Vector2.compareTo(other: Vector2): Int = when {
    this.y != other.y -> (this.y - other.y).toInt()
    else -> (this.x - other.x).toInt()
}

/**
 * Due to inability to override toString function, this is the current workaround printing the values of a [Vector2]
 * @return [String] values of [Vector2]
 */
inline fun Vector2.asString(): String {
    return "X: ${x.toString()}\nY: ${y.toString()}"
}

/**
 * Due to inability to override equals operator, this is the current workaround checking for equality of the values of a [Vector2]
 * @return [Boolean]
 */
inline fun Vector2.equalsTo(v: Vector2): Boolean {
    return (abs(this.x - v.x) <= EPSILON * max(
        1.0f,
        max(abs(this.x), abs(v.x))
    ) && abs(this.y - v.y) <= EPSILON * max(1.0f, max(abs(this.y), abs(v.y))))
}

/**
 * Set value of a [Vector2] with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Vector2.set(other: Vector2) {
    this.x = other.x
    this.y = other.y
}