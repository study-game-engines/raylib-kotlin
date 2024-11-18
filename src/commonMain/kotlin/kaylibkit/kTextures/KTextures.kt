package kaylibkit.kTextures

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kTextures

//=======================================================//
// TEXTURE LOADING FUNCTIONS
//=======================================================//

/**
 * Load [Texture] from file into GPU memory (VRAM)
 * @return [Texture2D]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadTexture(fileName: String, allocator: AutofreeScope) : Texture2D {
    return LoadTexture(fileName).getPointer(allocator).pointed
}

/**
 * Load [Texture] from image data
 * @return [Texture2D]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadTextureFromImage(image: Image, allocator: AutofreeScope) : Texture2D {
    return LoadTextureFromImage(image.readValue()).getPointer(allocator).pointed
}

/**
 * Load cubemap from image, multiple image cubemap layouts supported
 * @return [TextureCubemap]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadTextureCubemap(image: Image, layout: CubemapLayout, allocator: AutofreeScope) : TextureCubemap {
    return LoadTextureCubemap(image.readValue(), layout.toInt()).getPointer(allocator).pointed
}

/**
 * Load [RenderTexture2D] for rendering (framebuffer)
 * @return [RenderTexture2D]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadRenderTexture(width: Int, height: Int, allocator: AutofreeScope) : RenderTexture2D {
    return LoadRenderTexture(width, height).getPointer(allocator).pointed
}

/**
 * Unload [Texture] from GPU memory (VRAM)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadTexture(texture: Texture2D) {
    return UnloadTexture(texture.readValue())
}

/**
 * Unload render [RenderTexture2D] from GPU memory (VRAM)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadRenderTexture(target: RenderTexture2D) {
    return UnloadRenderTexture(target.readValue())
}

/**
 * Update GPU [Texture] with new data
 */
@OptIn(ExperimentalForeignApi::class)
inline fun updateTexture(texture: Texture2D, pixels: COpaquePointer) {
    UpdateTexture(texture.readValue(), pixels)
}

/**
 * Update GPU [Texture] rectangle with new data
 */
@OptIn(ExperimentalForeignApi::class)
inline fun updateTextureRec(texture: Texture2D, rec: Rectangle, pixels: COpaquePointer) {
    UpdateTextureRec(texture.readValue(), rec.readValue(), pixels)
}

//=======================================================//
// TEXTURE CONFIGURATION FUNCTIONS
//=======================================================//

/**
 * Generate GPU mipmaps for a [Texture]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genTextureMipmaps(texture: Texture2D) {
    GenTextureMipmaps(texture.ptr)
}

/**
 * Set [Texture] scaling filter mode
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setTextureFilter(texture: Texture2D, filter: kaylibkit.kEnums.TextureFilter) {
    SetTextureFilter(texture.readValue(), filter.value)
}

/**
 * Set [Texture] wrapping mode
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setTextureWrap(texture: Texture2D, wrap: kaylibkit.kEnums.TextureWrap) {
    SetTextureWrap(texture.readValue(), wrap.value)
}

//=======================================================//
// TEXTURE DRAWING FUNCTIONS
//=======================================================//

/**
 * Draw a [Texture2D]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawTexture(texture: Texture2D, posX: Int, posY: Int, tint: Color) {
    return DrawTexture(texture.readValue(), posX, posY, tint.readValue())
}

/**
 * Draw a [Texture2D] with position defined as Vector2
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawTextureV(texture: Texture2D, position: Vector2, tint: Color) {
    DrawTextureV(texture.readValue(), position.readValue(), tint.readValue())
}

/**
 * Draw a [Texture2D] with extended parameters
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawTextureEx(texture: Texture2D, position: Vector2, rotation: Float, scale: Float, tint: Color) {
    DrawTextureEx(texture.readValue(), position.readValue(), rotation, scale, tint.readValue())
}

/**
 * Draw a part of a [Texture] defined by a rectangle
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawTextureRec(texture: Texture2D, source: Rectangle, position: Vector2, tint: Color) {
    DrawTextureRec(texture.readValue(), source.readValue(), position.readValue(), tint.readValue())
}

/**
 * Draw a part of a [Texture] defined by a rectangle with 'pro' parameters
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawTexturePro(texture: Texture2D, source: Rectangle, dest: Rectangle, origin: Vector2, rotation: Float, tint: Color) {
    DrawTexturePro(texture.readValue(), source.readValue(), dest.readValue(), origin.readValue(), rotation, tint.readValue())
}

/**
 * Draws a [Texture] (or part of it) that stretches or shrinks nicely
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawTextureNPatch(texture: Texture2D, nPatchInfo: NPatchInfo, dest: Rectangle, origin: Vector2, rotation: Float, tint: Color) {
    DrawTextureNPatch(texture.readValue(), nPatchInfo.readValue(), dest.readValue(), origin.readValue(), rotation, tint.readValue())
}

/**
 * Get [Color] with brightness correction, brightness factor goes from -1.0f to 1.0f
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun colorBrightness(color: Color, factor: Float, allocator: AutofreeScope) : Color {
    return ColorBrightness(color.readValue(), factor).getPointer(allocator).pointed
}

/**
 * Get color multiplied with another [Color]
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun colorTint(color: Color, tint: Color, allocator: AutofreeScope) : Color {
    return ColorTint(color.readValue(), tint.readValue()).getPointer(allocator).pointed
}

/**
 * Get [Color] with contrast correction
 * NOTE: Contrast values between -1.0f and 1.0f
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun colorContrast(color: Color, contrast: Float, allocator: AutofreeScope) : Color {
    return ColorContrast(color.readValue(), contrast).getPointer(allocator).pointed
}

/**
 * Generate image: perlin noise
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genImagePerlinNoise(width: Int, height: Int, offsetX: Int, offsetY: Int, scale: Float, allocator: AutofreeScope) : Image {
    return GenImagePerlinNoise(width, height, offsetX, offsetY, scale).getPointer(allocator).pointed
}

/**
 * Generate image: grayscale image from text data
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genImageText(width: Int, height: Int, text: String, allocator: AutofreeScope) : Image {
    return GenImageText(width, height, text).getPointer(allocator).pointed
}

/**
 * Check if a [Texture] is ready
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isTextureReady(texture: Texture2D) : Boolean {
    return IsTextureReady(texture.readValue())
}

/**
 * Check if a [RenderTexture] is ready
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isRenderTextureReady(target: RenderTexture2D) : Boolean {
    return IsRenderTextureReady(target.readValue())
}

/**
 * Set value of a Texture with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Texture.set(other: Texture) {
    this.format = other.format
    this.height = other.height
    this.id = other.id
    this.mipmaps = other.mipmaps
    this.width = other.width
}