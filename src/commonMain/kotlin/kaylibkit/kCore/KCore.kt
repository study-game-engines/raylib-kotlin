package kaylibkit.kCore

import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kEnums.TraceLogLevel
import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kCore

//=======================================================//
// RAYLIB CORE FUNCTIONS
//=======================================================//

/**
* Initialize window and OpenGL context
*/
inline fun initWindow(width: Int, height: Int, title: String?) {
    return InitWindow(width, height, title)
}

/**
 * Check if KEY_ESCAPE pressed or Close icon pressed
 */
inline val windowShouldClose: Boolean
    get() { return WindowShouldClose() }

/**
 * Return Kaylib version
 */
val kaylibVersion: String = "1.0.3 - Cosmic Clash"

/**
 * Setup canvas (framebuffer) to start drawing, then calls [block].
 *
 * Drawing will end after [block] has finished.
 */
inline fun drawing(block: () -> Unit) {
    beginDrawing()
    block()
    endDrawing()
}

/**
 * Close window and unload OpenGL context
 */
inline fun closeWindow() {
    return CloseWindow()
}

/**
 * Check if window has been initialized successfully
 */
inline val isWindowReady: Boolean
    get() { return IsWindowReady() }

/**
 * Check if window is currently fullscreen
 */
inline val isWindowFullscreen: Boolean
    get() { return IsWindowFullscreen() }

/**
 * Check if window is currently hidden (only PLATFORM_DESKTOP)
 */
inline val isWindowHidden: Boolean
    get() { return IsWindowHidden() }

/**
 * Check if window is currently minimized (only PLATFORM_DESKTOP)
 */
inline val isWindowMinimized: Boolean
    get() { return IsWindowMinimized() }

/**
 * Check if window is currently maximized (only PLATFORM_DESKTOP)
 */
inline val isWindowMaximized: Boolean
    get() { return IsWindowMaximized() }

/**
 * Check if window is currently focused (only PLATFORM_DESKTOP)
 */
inline val isWindowFocused: Boolean
    get() { return IsWindowFocused() }

/**
 * Check if window has been resized last frame
 */
inline val isWindowResized: Boolean
    get() { return IsWindowResized() }

/**
 * Check if one specific window [flags] is enabled
 */
inline fun isWindowState(flags: ConfigFlag) : Boolean {
    return IsWindowState(flags.value)
}

/**
 * Set window configuration state using [flags]
 */
inline fun setWindowState(flags: ConfigFlag) {
    SetWindowState(flags.value)
}

/**
 * Clear window configuration state [flags]
 */
inline fun clearWindowState(flags: ConfigFlag) {
    ClearWindowState(flags.value)
}

/**
 * Clear window configuration state flags
 */
inline fun toggleFullscreen() {
    ToggleFullscreen()
}

/**
 * Clear window configuration state flags
 */
inline fun maximizeWindow() {
    MaximizeWindow()
}

/**
 * Set window state: minimized, if resizable (only PLATFORM_DESKTOP)
 */
inline fun minimizeWindow() {
    MinimizeWindow()
}

/**
 * Set window state: not minimized/maximized (only PLATFORM_DESKTOP)
 */
inline fun restoreWindow() {
    RestoreWindow()
}

/**
 * Set icon for window (only PLATFORM_DESKTOP)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setWindowIcon(image: Image) {
    SetWindowIcon(image.readValue())
}

/**
 * Set [title] for window (only PLATFORM_DESKTOP)
 */
inline fun setWindowTitle(title: String) {
    title.apply { SetWindowTitle(this) }
}

/**
 * Set window position on screen (only PLATFORM_DESKTOP)
 */
inline fun setWindowPosition(x: Int, y: Int) {
    SetWindowPosition(x, y)
}

/**
 * Set window position on screen (only PLATFORM_DESKTOP)
 */
inline fun setWindowMonitor(monitor: Int) {
    SetWindowMonitor(monitor)
}

/**
 * Set window position on screen (only PLATFORM_DESKTOP)
 */
inline fun setWindowMinSize(width: Int, height: Int) {
    SetWindowMinSize(width, height)
}

/**
 * Get native window handle
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getWindowHandle(): COpaquePointer? {
    return GetWindowHandle()
}

/**
 * Get current screen width
 * @return [Int]
 */
inline fun getScreenWidth() : Int {
    return GetScreenWidth()
}

/**
 *Get current screen height
 * @return [Int]
 */
inline fun getScreenHeight() : Int {
    return GetScreenHeight()
}


/**
 * Get number of connected monitors
 * @return [Int]
 */
