package kaylibkit.kImage

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kImage

//=======================================================//
// IMAGE CONSTRUCTOR
//=======================================================//

/**
 * Constructor function for [Image].
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kImage(data: COpaquePointer?, width: Int, height: Int, mipmaps: Int, format: Int, allocator: AutofreeScope) : Image {
    return allocator.alloc<Image> {
        this.data = data
        this.width = width
        this.height = height
        this.mipmaps = mipmaps
        this.format = format
    }
}

//=======================================================//
// IMAGE LOADING FUNCTIONS
//=======================================================//

/**
 * Load [Image] from file into CPU memory (RAM)
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadImage(fileName: String, allocator: AutofreeScope) : Image {
    return LoadImage(fileName).getPointer(allocator).pointed
}

/**
 * Load [Image] from RAW file data
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadImageRaw(fileName: String, width: Int, height: Int, format: PixelFormat, headerSize: Int, allocator: AutofreeScope) : Image {
    return LoadImageRaw(fileName, width, height, format.toInt(), headerSize).getPointer(allocator).pointed
}

/**
 * Load [Image] sequence from file (frames appended to image.data)
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadImageAnim(fileName: String, frames: IntVar, allocator: AutofreeScope) : Image {
    return LoadImageAnim(fileName, frames.ptr).getPointer(allocator).pointed
}

/**
 * Load [Image] from memory buffer, fileType refers to extension: i.e. `.png`
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadImageFromMemory(fileType: String, fileData: UByteVar, dataSize: Int, allocator: AutofreeScope) : Image {
    return LoadImageFromMemory(fileType, fileData.ptr, dataSize).getPointer(allocator).pointed
}

/**
 * Load [Image] from GPU texture data
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadImageFromTexture(texture: Texture2D, allocator: AutofreeScope) : Image {
    return LoadImageFromTexture(texture.readValue()).getPointer(allocator).pointed
}

/**
 * Load image from screen buffer and (screenshot)
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadImageFromScreen(allocator: AutofreeScope) : Image {
    return LoadImageFromScreen().getPointer(allocator).pointed
}

/**
 * Unload [Image] from CPU memory (RAM)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadImage(image: Image) {
    UnloadImage(image.readValue())
}

/**
 * Export [Image] data to file, returns true on success
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun exportImage(image: Image, fileName: String) : Boolean {
    return ExportImage(image.readValue(), fileName)
}

/**
 * Export [Image] as code file defining an array of bytes, returns true on success
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun exportImageAsCode(image: Image, fileName: String) : Boolean {
    return ExportImageAsCode(image.readValue(), fileName)
}

//=======================================================//
// IMAGE GENERATION FUNCTIONS
//=======================================================//

/**
 * Generate [Image]: plain [color]
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genImageColor(width: Int, height: Int, color: Color, allocator: AutofreeScope) : Image {
    return GenImageColor(width, height, color.readValue()).getPointer(allocator).pointed
}

/**
 * Generate [Image]: vertical gradient
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genImageGradientV(width: Int, height: Int, top: Color, bottom: Color, allocator: AutofreeScope) : Image {
    return GenImageGradientV(width, height, top.readValue(), bottom.readValue()).getPointer(allocator).pointed
}

/**
 * Generate [Image]: horizontal gradient
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genImageGradientH(width: Int, height: Int, left: Color, right: Color, allocator: AutofreeScope) : Image {
    return GenImageGradientH(width, height, left.readValue(), right.readValue()).getPointer(allocator).pointed
}

/**
 * Generate [Image]: radial gradient
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genImageGradientRadial(width: Int, height: Int, density: Float, inner: Color, outer: Color, allocator: AutofreeScope) : Image {
    return GenImageGradientRadial(width, height, density, inner.readValue(), outer.readValue()).getPointer(allocator).pointed
}

/**
 * Generate [Image]: checked
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genImageChecked(width: Int, height: Int, checksX: Int, checksY: Int, col1: Color, col2: Color, allocator: AutofreeScope) : Image {
    return GenImageChecked(width, height, checksX, checksY, col1.readValue(), col2.readValue()).getPointer(allocator).pointed
}

/**
 * Generate [Image]: white noise
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genImageWhiteNoise(width: Int, height: Int, factor: Float, allocator: AutofreeScope) : Image {
    return GenImageWhiteNoise(width, height, factor).getPointer(allocator).pointed
}

/**
 * Generate [Image]: cellular algorithm, bigger tileSize means bigger cells
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genImageCellular(width: Int, height: Int, tileSize: Int, allocator: AutofreeScope) : Image {
    return GenImageCellular(width, height, tileSize).getPointer(allocator).pointed
}

//=======================================================//
// IMAGE MANIPULATION FUNCTIONS
//=======================================================//

/**
 * Create an [Image] duplicate (useful for transformations)
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageCopy(image: Image, allocator: AutofreeScope) : Image {
    return ImageCopy(image.readValue()).getPointer(allocator).pointed
}

/**
 * Create an [Image] from another [Image] piece
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageFromImage(image: Image, rec: Rectangle, allocator: AutofreeScope) : Image {
    return ImageFromImage(image.readValue(), rec.readValue()).getPointer(allocator).pointed
}

/**
 *  Create an [Image] from text (default font)
 *  @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageText(text: String, fontSize: Int, color: Color, allocator: AutofreeScope) : Image {
    return ImageText(text, fontSize, color.readValue()).getPointer(allocator).pointed
}

/**
 * Create an [Image] from text (custom sprite font)
 * @return [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageTextEx(font: Font, text: String, fontSize: Float, spacing: Float, tint: Color, allocator: AutofreeScope) : Image {
    return ImageTextEx(font.readValue(), text, fontSize, spacing, tint.readValue()).getPointer(allocator).pointed
}

/**
 * Convert [Image] data to desired format
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageFormat(image: Image, newFormat: kaylibkit.kEnums.PixelFormat) {
    ImageFormat(image.ptr, newFormat.value)
}

/**
 * Convert [Image] to POT (power-of-two)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageToPOT(image: Image, fill: Color) {
    ImageToPOT(image.ptr, fill.readValue())
}

/**
 * Crop an [Image] to a defined [Rectangle]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageCrop(image: Image, crop: Rectangle) {
    ImageCrop(image.ptr, crop.readValue())
}

/**
 * Crop [Image] depending on alpha value
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageAlphaCrop(image: Image, threshold: Float) {
    ImageAlphaCrop(image.ptr, threshold)
}

/**
 * Clear alpha channel to desired [color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageAlphaClear(image: Image, color: Color, threshold: Float) {
    ImageAlphaClear(image.ptr, color.readValue(), threshold)
}

/**
 * Apply alpha mask to [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageAlphaMask(image: Image, alphaMask: Image) {
    ImageAlphaMask(image.ptr, alphaMask.readValue())
}

/**
 * Premultiply alpha channel
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageAlphaPremultiply(image: Image) {
    ImageAlphaPremultiply(image.ptr)
}

/**
 * Apply Gaussian blur using a box blur approximation
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageBlurGaussian(image: Image, blurSize: Int) {
    ImageBlurGaussian(image.ptr, blurSize)
}

/**
 * Resize [Image] (Bicubic scaling algorithm)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageResize(image: Image, newWidth: Int, newHeight: Int) {
    ImageResize(image.ptr, newWidth, newHeight)
}

/**
 * Resize [Image] (Nearest-Neighbor scaling algorithm)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageResizeNN(image: Image, newWidth: Int, newHeight: Int) {
    ImageResizeNN(image.ptr, newWidth, newHeight)
}

/**
 * Resize canvas and fill with [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageResizeCanvas(image: Image, newWidth: Int, newHeight: Int, offsetX: Int, offsetY: Int, fill: Color) {
    ImageResizeCanvas(image.ptr, newWidth, newHeight, offsetX, offsetY, fill.readValue())
}

/**
 * Compute all mipmap levels for a provided [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageMipmaps(image: Image) {
    ImageMipmaps(image.ptr)
}

/**
 * Dither [Image] data to 16bpp or lower (Floyd-Steinberg dithering)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDither(image: Image, rBpp: Int, gBpp: Int, bBpp: Int, aBpp: Int) {
    ImageDither(image.ptr, rBpp, gBpp, bBpp, aBpp)
}

/**
 * Flip [Image] vertically
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageFlipVertical(image: Image) {
    ImageFlipVertical(image.ptr)
}

/**
 * Flip [Image] horizontally
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageFlipHorizontal(image: Image) {
    ImageFlipHorizontal(image.ptr)
}

/**
 * Rotate [Image] clockwise 90deg
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageRotateCW(image: Image) {
    ImageRotateCW(image.ptr)
}

/**
 * Rotate [Image] counter-clockwise 90deg
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageRotateCCW(image: Image) {
    ImageRotateCCW(image.ptr)
}

/**
 * Modify [Image] [Color]: tint
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageColorTint(image: Image, color: Color) {
    ImageColorTint(image.ptr, color.readValue())
}

/**
 * Modify [Image] [Color]: invert
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageColorInvert(image: Image) {
    ImageColorInvert(image.ptr)
}

/**
 * Modify [Image] [Color]: grayscale
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageColorGrayscale(image: Image) {
    ImageColorGrayscale(image.ptr)
}

/**
 * Modify [Image] [Color]: contrast (-100 to 100)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageColorContrast(image: Image, contrast: Float) {
    ImageColorContrast(image.ptr, contrast)
}

/**
 * Modify [Image] [Color]: brightness (-255 to 255)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageColorBrightness(image: Image, brightness: Int) {
    ImageColorBrightness(image.ptr, brightness)
}

/**
 * Modify [Image] [color]: replace [color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageColorReplace(image: Image, color: Color, replace: Color) {
    ImageColorReplace(image.ptr, color.readValue(), replace.readValue())
}

/**
 * Load color data from [Image] as a [Color] [Array] (RGBA - 32bit) Memory allocated should be freed
 * @return [Array] of [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadImageColors(image: Image): Array<Color> {
    val colorPointer = LoadImageColors(image.readValue())
    val colorList = mutableListOf<Color>()
    colorPointer?.pointed?.let { colorList.add(it) }
    return colorList.toTypedArray()
}

/**
 * Load colors palette from [Image] as a [Color] [Array] (RGBA - 32bit)
 * @return [Array] of [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadImagePalette(image: Image, maxPaletteSize: Int, colorCount: IntVar) : Array<Color> {
    val result = LoadImagePalette(image.readValue(), maxPaletteSize, colorCount.ptr)
    val colorList = mutableListOf<Color>()
    result?.pointed?.let { colorList.add(it) }
    return colorList.toTypedArray()
}

/**
 * Unload [Color] data loaded with LoadImageColors() Read Deprecated note
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadImageColors(colors: Color) {
    UnloadImageColors(colors.ptr)
}


/**
 * Unload [colors] palette loaded with LoadImagePalette() Read Deprecated note
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadImagePalette(colors: Color) {
    UnloadImagePalette(colors.ptr)
}

/**
 * Get [Image] alpha border [Rectangle]
 * @return [Rectangle]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getImageAlphaBorder(image: Image, threshold: Float, allocator: AutofreeScope) : Rectangle {
    return GetImageAlphaBorder(image.readValue(), threshold).getPointer(allocator).pointed
}

/**
 * Get [Image] pixel [Color] at (x, y) position
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getImageColor(image: Image, x: Int, y: Int, allocator: AutofreeScope) : Color {
    return GetImageColor(image.readValue(), x, y).getPointer(allocator).pointed
}

//=======================================================//
// IMAGE DRAWING FUNCTIONS
//=======================================================//

/**
 * Clear [Image] background with given [color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageClearBackground(dst: Image, color: Color) {
    ImageClearBackground(dst.ptr, color.readValue())
}

/**
 * Draw pixel within an [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawPixel(dst: Image, posX: Int, posY: Int, color: Color) {
    ImageDrawPixel(dst.ptr, posX, posY, color.readValue())
}

/**
 * Draw pixel within an [Image] ([Vector2] version)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawPixelV(dst: Image, position: Vector2, color: Color) {
    ImageDrawPixelV(dst.ptr, position.readValue(), color.readValue())
}

/**
 * Draw line within an [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawLine(dst: Image, startPosX: Int, startPosY: Int, endPosX: Int, endPosY: Int, color: Color) {
    ImageDrawLine(dst.ptr, startPosX, startPosY, endPosX, endPosY, color.readValue())
}

/**
 * Draw line within an [Image] ([Vector2] version)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawLineV(dst: Image, start: Vector2, end: Vector2, color: Color) {
    ImageDrawLineV(dst.ptr, start.readValue(), end.readValue(), color.readValue())
}

/**
 * Draw circle within an [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawCircle(dst: Image, centerX: Int, centerY: Int, radius: Int, color: Color) {
    ImageDrawCircle(dst.ptr, centerX, centerY, radius, color.readValue())
}

/**
 * Draw circle within an [Image] ([Vector2] version)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawCircleV(dst: Image, center: Vector2, radius: Int, color: Color) {
    ImageDrawCircleV(dst.ptr, center.readValue(), radius, color.readValue())
}

/**
 * Draw [Rectangle] within an [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawRectangle(dst: Image, posX: Int, posY: Int, width: Int, height: Int, color: Color) {
    ImageDrawRectangle(dst.ptr, posX, posY, width, height, color.readValue())
}

/**
 * Draw [Rectangle] within an [Image] ([Vector2] version)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawRectangleV(dst: Image, position: Vector2, size: Vector2, color: Color) {
    ImageDrawRectangleV(dst.ptr, position.readValue(), size.readValue(), color.readValue())
}

/**
 * Draw [Rectangle] within an [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawRectangleRec(dst: Image, rec: Rectangle, color: Color) {
    ImageDrawRectangleRec(dst.ptr, rec.readValue(), color.readValue())
}

/**
 * Draw [Rectangle] lines within an [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawRectangleLines(dst: Image, rec: Rectangle, thick: Int, color: Color) {
    ImageDrawRectangleLines(dst.ptr, rec.readValue(), thick, color.readValue())
}

/**
 * Draw a source [Image] within a destination [Image] (tint applied to source)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDraw(dst: Image, src: Image, srcRec: Rectangle, dstRec: Rectangle, tint: Color) {
    ImageDraw(dst.ptr, src.readValue(), srcRec.readValue(), dstRec.readValue(), tint.readValue())
}

/**
 * Draw text (using default font) within an [Image] (destination)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawText(dst: Image, text: String, posX: Int, posY: Int, fontSize: Int, color: Color) {
    return ImageDrawText(dst.ptr, text, posX, posY, fontSize, color.readValue())
}

/**
 * Draw text (custom sprite font) within an [Image] (destination)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawText(dst: Image, font: Font, text: String, position: Vector2, fontSize: Float, spacing: Float, tint: Color) {
    return ImageDrawTextEx(dst.ptr, font.readValue(), text, position.readValue(), fontSize, spacing, tint.readValue())
}

/**
 * Draw circle outline within an [Image]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawCircleLines(image: Image, centerX: Int, centerY: Int, radius: Int, color: Color) {
    ImageDrawCircleLines(image.ptr, centerX, centerY, radius, color.readValue())
}

/**
 * Draw circle outline within an [Image] using [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun imageDrawCircleLines(image: Image, center: Vector2, radius: Int, color: Color) {
    ImageDrawCircleLines(image.ptr, center.x.toInt(), center.y.toInt(), radius, color.readValue())
}

/**
 * Check if an [Image] is ready
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isImageReady(image: Image) : Boolean {
    return IsImageReady(image.readValue())
}

/**
 * Set value of an [Image] with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
@OptIn(ExperimentalForeignApi::class)
inline fun Image.set(other: Image) {
    this.format = other.format
    this.height = other.height
    this.data = other.data
    this.mipmaps = other.mipmaps
    this.width = other.width
}