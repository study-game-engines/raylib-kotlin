package kaylibkit.kEasing

import platform.posix.pow
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

// -- Module: kEasing

//=======================================================//
// EASINGS FUNCTIONS
//=======================================================//

/**
 * * A port of Robert Penner's easing equations to Kotlin from C (Raylib) (http://robertpenner.com/easing/)
*
*   Robert Penner License
*   ---------------------------------------------------------------------------------
*   Open source under the BSD License.
*
*   Copyright (c) 2001 Robert Penner. All rights reserved.
*
*   Redistribution and use in source and binary forms, with or without modification,
*   are permitted provided that the following conditions are met:
*
*       - Redistributions of source code must retain the above copyright notice,
*         this list of conditions and the following disclaimer.
*       - Redistributions in binary form must reproduce the above copyright notice,
*         this list of conditions and the following disclaimer in the documentation
*         and/or other materials provided with the distribution.
*       - Neither the name of the author nor the names of contributors may be used
*         to endorse or promote products derived from this software without specific
*         prior written permission.
*
*   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
*   ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
*   WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
*   IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
*   INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
*   BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
*   DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
*   LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
*   OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
*   OF THE POSSIBILITY OF SUCH DAMAGE.
*   ---------------------------------------------------------------------------------
*
*   Copyright (c) 2015-2023 Ramon Santamaria (@raysan5)
*
*   This software is provided "as-is", without any express or implied warranty. In no event
*   will the authors be held liable for any damages arising from the use of this software.
*
*   Permission is granted to anyone to use this software for any purpose, including commercial
*   applications, and to alter it and redistribute it freely, subject to the following restrictions:
*
*     1. The origin of this software must not be misrepresented; you must not claim that you
*     wrote the original software. If you use this software in a product, an acknowledgment
*     in the product documentation would be appreciated but is not required.
*
*     2. Altered source versions must be plainly marked as such, and must not be misrepresented
*     as being the original software.
*
*     3. This notice may not be removed or altered from any source distribution.
*
**********************************************************************************************/


/*
    How to use:
*   The four inputs t,b,c,d are defined as follows:
*   t = current time (in any unit measure, but same unit as duration)
*   b = starting value to interpolate
*   c = the total change in value of b that needs to occur
*   d = total time it should take to complete (duration)
 */

// Linear Easing Functions
inline fun linearNone(t: Float, b: Float, c: Float, d: Float): Float {
    return (c*t/d + b)
}

inline fun linearIn(t: Float, b: Float, c: Float, d: Float): Float  {
    return (c*t/d + b)
}

inline fun linearOut(t: Float, b: Float, c: Float, d: Float): Float  {
    return (c*t/d + b)
}

inline fun linearInOut(t: Float, b: Float, c: Float, d: Float): Float  {
    return (c*t/d + b)
}

// Sine Easing Functions
inline fun sineIn(t: Float, b: Float, c: Float, d: Float): Float {
    return (-c*cos(t/d*(PI/2.0f)) + c + b).toFloat()
}

inline fun sineOut(t: Float, b: Float, c: Float, d: Float): Float {
    return c * sin(t / d * (PI / 2.0f)).toFloat() + b
}

inline fun sineInOut(t: Float, b: Float, c: Float, d: Float): Float {
    return -c / 2.0f * (cos(PI * t / d) - 1.0f).toFloat() + b
}

// Circular Easing Functions
inline fun circIn(t: Float, b: Float, c: Float, d: Float): Float {
    var t2 = t
    t2 /= d
    return -c * (sqrt(1.0f - t2 * t2) - 1.0f) + b
}

inline fun circOut(t: Float, b: Float, c: Float, d: Float): Float {
    var t2 = t
    t2 = t2 / d - 1.0f
    return c * sqrt(1.0f - t2 * t2) + b
}

inline fun circInOut(t: Float, b: Float, c: Float, d: Float): Float
{
    var t2 = t
    if (d / 2.0f.let { t2 /= it; t2 } < 1.0f) return -c / 2.0f * (sqrt(1.0f - t2 * t2) - 1.0f) + b
    t2 -= 2.0f
    return c / 2.0f * (sqrt(1.0f - t2 * t2) + 1.0f) + b
}

// Cubic Easing Functions
inline fun cubicIn(t: Float, b: Float, c: Float, d: Float): Float {
    var t2 = t
    t2 /= d
    return c * t2 * t2 * t2 + b
}

inline fun cubicOut(t: Float, b: Float, c: Float, d: Float): Float {
    var t2 = t
    t2 = t2 / d - 1.0f
    return c * (t2 * t2 * t2 + 1.0f) + b
}

inline fun cubicInOut(t: Float, b: Float, c: Float, d: Float): Float
{
    var t2 = t
    if (d / 2.0f.let { t2 /= it; t2 } < 1.0f) return c / 2.0f * t2 * t2 * t2 + b
    t2 -= 2.0f
    return c / 2.0f * (t2 * t2 * t2 + 2.0f) + b
}

// Quadratic Easing Functions
inline fun quadIn(t: Float, b: Float, c: Float, d: Float): Float {
    var t2 = t
    t2 /= d
    return c * t2 * t2 + b
}

inline fun quadOut(t: Float, b: Float, c: Float, d: Float): Float {
    var t2 = t
    t2 /= d
    return -c * t2 * (t2 - 2.0f) + b
}