inline fun getMonitorCount() : Int {
    return GetMonitorCount()
}

/**
 * Get current connected monitor
 * @return [Int]
 */
inline fun getCurrentMonitor() : Int {
    return GetCurrentMonitor()
}

/**
 * Get specified [monitor] position
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getMonitorPosition(monitor: Int, allocator: AutofreeScope) : Vector2 {
    return GetMonitorPosition(monitor).getPointer(allocator).pointed
}

/**
 * Get specified [monitor] width (max available by monitor)
 * @return [Int]
 */
inline fun getMonitorWidth(monitor: Int) : Int {
    return GetMonitorWidth(monitor)
}

/**
 * Get specified [monitor] height (max available by monitor)
 * @return [Int]
 */
inline fun getMonitorHeight(monitor: Int) : Int {
    return GetMonitorHeight(monitor)
}

/**
 * Get specified [monitor] physical width in millimetres
 * @return [Int]
 */
inline fun getMonitorPhysicalWidth(monitor: Int) : Int {
    return GetMonitorPhysicalWidth(monitor)
}

/**
 * Get specified [monitor] physical height in millimetres
 * @return [Int]
 */
inline fun getMonitorPhysicalHeight(monitor: Int) : Int {
    return GetMonitorPhysicalHeight(monitor)
}

/**
 * Get window position XY on monitor
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getWindowPosition(allocator: AutofreeScope): Vector2 {
    return GetWindowPosition().getPointer(allocator).pointed
}

/**
 * Get window scale DPI factor
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getWindowScaleDPI(allocator: AutofreeScope) : Vector2 {
    return GetWindowScaleDPI().getPointer(allocator).pointed
}

/**
 * Get the human-readable, UTF-8 encoded name of the primary [monitor]
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getMonitorName(monitor: Int) : String {
    return GetMonitorName(monitor)?.toKStringFromUtf8() ?: "WARNING: GLFW: Failed to find selected monitor"
}

/**
 * Set clipboard [text] content
 */
inline fun setClipboardText(text: String) {
    SetClipboardText(text)
}

/**
 * Get clipboard text content
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getClipboardText() : String {
    return GetClipboardText()?.toKStringFromUtf8() ?: ""
}
//=======================================================//
// FRAME CONTROLS FUNCTIONS
//=======================================================//
/**
 * Swap back buffer with front buffer (screen drawing)
 */
inline fun swapScreenBuffer() {
    SwapScreenBuffer()
}

/**
 * Register all input events
 */
inline fun pollInputEvents() {
    PollInputEvents()
}

/**
 * Wait for some milliseconds (halt program execution)
 * NOTE: Sleep() granularity could be around 10 ms, it means, Sleep() could
 * take longer than expected... for that reason we use the busy wait loop
 */
inline fun waitTime(ms: Double) {
    WaitTime(ms)
}

//=======================================================//
// CURSOR FUNCTIONS
//=======================================================//

/**
 * Show cursor
 */
inline fun showCursor() {
    ShowCursor()
}

/**
 * Hide cursor
 */
inline fun hideCursor() {
    HideCursor()
}

/**
 * Check if cursor is not visible
 * @return [Boolean]
 */
inline fun isCursorHidden() : Boolean {
    return IsCursorHidden()
}

/**
 * Enables cursor (unlock cursor)
 */
inline fun enableCursor() {
    EnableCursor()
}

/**
 * Disables cursor (lock cursor)
 */
inline fun disableCursor() {
    DisableCursor()
}

inline val isCursorOnScreen: Boolean
    get() { return IsCursorOnScreen() }

//=======================================================//
// DRAWING FUNCTIONS
//=======================================================//

/**
 * Set background [color] (framebuffer clear [color])
 */
@OptIn(ExperimentalForeignApi::class)
inline fun clearBackground(color: Color) {
   ClearBackground(color.readValue())
}

/**
 * Setup canvas (framebuffer) to start drawing
 */
inline fun beginDrawing() {
    BeginDrawing()
}

/**
 * End canvas drawing and swap buffers (double buffering)
 */
inline fun endDrawing() {
    EndDrawing()
}

/**
 * Begin 2D mode with custom [camera] (2D)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun beginMode2D(camera: Camera2D) {
    BeginMode2D(camera.readValue())
}

/**
 * Ends 2D mode with custom camera
 */
inline fun endMode2D() {
    EndMode2D()
}

/**
 * Setup 2D mode with custom [camera] to start 2D Mode, then calls [block].
 *
 * Mode2D will end after [block] has finished.
 * @param [camera] accepts [Camera2D]
 */
