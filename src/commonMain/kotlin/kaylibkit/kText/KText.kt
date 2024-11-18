package kaylibkit.kText

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kText

//=======================================================//
// FONT LOADING FUNCTIONS
//=======================================================//

/**
 * Get the default [Font]
 * @return [Font]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getFontDefault(allocator: AutofreeScope) : Font {
    return GetFontDefault().getPointer(allocator).pointed
}

/**
 * Load [Font] from file into GPU memory (VRAM)
 * @return [Font]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadFont(fileName: String, allocator: AutofreeScope) : Font {
    return LoadFont(fileName).getPointer(allocator).pointed
}

/**
 * Load font from file with extended parameters
 * @return [Font]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadFont(fileName: String, fontSize: Int, fontChars: IntVar, glyphCount: Int, allocator: AutofreeScope) : Font {
    return LoadFontEx(fileName, fontSize, fontChars.ptr, glyphCount).getPointer(allocator).pointed
}

/**
 * Load [Font] from Image (XNA style)
 * @return [Font]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadFontFromImage(image: Image, key: Color, firstChar: Int, allocator: AutofreeScope) : Font {
    return LoadFontFromImage(image.readValue(), key.readValue(), firstChar).getPointer(allocator).pointed
}

/**
 * Load [Font] from memory buffer, fileType refers to extension: i.e. ".ttf"
 * @return [Font]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadFontFromMemory(fileType: String, fileData: UByteVar, dataSize: Int, fontSize: Int, fontChars: IntVar, glyphCount: Int, allocator: AutofreeScope) : Font {
    return LoadFontFromMemory(fileType, fileData.ptr, dataSize, fontSize, fontChars.ptr, glyphCount).getPointer(allocator).pointed
}

/**
 * Load font data for further use
 * @return [GlyphInfo]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadFontData(fileData: UByteVar, dataSize: Int, fontSize: Int, fontChars: IntVar, glyphCount: Int, type: kaylibkit.kEnums.FontType, allocator: AutofreeScope) : GlyphInfo? {
    return LoadFontData(fileData.ptr, dataSize, fontSize, fontChars.ptr, glyphCount, type.value)?.getPointer(allocator)?.pointed
}

/**
 * Generate [Image] font atlas using chars info
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genImageFontAtlas(chars: GlyphInfo, recs: CPointerVar<Rectangle>, glyphCount: Int, fontSize: Int, padding: Int, packMethod: Int, allocator: AutofreeScope) : Image {
    return GenImageFontAtlas(chars.ptr, recs.ptr, glyphCount, fontSize, padding, packMethod).getPointer(allocator).pointed
}

/**
 * Unload font chars info data (RAM)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadFontData(chars: GlyphInfo, glyphCount: Int) {
    UnloadFontData(chars.ptr, glyphCount)
}

/**
 * Unload Font from GPU memory (VRAM)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadFont(font: Font) {
    UnloadFont(font.readValue())
}

/**
 * Export font as code file, returns true on success
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun exportFontAsCode(font: Font, fileName: String) : Boolean {
    return ExportFontAsCode(font.readValue(), fileName)
}

//=======================================================//
// TEXT DRAWING FUNCTIONS
//=======================================================//

/**
 * Draw current FPS
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawFPS(posX: Int, posY: Int) {
    DrawFPS(posX, posY)
}

/**
 * Draw current FPS
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawText(text: String, posX: Int, posY: Int, fontSize: Int, color: Color) {
    DrawText(text, posX, posY, fontSize, color.readValue())
}

/**
 * Draw text using font and additional parameters
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawText(font: Font, text: String, position: Vector2, fontSize: Float, spacing: Float, tint: Color) {
    DrawTextEx(font.readValue(), text, position.readValue(), fontSize, spacing, tint.readValue())
}

/**
 * Draw text using Font and pro parameters (rotation)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawText(font: Font, text: String, position: Vector2, origin: Vector2, rotation: Float, fontSize: Float, spacing: Float, tint: Color) {
    DrawTextPro(font.readValue(), text, position.readValue(), origin.readValue(), rotation, fontSize, spacing, tint.readValue())
}

/**
 * Draw one character (codepoint)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawText(font: Font, codepoint: Int, position: Vector2, fontSize: Float, tint: Color) {
    DrawTextCodepoint(font.readValue(), codepoint, position.readValue(), fontSize, tint.readValue())
}

/**
 * Draw one character (codepoint)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawText(font: Font, codepoint: IntVar, count: Int, position: Vector2, fontSize: Float, spacing: Float, tint: Color) {
    DrawTextCodepoints(font.readValue(), codepoint.ptr, count, position.readValue(), fontSize, spacing, tint.readValue())
}

//=======================================================//
// TEXT FONT INFO FUNCTIONS
//=======================================================//

/**
 * Get text length, checks for '\0' ending
 * Recommend using Kotlin means of finding this out
 * @return [UInt]
 */
