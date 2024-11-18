package kaylibkit.kUtils

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kUtils

//=======================================================//
// Raylib Utility Functions - Don't belong anywhere else
//=======================================================//

/**
 * Encode text codepoint into UTF-8 text
 * REQUIRES: memcpy()
 * WARNING: Allocated memory must be manually freed
 * @return [ByteVar]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadUTF8(codepoints: IntVar, length: Int, allocator: AutofreeScope): ByteVar? {
    return LoadUTF8(codepoints.ptr, length)?.getPointer(allocator)?.pointed
}

/**
 * Unload UTF-8 text encoded from codepoints array
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadUTF8(text: String) {
    UnloadUTF8(text.cstr)
}

/**
 * Unload codepoints data from memory
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadCodepoints(codepoints: IntVar) {
    UnloadCodepoints(codepoints.ptr)
}

/**
 * Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
 * @return [Int]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getCodepoint(text: String, codepointSize: IntVar) : Int {
    return GetCodepoint(text, codepointSize.ptr)
}

/**
 * Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
 * @return [Int]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getCodepointNext(text: String, codepointSize: IntVar) : Int {
    return GetCodepointNext(text, codepointSize.ptr)
}

/**
 * Get previous codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
 * @return [Int]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getCodepointPrevious(text: String, codepointSize: IntVar) : Int {
    return GetCodepointPrevious(text, codepointSize.ptr)
}

/**
 * Get previous codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
 */
@OptIn(ExperimentalForeignApi::class)
inline fun codepointToUTF8(codepoint: Int, utf8Size: IntVar, allocator: AutofreeScope) : ByteVar? {
    return CodepointToUTF8(codepoint, utf8Size.ptr)?.getPointer(allocator)?.pointed
}

//=======================================================//
// COLOR/PIXEL FUNCTIONS
//=======================================================//

/**
 * Get [Color] with alpha applied, alpha goes from 0.0f to 1.0f
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun fade(color: Color, alpha: Float, allocator: AutofreeScope) : Color {
    return Fade(color.readValue(), alpha).getPointer(allocator).pointed
}

/**
 * Get hexadecimal value for a [Color]
 * @return [Int]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun colorToInt(color: Color) : Int {
    return ColorToInt(color.readValue())
}

/**
 * Get [Color] normalized as float [0..1]
 * @return [Vector4]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun colorNormalize(color: Color, allocator: AutofreeScope) : Vector4 {
    return ColorNormalize(color.readValue()).getPointer(allocator).pointed
}

/**
 * Get [Color] from normalized values [0..1]
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun colorFromNormalized(normalized: Vector4, allocator: AutofreeScope) : Color {
    return ColorFromNormalized(normalized.readValue()) .getPointer(allocator).pointed
}

/**
 * Get HSV values for a [Color], hue [0..360], saturation/value [0..1]
 * @return [Vector3]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun colorToHSV(color: Color, allocator: AutofreeScope) : Vector3 {
    return ColorToHSV(color.readValue()).getPointer(allocator).pointed
}

/**
 * Get a [Color] from HSV values, hue [0..360], saturation/value [0..1]
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun colorFromHSV(hue: Float, saturation: Float, value: Float, allocator: AutofreeScope) : Color {
    return ColorFromHSV(hue, saturation, value).getPointer(allocator).pointed
}

/**
 * Get [Color] with alpha applied, alpha goes from .0F to 1.0F
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun colorAlpha(color: Color, alpha: Float, allocator: AutofreeScope) : Color {
    return ColorAlpha(color.readValue(), alpha).getPointer(allocator).pointed
}

/**
 * Get src alpha-blended into dst [Color] with tint
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun colorAlphaBlend(dst: Color, src: Color, tint: Color, allocator: AutofreeScope) : Color {
    return ColorAlphaBlend(dst.readValue(), src.readValue(), tint.readValue()).getPointer(allocator).pointed
}

/**
 * Get [Color] structure from hexadecimal value
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getColor(hexValue: UInt, allocator: AutofreeScope) : Color {
    return GetColor(hexValue).getPointer(allocator).pointed
}

/**
 * Get [Color] from a source pixel pointer of certain format
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getPixelColor(srcPtr: COpaquePointer, format: kaylibkit.kEnums.PixelFormat, allocator: AutofreeScope) : Color {
    return GetPixelColor(srcPtr, format.value).getPointer(allocator).pointed
}

/**
 * Set [Color] formatted into destination pixel pointer
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setPixelColor(dstPtr: COpaquePointer, color: Color, format: kaylibkit.kEnums.PixelFormat) {
    return SetPixelColor(dstPtr, color.readValue(), format.value)
}

/**
 * Get pixel data size in bytes for certain format
 * @return [Int]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getPixelDataSize(width: Int, height: Int, format: kaylibkit.kEnums.PixelFormat) : Int {
    return GetPixelDataSize(width, height, format.value)
}

/**
 * Checks if shader is ready
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isShaderReady(shader: Shader) : Boolean {
    return IsShaderReady(shader.readValue())
}

/**
 * Check if a material is ready
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isMaterialReady(material: Material) : Boolean {
    return IsMaterialReady(material.readValue())
}