inline fun mode2D(camera: Camera2D, block: () -> Unit) {
    beginMode2D(camera)
    block()
    endMode2D()
}


/**
 * Begin 3D mode with custom [camera] (3D)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun beginMode3D(camera: Camera3D) {
    BeginMode3D(camera.readValue())
}


/**
 * Ends 3D mode and returns to default 2D orthographic mode
 */
inline fun endMode3D() {
    EndMode3D()
}

/**
 * Setup 3D mode with custom [camera] to start 3D Mode, then calls [block].
 *
 * Mode3D will end after [block] has finished.
 * @param [camera] accepts [Camera3D]
 */
inline fun mode3D(camera: Camera3D, block: () -> Unit) {
    beginMode3D(camera)
    block()
    endMode3D()
}

/**
 * Begin drawing to render texture
 */
@OptIn(ExperimentalForeignApi::class)
inline fun beginTextureMode(target: RenderTexture2D) {
    BeginTextureMode(target.readValue())
}

/**
 * Ends drawing to render texture
 */
inline fun endTextureMode() {
    EndTextureMode()
}

/**
 * Setup [texture] mode to draw to render texture, then calls [block].
 *
 * texture mode will end after [block] has finished.
 * @param [texture] accepts [RenderTexture2D]
 */
inline fun textureMode(texture: RenderTexture2D, block: () -> Unit) {
    beginTextureMode(texture)
    block()
    endTextureMode()
}

/**
 * Begin custom [shader] drawing
 */
@OptIn(ExperimentalForeignApi::class)
inline fun beginShaderMode(shader: Shader) {
    BeginShaderMode(shader.readValue())
}

/**
 * End custom shader drawing (use default shader)
 */
inline fun endShaderMode() {
    EndShaderMode()
}

/**
 * Setup custom [shader] mode then calls [block].
 *
 * shader mode will end after [block] has finished.
 * @param [shader] accepts [Shader]
 */
inline fun shaderMode(shader: Shader, block: () -> Unit) {
    beginShaderMode(shader)
    block()
    endShaderMode()
}

/**
 * Begin blending [mode] (alpha, additive, multiplied), subtract, custom)
 */
inline fun beginBlendMode(mode: kaylibkit.kEnums.BlendMode) {
    BeginBlendMode(mode.value)
}

/**
 * End blending mode (reset to default: alpha blending)
 */
inline fun endBlendMode() {
    EndBlendMode()
}

/**
 * Setup blending [mode], then calls [block].
 *
 * blend mode will end after [block] has finished.
 * @param [mode] accepts [kaylibkit.kEnums.BlendMode]
 */
inline fun blendMode(mode: kaylibkit.kEnums.BlendMode, block: () -> Unit) {
    beginBlendMode(mode)
    block()
    endBlendMode()
}

/**
 * Begin scissor mode (define screen area for following drawing)
 */
inline fun beginScissorMode(x: Int, y: Int, width: Int, height: Int) {
    BeginScissorMode(x, y, width, height)
}

/**
 * End scissor mode
 */
inline fun endScissorMode() {
    EndScissorMode()
}

/**
 * Setup scissor mode then calls [block].
 *
 * scissor mode will end after [block] has finished.
 * @param [x] accept [Int]
 * @param [y] accept [Int]
 * @param [width] accept [Int]
 * @param [height] accept [Int]
 */
inline fun scissorMode(x: Int, y: Int, width: Int, height: Int, block: () -> Unit) {
    beginScissorMode(x, y, width, height)
    block()
    endShaderMode()
}

/**
 * Begin stereo rendering (requires VR simulator)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun beginVrStereoMode(config: VrStereoConfig) {
    BeginVrStereoMode(config.readValue())
}

/**
 * End stereo rendering (requires VR simulator)
 */
inline fun endVrStereoMode() {
    EndVrStereoMode()
}

/**
 * Setup stereo rendering mode, then calls [block].
 *
 * stereo rendering mode will end after [block] has finished.
 * @param [config] accepts [VrStereoConfig]
 */
inline fun vrMode(config: VrStereoConfig, block: () -> Unit) {
    beginVrStereoMode(config)
    block()
    endVrStereoMode()
}

//=======================================================//
// VR STEREO CONFIG FUNCTIONS
//=======================================================//