inline fun quadInOut(t: Float, b: Float, c: Float, d: Float): Float
{
    var t2 = t
    return if (d / 2.let { t2 /= it; t2 } < 1) c / 2 * (t2 * t2) + b else -c / 2.0f * ((t2 - 1.0f) * (t2 - 3.0f) - 1.0f) + b
}

// Exponential Easing Functions
inline fun expoIn(t: Float, b: Float, c: Float, d: Float): Float {
    return if (t == 0.0f) b else c * pow(2.0, 10.0 * (t / d - 1.0f)).toFloat() + b
}

inline fun expoOut(t: Float, b: Float, c: Float, d: Float): Float {
    return if (t == d) b + c else c * (-pow(2.0, -10.0 * t / d) + 1.0f).toFloat() + b
}

inline fun expoInOut(t: Float, b: Float, c: Float, d: Float): Float
{
    var t2 = t
    if (t2 == 0.0f) return b
    if (t2 == d) return b + c
    return if (d / 2.0f.let { t2 /= it; t2 } < 1.0f) (c / 2.0f * pow(2.0, 10.0 * (t2 - 1.0f)) + b).toFloat() else c / 2.0f * (-pow(
        2.0,
        -10.0 * (t2 - 1.0f)
    ) + 2.0f).toFloat() + b
}

// Back Easing Functions
inline fun backIn(t: Float, b: Float, c: Float, d: Float): Float
{
    var t2 = t
    val s = 1.70158f
    t2 /= d
    val postFix = t2
    return c * postFix * t2 * ((s + 1.0f) * t2 - s) + b
}

fun backOut(t: Float, b: Float, c: Float, d: Float): Float
{
    var t2 = t
    val s = 1.70158f
    t2 = t2 / d - 1.0f
    return c * (t2 * t2 * ((s + 1.0f) * t2 + s) + 1.0f) + b
}

fun backInOut(t: Float, b: Float, c: Float, d: Float): Float
{
    var t2 = t
    var s = 1.70158f
    if (d / 2.0f.let { t2 /= it; t2 } < 1.0f) {
        s *= 1.525f
        return c / 2.0f * (t2 * t2 * ((s + 1.0f) * t2 - s)) + b
    }
    t2 -= 2.0f
    val postFix = t2
    s *= 1.525f
    return c / 2.0f * (postFix * t2 * ((s + 1.0f) * t2 + s) + 2.0f) + b
}

// Bounce Easing Function
inline fun bounceOut(t: Float, b: Float, c: Float, d: Float): Float
{
    var t2 = t
    return if (d.let { t2 /= it; t2 } < 1.0f / 2.75f) {
        c * (7.5625f * t2 * t2) + b
    } else if (t2 < 2.0f / 2.75f) {
        t2 -= 1.5f / 2.75f
        val postFix = t2
        c * (7.5625f * postFix * t2 + 0.75f) + b
    } else if (t2 < 2.5 / 2.75) {
        t2 -= 2.25f / 2.75f
        val postFix = t2
        c * (7.5625f * postFix * t2 + 0.9375f) + b
    } else {
        t2 -= 2.625f / 2.75f
        val postFix = t2
        c * (7.5625f * postFix * t2 + 0.984375f) + b
    }
}

inline fun bounceIn(t: Float, b: Float, c: Float, d: Float): Float {
    return c - bounceOut(d - t, 0.0f, c, d) + b
}

inline fun bounceInOut(t: Float, b: Float, c: Float, d: Float): Float // Ease: Bounce In Out
{
    return if (t < d / 2.0f) bounceIn(t * 2.0f, 0.0f, c, d) * 0.5f + b else bounceOut(
        t * 2.0f - d,
        0.0f,
        c,
        d
    ) * 0.5f + c * 0.5f + b
}

// Elastic Easing Function
inline fun elasticIn(t: Float, b: Float, c: Float, d: Float): Float // Ease: Elastic In
{
    var t2 = t
    if (t2 == 0.0f) return b
    if (d.let { t2 /= it; t2 } == 1.0f) return b + c
    val p = d * 0.3f
    val s = p / 4.0f
    val postFix: Float = c * pow(2.0, 10.0 * 1.0f.let { t2 -= it; t2 }).toFloat()
    return -(postFix * sin((t2 * d - s) * (2.0f * PI) / p)).toFloat() + b
}

inline fun elasticOut(t: Float, b: Float, c: Float, d: Float): Float // Ease: Elastic Out
{
    var t2 = t
    if (t2 == 0.0f) return b
    if (d.let { t2 /= it; t2 } == 1.0f) return b + c
    val p = d * 0.3f
    val s = p / 4.0f
    return c * pow(2.0, -10.0 * t2).toFloat() * sin((t2 * d - s) * (2.0f * PI).toFloat() / p) + c + b
}

fun elasticInOut(t: Float, b: Float, c: Float, d: Float): Float {
    var t2 = t
    if (t2 == 0.0f) return b
    if (d / 2.0f.let { t2 /= it; t2 } == 2.0f) return b + c
    val p = d * (0.3f * 1.5f)
    val s = p / 4.0f
    if (t2 < 1.0f) {
        val postFix: Float = c * pow(2.0, 10.0 * 1.0f.let { t2 -= it; t2 }).toFloat()
        return -0.5f * (postFix * sin((t2 * d - s) * (2.0f * PI) / p)).toFloat() + b
    }
    val postFix: Float = c * pow(2.0, -10.0 * 1.0f.let { t2 -= it; t2 }).toFloat()
    return postFix * sin((t2 * d - s) * (2.0f * PI) / p).toFloat() * 0.5f + c + b
}