inline fun textLength(text: String) : UInt {
    return TextLength(text)
}

/**
 * Get a piece of a text string
 * Recommend using Kotlin standard lib to do this
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun textSubtext(text: String, position: Int, length: Int) : String {
    return TextSubtext(text, position, length)?.toKString() ?: "Error: Unable to return String"
}

/**
 * Replace text string
 * Recommend using Kotlin standard lib to do this
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun textReplace(text: String, replace: String, by: String, allocator: AutofreeScope) : String {
    memScoped { return TextReplace(text.cstr.getPointer(allocator), replace, by)?.toKString() ?: "Error: Unable to return String" }
}

/**
 * Insert text in a position
 * Recommend using Kotlin standard lib to do this
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun textInsert(text: String, insert: String, position: Int) : String {
    memScoped { return TextInsert(text, insert, position)?.getPointer(this)?.toKString() ?: "Error: Unable to return String" }
}

/**
 * Check if two text string are equal
 * Recommend using Kotlin means of finding this out
 * @return [Boolean]
 */
inline fun textIsEqual(text1: String, text2: String) : Boolean {
    return TextIsEqual(text1, text2)
}

/**
 * Measure string width for default [Font]
 * Recommend using Kotlin standard lib to do this
 * @return [Int]
 */
inline fun measureText(text: String, fontSize: Int) : Int {
    return MeasureText(text, fontSize)
}

/**
 * Measure string size for [Font]
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun measureText(font: Font, text: String, fontSize: Float, spacing: Float, allocator: AutofreeScope) : Vector2 {
    return MeasureTextEx(font.readValue(), text, fontSize, spacing).getPointer(allocator).pointed
}

/**
 * Get index position for a unicode character on font
 * @return [Int]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getGlyphIndex(font: Font, codepoint: Int) : Int {
    return GetGlyphIndex(font.readValue(), codepoint)
}

/**
 * Get glyph font info data for a codepoint (unicode character), fallback to '?' if not found
 * @return [GlyphInfo]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getGlyphInfo(font: Font, codepoint: Int, allocator: AutofreeScope) : GlyphInfo {
    return GetGlyphInfo(font.readValue(), codepoint).getPointer(allocator).pointed
}

/**
 * Get glyph [Rectangle] in [font] atlas for a codepoint (unicode character), fallback to '?' if not found
 * @return [Rectangle]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getGlyphAtlasRec(font: Font, codepoint: Int, allocator: AutofreeScope) : Rectangle {
    return GetGlyphAtlasRec(font.readValue(), codepoint).getPointer(allocator).pointed
}

/**
 * Check if a font is ready
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isFontReady(font: Font) : Boolean {
    return IsFontReady(font.readValue())
}

//=======================================================//
// TEXT STRINGS MANAGEMENT FUNCTIONS
//=======================================================//

// Internal Note: There is no need to bind other related text strings management functions. Use existing Kotlin API.

/**
 * Get Pascal case notation version of provided string
 */
@OptIn(ExperimentalForeignApi::class)
inline fun textToPascal(text: String) : String {
    return TextToPascal(text)?.toKString() ?: "WARNING: Unable to return requested string."

}