/**
 * Load VR stereo config for VR simulator [device] parameters
 * @return [VrStereoConfig]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadVrStereoConfig(device: VrDeviceInfo, allocator: AutofreeScope) : VrStereoConfig {
    return LoadVrStereoConfig(device.readValue()).getPointer(allocator).pointed
}

/**
 * Unload VR stereo [config]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadVrStereoConfig(config: VrStereoConfig) {
    UnloadVrStereoConfig(config.readValue())
}

//=======================================================//
// SHADER MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Load [Shader] from files and bind default locations
 * @return [Shader]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadShader(vsFileName: String, fsFileName: String, allocator: AutofreeScope) : Shader {
    return LoadShader(vsFileName, fsFileName).getPointer(allocator).pointed
}

/**
 * Load [Shader] from code strings and bind default locations
 * @return [Shader]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadShaderFromMemory(vsCode: String, fsCode: String, allocator: AutofreeScope) : Shader {
    return LoadShaderFromMemory(vsCode, fsCode).getPointer(allocator).pointed
}

/**
 * Get [Shader] uniform location
 * @return [Int]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getShaderLocation(shader: Shader, uniformName: String) : Int {
    return GetShaderLocation(shader.readValue(), uniformName)
}

/**
 * Get [shader] attribute location
 * @return [Int]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getShaderLocationAttrib(shader: Shader, attribName: String) : Int {
    return GetShaderLocationAttrib(shader.readValue(), attribName)
}

/**
 * Set [shader] uniform value
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setShaderValue(shader: Shader, locIndex: kaylibkit.kEnums.ShaderLocationIndex, value: COpaquePointer, uniformType: kaylibkit.kEnums.ShaderUniformDataType) {
    return SetShaderValue(shader.readValue(), locIndex.value, value, uniformType.value)
}

/**
 * Set [shader] uniform value vector
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setShaderValueV(shader: Shader, locIndex: kaylibkit.kEnums.ShaderLocationIndex, value: COpaquePointer, uniformType: kaylibkit.kEnums.ShaderUniformDataType, count: Int) {
    SetShaderValueV(shader.readValue(), locIndex.value, value, uniformType.value, count)
}

/**
 * Set [shader] uniform value ([Matrix] 4x4)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setShaderValueMatrix(shader: Shader, locIndex: kaylibkit.kEnums.ShaderLocationIndex, mat: Matrix) {
    SetShaderValueMatrix(shader.readValue(), locIndex.value, mat.readValue())
}

/**
 * Set [shader] uniform value for texture (sampler2d)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setShaderValueTexture(shader: Shader, locIndex: kaylibkit.kEnums.ShaderLocationIndex, texture: Texture2D) {
    SetShaderValueTexture(shader.readValue(), locIndex.value, texture.readValue())
}

/**
 * Unload [shader] from GPU memory (VRAM)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadShader(shader: Shader) {
    UnloadShader(shader.readValue())
}

//=======================================================//
// SCREEN-SPACE MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Get a [Ray] trace from mouse position
 * @return [Ray]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getMouseRay(mousePosition: Vector2, camera: Camera, allocator: AutofreeScope) : Ray {
    return GetMouseRay(mousePosition.readValue(), camera.readValue()).getPointer(allocator).pointed
}

/**
 * Get [camera] transform [Matrix] (view [Matrix]])
 * @return [Matrix]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getCameraMatrix(camera: Camera, allocator: AutofreeScope) : Matrix {
    return GetCameraMatrix(camera.readValue()).getPointer(allocator).pointed
}

/**
 * Get [camera] 2d transform [Matrix]
 * @return [Matrix]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getCameraMatrix2D(camera: Camera2D, allocator: AutofreeScope) : Matrix {
    return GetCameraMatrix2D(camera.readValue()).getPointer(allocator).pointed
}

/**
 * Get the screen space position for a 3d world space [position]
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getWorldToScreen(position: Vector3, camera: Camera, allocator: AutofreeScope) : Vector2 {
    return GetWorldToScreen(position.readValue(), camera.readValue()).getPointer(allocator).pointed
}

/**
 * Get size position for a 3d world space [position]
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getWorldToScreenEx(position: Vector3, camera: Camera, width: Int, height: Int, allocator: AutofreeScope) : Vector2 {
    return GetWorldToScreenEx(position.readValue(), camera.readValue(), width, height).getPointer(allocator).pointed
}

/**
 * Get the screen space [position] for a 2d [camera] world space position
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getWorldToScreen2D(position: Vector2, camera: Camera2D, allocator: AutofreeScope) : Vector2 {
    return GetWorldToScreen2D(position.readValue(), camera.readValue()).getPointer(allocator).pointed
}

/**
 * Get the world space position for a 2d [camera] screen space position
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getScreenToWorld2D(position: Vector2, camera: Camera2D, allocator: AutofreeScope) : Vector2 {
    return GetScreenToWorld2D(position.readValue(), camera.readValue()).getPointer(allocator).pointed
}

//=======================================================//
// TIMING FUNCTIONS
//=======================================================//

/**
 * Set target FPS (maximum)
 */
inline fun setTargetFPS(fps: Int) {
    SetTargetFPS(fps)
}

/**
 * Get current FPS
 * @return [Int]
 */
inline fun getFPS() : Int {
    return GetFPS()
}

/**
 * Get time in seconds for last frame drawn (delta time)
 * @return [Float]
 */
inline fun getFrameTime() : Float {
    return GetFrameTime()
}

/**
 * Get elapsed time in seconds since InitWindow()
 * @return [Double]
 */
inline fun getTime() : Double {
    return GetTime()
}

//=======================================================//
// MISC FUNCTIONS
//=======================================================//

/**
 * Returns a random value between [min] and [max] (both included)
 * @return [Int]
 */
inline fun getRandomValue(min: Int, max: Int) : Int {
    return GetRandomValue(min, max)
}

/**
 * Set the [seed] for the random number generator
 */
inline fun setRandomSeed(seed: UInt) {
    SetRandomSeed(seed)
}

/**
 * Takes a screenshot of current screen (filename extension defines format)
 */
inline fun takeScreenshot(fileName: String) {
    TakeScreenshot(fileName)
}

/**
 * Setup init configuration [flags] (view FLAGS)
 */
inline fun setConfigFlags(flags: kaylibkit.kEnums.ConfigFlag) {
    SetConfigFlags(flags.value)
}

/**
 * Show trace log messages (LOG_DEBUG, LOG_INFO, LOG_WARNING, LOG_ERROR...)
 * Vararg parameter of type Any does not translate to any C type. You can only use traceLog with log level and text(String)
 */
inline fun traceLog(logLevel: TraceLogLevel, text: String) {
    return TraceLog(logLevel.level, text)
}

/**
 * Set the current threshold (minimum) log level
 */
inline fun setTraceLogLevel(logLevel: TraceLogLevel) {
    SetTraceLogLevel(logLevel.level)
}

//=======================================================//
// CUSTOM CALLBACKS FUNCTIONS
//=======================================================//


/**
 * Logging: Redirect trace log messages
 */
@OptIn(ExperimentalForeignApi::class)
typealias TraceLogCallback = CPointer<CFunction<(Int, CPointer<ByteVar>?, platform.posix.va_list?) -> Unit>>

/**
 * A custom exception class for resource path-related errors.
 *
 * This exception is used to indicate errors related to the retrieval and processing
 * of resource paths. It can be thrown when issues occur while obtaining or manipulating
 * resource paths, providing a descriptive [message] to explain the specific error.
 *
 * @param message A descriptive error message explaining the reason for the exception.
 *
 * @see RuntimeException
 */
class ResourcePathException(message: String) : RuntimeException(message)

/**
 * Returns the absolute path to the 'resources' folder located at the same location as your executable.
 *
 * This property provides the path to the 'resources' folder associated with your executable.
 * Ensure that a folder named 'resources' is present in the same directory as your executable.
 *
 * @return The absolute path to the 'resources' folder, or null if it doesn't exist or cannot be determined.
 */
internal expect val resourcePath: String?

/**
 * Set custom trace log
 * @param callback takes in a pointer to a C Function.
 */
@OptIn(ExperimentalForeignApi::class)
@PublishedApi
internal expect fun setTraceLogCallbackInternal(callback: TraceLogCallback)

/**
 * Set custom file binary data loader
 * @param callback takes in a pointer to a C Function.
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setLoadFileDataCallback(callback: LoadFileDataCallback) {
    SetLoadFileDataCallback(callback)
}

/**
 * Set custom file binary data saver
 * @param callback takes in a pointer to a C Function.
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setSaveFileDataCallback(callback: SaveFileDataCallback) {
    SetSaveFileDataCallback(callback)
}

/**
 * Set custom file text data loader
 * @param callback takes in a pointer to a C Function.
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setLoadFileTextCallback(callback: LoadFileTextCallback) {
    SetLoadFileTextCallback(callback)
}

/**
 * Set custom file text data saver
 * @param callback takes in a pointer to a C Function.
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setSaveFileTextCallback(callback: SaveFileTextCallback) {
    SetSaveFileTextCallback(callback)
}

//=======================================================//
// FILE MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Load file data as byte array (read)
 * @return [UByteVar]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadFileData(fileName: String, bytesRead: UIntVar, allocator: AutofreeScope) : UByteVar? {
    return LoadFileData(fileName, bytesRead.ptr)?.getPointer(allocator)?.pointed
}

/**
 * Unload file [data] allocated by LoadFileData()
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadFileData(data: UByteVar) {
    UnloadFileData(data.ptr)
}

/**
 * Save [data] to file from byte array (write), returns true on success
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun saveFileData(fileName: String, data: COpaquePointer, bytesToWrite: UInt) : Boolean {
    return SaveFileData(fileName, data, bytesToWrite)
}

/**
 * Load text data from file (read), returns a '\0' terminated string
 * @return [ByteVar]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadFileText(fileName: String, allocator: AutofreeScope) : ByteVar? {
    return LoadFileText(fileName)?.getPointer(allocator)?.pointed
}

/**
 * Unload file [text] data allocated by LoadFileText()
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadFileText(text: ByteVar) {
    UnloadFileText(text.ptr)
}

/**
 * Save text data to file (write), string must be '\0' terminated, returns true on success
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun saveFileText(fileName: String, text: String) : Boolean {
    return SaveFileText(fileName, text.cstr)
}

/**
 * Check if file exists
 * @return [Boolean]
 */
inline fun fileExists(fileName: String) : Boolean {
    return FileExists(fileName)
}

/**
 * Check if a directory path exists
 * @return [Boolean]
 */
inline fun directoryExists(dirPath: String) : Boolean {
    return DirectoryExists(dirPath)
}

/**
 * Check file extension (including point: .png, .wav)
 * @return [Boolean]
 */
inline fun isFileExtension(fileName: String, ext: String) : Boolean {
    return IsFileExtension(fileName, ext)
}

/**
 * Get pointer to extension for a filename string (includes dot: `.png`)
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getFileExtension(fileName: String) : String {
    return GetFileExtension(fileName)?.toKString() ?: ""
}

/**
 * Get pointer to filename for a path string
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getFileName(filePath: String) : String {
    return GetFileName(filePath)?.toKString() ?: ""
}

/**
 * Get filename string without extension (uses inline string)
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getFileNameWithoutExt(filePath: String) : String {
    return GetFileNameWithoutExt(filePath)?.toKString() ?: ""
}

/**
 * Get full path for a given fileName with path (uses inline string)
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getDirectoryPath(filePath: String) : String {
    return GetDirectoryPath(filePath)?.toKString() ?: ""
}

/**
 * Get previous directory path for a given path (uses inline string)
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getPrevDirectoryPath(dirPath: String) : String {
    return GetPrevDirectoryPath(dirPath)?.toKString() ?: ""
}

/**
 * Get current working directory (uses inline string)
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getWorkingDirectory() : String {
    return GetWorkingDirectory()?.toKString() ?: ""
}

/**
 * Get filenames in a directory path (memory should be freed)
 * @return [FilePathList]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadDirectoryFiles(dirPath: String, allocator: AutofreeScope) : FilePathList {
    return LoadDirectoryFiles(dirPath).getPointer(allocator).pointed
}

/**
 * Clear directory [files] paths buffers (free memory)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadDirectoryFiles(files: FilePathList) {
    return UnloadDirectoryFiles(files.readValue())
}

/**
 * Change working directory, return true on success
 * @return [Boolean]
 */
inline fun changeDirectory(dir: String) : Boolean {
    return ChangeDirectory(dir)
}

/**
 * Check if a file has been dropped into window
 * @return [Boolean]
 */
inline val isFileDropped: Boolean
    get() { return IsFileDropped() }

/**
 * Get dropped files names (memory should be freed)
 * @return [FilePathList]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadDroppedFiles(allocator: AutofreeScope) : FilePathList {
    return LoadDroppedFiles().getPointer(allocator).pointed
}

/**
 * Clear dropped [files] paths buffer (free memory)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadDroppedFiles(files: FilePathList) {
    UnloadDroppedFiles(files.readValue())
}

/**
 * Get file modification time (last write time)
 * @return [Int]
 */
@OptIn(ExperimentalForeignApi::class, UnsafeNumber::class)
inline fun getFileModTime(fileName: String): Int {
    return GetFileModTime(fileName).convert()
}

//=======================================================//
// COMPRESSION/ENCODING FUNCTIONS
//=======================================================//

/**
 * Compress [data] (DEFLATE algorithm)
 * @return [UByteVar]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun compressData(data: UByteVar, dataLength: Int, compDataLength: IntVar, allocator: AutofreeScope) : UByteVar? {
    return CompressData(data.ptr, dataLength, compDataLength.ptr)?.getPointer(allocator)?.pointed
}

/**
 * Decompress [compData] (DEFLATE algorithm)
 * @return [UByteVar]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun decompressData(compData: UByteVar, compDataLength: Int, dataLength: IntVar, allocator: AutofreeScope) : UByteVar? {
    return DecompressData(compData.ptr, compDataLength, dataLength.ptr)?.getPointer(allocator)?.pointed
}

/**
 * Encode [data] to Base64 string
 * @return [ByteVar]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun encodeDataBase64(data: UByteVar, dataLength: Int, outputLength: IntVar, allocator: AutofreeScope) : ByteVar? {
    return EncodeDataBase64(data.ptr, dataLength, outputLength.ptr)?.getPointer(allocator)?.pointed
}

/**
 * Encode [data] to Base64 string
 * @return [UByteVar]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun decodeDataBase64(data: UByteVar, outputLength: IntVar, allocator: AutofreeScope) : UByteVar? {
    return DecodeDataBase64(data.ptr, outputLength.ptr)?.getPointer(allocator)?.pointed
}

//=======================================================//
// WEB RELATED FUNCTIONS
//=======================================================//

/**
 * Open [url] with default system browser (if available)
 */
inline fun openURL(url: String) {
    OpenURL(url)
}

//=======================================================//
// INPUT HANDLING FUNCTIONS - KEYBOARDS
//=======================================================//

/**
 * Check if a key has been pressed once
 * @return [Boolean]
 */ 
inline fun isKeyPressed(keyboardKey: kaylibkit.kEnums.KeyboardKey) : Boolean {
    return IsKeyPressed(keyboardKey.key)
}

/**
 * Check if a key is being pressed
 * @return [Boolean]
 */
inline fun isKeyDown(keyboardKey: kaylibkit.kEnums.KeyboardKey) : Boolean {
    return IsKeyDown(keyboardKey.key)
}

/**
 * Check if a key has been released once
 * @return [Boolean]
 */
inline fun isKeyReleased(keyboardKey: kaylibkit.kEnums.KeyboardKey) : Boolean {
    return IsKeyReleased(keyboardKey.key)
}

/**
 * Check if a key is NOT being pressed
 * @return [Boolean]
 */
inline fun isKeyUp(keyboardKey: kaylibkit.kEnums.KeyboardKey) : Boolean {
    return IsKeyUp(keyboardKey.key)
}

/**
 * Set a custom key to exit program (default is ESC)
 */
inline fun setExitKey(keyboardKey: kaylibkit.kEnums.KeyboardKey) {
        SetExitKey(keyboardKey.key)
}

/**
 * Get key pressed (keycode), call it multiple times for keys queued, returns 0 when the queue is empty
 * @return [Int]
 */
inline fun getKeyPressed(): Int {
    return GetKeyPressed()
}

/**
 * Get char pressed (unicode), call it multiple times for chars queued, returns 0 when the queue is empty
 * @return [Char]
 */
inline fun getCharPressed() : Char {
    return GetCharPressed().toChar()
}

//=======================================================//
// INPUT HANDLING FUNCTIONS - GAMEPADS
//=======================================================//

/**
 * Check if a [gamepad] is available
 * @return [Boolean]
 */
inline fun isGamepadAvailable(gamepad: Int) : Boolean {
    return IsGamepadAvailable(gamepad)
}

/**
 * Get [gamepad] internal name id
 * @return [String]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getGamepadName(gamepad: Int) : String {
    return GetGamepadName(gamepad)?.toKString() ?: "WARNING: GamePad not found!"
}

/**
 * Check if a [gamepad] [button] has been pressed once
 * @return [Boolean]
 */
inline fun isGamepadButtonPressed(gamepad: Int, button: kaylibkit.kEnums.GamepadButton) : Boolean {
    return IsGamepadButtonPressed(gamepad, button.value)
}

/**
 * Check if a [gamepad] [button] is being pressed
 * @return [Boolean]
 */
inline fun isGamepadButtonDown(gamepad: Int, button: kaylibkit.kEnums.GamepadButton) : Boolean {
    return IsGamepadButtonDown(gamepad, button.value)
}

/**
 * Check if a [gamepad] [button] has been released once
 * @return [Boolean]
 */
inline fun isGamepadButtonReleased(gamepad: Int, button: kaylibkit.kEnums.GamepadButton) : Boolean {
    return IsGamepadButtonReleased(gamepad, button.value)
}

/**
 * Check if a [gamepad] [button] is NOT being pressed
 * @return [Boolean]
 */
inline fun isGamepadButtonUp(gamepad: Int, button: kaylibkit.kEnums.GamepadButton) : Boolean {
    return IsGamepadButtonUp(gamepad, button.value)
}

/**
 * Get the last gamepad button pressed - Will always return
 * @return [Int]
 */
inline fun getGamepadButtonPressed() : Int {
    return GetGamepadButtonPressed()
}

/**
 * Get gamepad axis count for a [gamepad] axis
 * @return [Int]
 */
inline fun getGamepadAxisCount(gamepad: Int) : Int {
    return GetGamepadAxisCount(gamepad)
}

/**
 * Get axis movement value for a [gamepad] [axis]
 * @return [Float]
 */
inline fun getGamepadAxisMovement(gamepad: Int, axis: kaylibkit.kEnums.GamepadAxis) : Float {
    return GetGamepadAxisMovement(gamepad, axis.value)
}

/**
 * Set internal gamepad [mappings] (SDL_GameControllerDB)
 * @return [Int]
 */
inline fun setGamepadMappings(mappings: String) : Int {
    return SetGamepadMappings(mappings)
}

//=======================================================//
// INPUT HANDLING FUNCTIONS - MOUSE
//=======================================================//

/**
 * Check if a mouse [button] has been pressed once
 * @return [Boolean]
 */
inline fun isMouseButtonPressed(button: kaylibkit.kEnums.MouseButton) : Boolean {
    return IsMouseButtonPressed(button.value)
}

/**
 * Check if a mouse [button] is being pressed
 * @return [Boolean]
 */
inline fun isMouseButtonDown(button: kaylibkit.kEnums.MouseButton) : Boolean {
    return IsMouseButtonDown(button.value)
}

/**
 * Check if a mouse [button] has been released once
 * @return [Boolean]
 */
inline fun isMouseButtonReleased(button: kaylibkit.kEnums.MouseButton) : Boolean {
    return IsMouseButtonReleased(button.value)
}

/**
 * Check if a mouse [button] is NOT being pressed
 * @return [Boolean]
 */
inline fun isMouseButtonUp(button: kaylibkit.kEnums.MouseButton) : Boolean {
    return IsMouseButtonUp(button.value)
}

/**
 * Get mouse position X
 * @return [Int]
 */
inline fun getMouseX() : Int {
    return GetMouseX()
}

/**
 * Get mouse position Y
 * @return [Int]
 */
inline fun getMouseY() : Int {
    return GetMouseY()
}

/**
 * Get mouse position XY - This function uses getPointer with passed allocator as scope! You can also allocate your arena in the parameter
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getMousePosition(allocator: AutofreeScope) : Vector2 {
    return GetMousePosition().getPointer(allocator).pointed

}

/**
 * Set mouse position [x] [y]
 */
inline fun setMousePosition(x: Int, y : Int) {
    SetMousePosition(x, y)
}

/**
 * Set mouse offset
 */
inline fun setMouseOffset(offsetX: Int, offsetY: Int) {
    SetMouseOffset(offsetX, offsetY)
}

/**
 * Set mouse scaling
 */
inline fun setMouseScale(scaleX: Float, scaleY: Float) {
    SetMouseScale(scaleX, scaleY)
}

/**
 * Get mouse wheel movement Y
 * @return [Float]
 */
inline fun getMouseWheelMove() : Float {
    return GetMouseWheelMove()
}

/**
 * Set mouse [cursor]
 */
inline fun setMouseCursor(cursor: kaylibkit.kEnums.MouseCursor) {
    SetMouseCursor(cursor.value)
}

//=======================================================//
// INPUT HANDLING FUNCTIONS - TOUCH
//=======================================================//

/**
 * Get touch position X for touch point 0 (relative to screen size)
 * @return [Int]
 */
inline fun getTouchX() : Int {
    return GetTouchX()
}

/**
 * Get touch position Y for touch point 0 (relative to screen size)
 * @return [Int]
 */
inline fun getTouchY() : Int {
    return GetTouchY()
}

/**
 * Get touch position XY for a touch point [index] (relative to screen size)
 * @return [Vector2]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getTouchPosition(index: Int, allocator: AutofreeScope) : Vector2 {
    return GetTouchPosition(index).getPointer(allocator).pointed
}

/**
 * Get touch point identifier for given [index]
 * @return [Int]
 */
inline fun getTouchPointId(index: Int) : Int{
    return GetTouchPointId(index)
}

/**
 * Get number of touch points
 * @return [Int]
 */
inline fun getTouchPointCount() : Int {
    return GetTouchPointCount()
}
