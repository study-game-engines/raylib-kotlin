import kaylibkit.kCamera.updateCamera
import kaylibkit.kCore.*
import kaylibkit.kEnums.CameraProjection
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kMath.kVector2
import kaylibkit.kMath.kVector3
import kaylibkit.kModels.drawCube
import kaylibkit.kModels.drawCubeWires
import kaylibkit.kModels.drawPlane
import kaylibkit.kShapes.drawRectangle
import kaylibkit.kShapes.drawRectangleLines
import kaylibkit.kText.drawText
import kaylibkit.kEnums.CameraProjection.*
import kaylibkit.kTypes.kCamera3D
import kaylibkit.kTypes.kColor
import kaylibkit.kUtils.fade
import kaylibc.*
import kaylibkit.kEnums.CameraMode.*
import kaylibkit.kCamera.cameraPitch
import kaylibkit.kCamera.cameraYaw
import kaylibkit.kEnums.KeyboardKey
import kotlinx.cinterop.memScoped
import kotlin.random.Random
import kotlin.random.nextInt

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 450
const val MAX_COLUMNS = 20

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    memScoped {
        setConfigFlags(ConfigFlag.VSYNC_HINT) // Turn VSYNC On
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - 3d camera first person")

        val camera = kCamera3D(
            kVector3(4F, 2F, 4F, allocator = this),
            kVector3(0F, 1.8F, 0F, allocator = this),
            kVector3(0F, 1F, 0F, allocator = this),
            60F,
            CameraProjection.PERSPECTIVE,
            allocator = this
        )

        var cameraMode = FIRST_PERSON

        val heights = Array(MAX_COLUMNS) { Random.nextInt(1..12).toFloat() }
        val position = Array(MAX_COLUMNS) { i ->
            kVector3(
                Random.nextInt(-15..15).toFloat(),
                heights[i] / 2F,
                Random.nextInt(-15..15).toFloat(),
                allocator = this
            )
        }
        val colors = Array(MAX_COLUMNS) {
            kColor(
                Random.nextInt(20..255).toUByte(),
                Random.nextInt(10..55).toUByte(),
                30U,
                255U,
                allocator = this
            )
        }

        disableCursor()

        //--------------------------------------------------------------------------------------

        // Main game loop
        while (!windowShouldClose) { // Detect window close button or ESC key

            // Update
            //----------------------------------------------------------------------------------
            // Switch camera mode
            if (isKeyPressed(KeyboardKey.NUMBER1)) {
                cameraMode = FREE
                camera.up.apply {
                    this.x = 0F
                    this.y = 1F
                    this.z = 0F
                }
            }

            if (isKeyPressed(KeyboardKey.NUMBER2)) {
                cameraMode = FIRST_PERSON
                camera.up.apply {
                    this.x = 0F
                    this.y = 1F
                    this.z = 0F
                }
            }

            if (isKeyPressed(KeyboardKey.NUMBER3)) {
                cameraMode = THIRD_PERSON
                camera.up.apply {
                    this.x = 0F
                    this.y = 1F
                    this.z = 0F
                }
            }

            if (isKeyPressed(KeyboardKey.NUMBER4)) {
                cameraMode = ORBITAL
                camera.up.apply {
                    this.x = 0F
                    this.y = 1F
                    this.z = 0F
                }
            }

            // Switch camera projection
            if (isKeyPressed(KeyboardKey.P)) {
                // Create isometric view
                cameraMode = THIRD_PERSON
                // Note: The target distance is related to the render distance in the orthographic projection
                camera.position.apply {
                    this.x = 0F
                    this.y = 2F
                    this.z = -100F
                }

                camera.target.apply {
                    this.x = 0F
                    this.y = 2F
                    this.z = 0F
                }

                camera.up.apply {
                    this.x = 0F
                    this.y = 1F
                    this.z = 0F
                }

                camera.projection = ORTHOGRAPHIC.value
                camera.fovy = 20F // near plane width in CAMERA_ORTHOGRAPHIC
                cameraYaw(camera, -135 * DEG2RAD, true)
                cameraPitch(camera, -45 * DEG2RAD, true, true, false)
            } else if (camera.projection == ORTHOGRAPHIC.value) {
                cameraMode = THIRD_PERSON
                camera.position.apply {
                    this.x = 0F
                    this.y = 2F
                    this.z = 10F
                }
                camera.target.apply {
                    this.x = 0F
                    this.y = 2F
                    this.z = 0F
                }
                camera.up.apply {
                    this.x = 0F
                    this.y = 1F
                    this.z = 0F
                }
                camera.projection = PERSPECTIVE.value
                camera.fovy = 60F
            }
            // Update camera computes movement internally depending on the camera mode
            // Some default standard keyboard/mouse inputs are hardcoded to simplify use
            // For advance camera controls, it's reecommended to compute camera movement manually
            updateCamera(camera, cameraMode) // Update camera

            drawing {
                // Draw
                //----------------------------------------------------------------------------------
                clearBackground(rayWhite)

                mode3D(camera) {
                    drawPlane(kVector3(0F, 0F, 0F, allocator = this), kVector2(32F, 32F, allocator = this), lightGray) // Draw ground
                    drawCube(kVector3(-16F, 2.5F, 0F, allocator = this), 1F, 5F, 32F, blue) // Draw a blue wall
                    drawCube(kVector3(16F, 2.5F, 0F, allocator = this), 1F, 5F, 32F, blue) // Draw a green wall
                    drawCube(kVector3(0F, 2.5F, 16F, allocator = this), 32F, 5F, 1F, blue) // Draw a yellow wall

                    // Draw some cubes around
                    heights.indices.forEach { i ->
                        drawCube(position[i], 2F, heights[i], 2F, colors[i])
                        drawCubeWires(position[i], 2F, heights[i], 2F, maroon)
                    }

                }

                drawRectangle(10, 10, 220, 70, fade(skyBlue, .5F, allocator = this))
                drawRectangleLines(10, 10, 220, 70, blue)
                drawText("First person camera default controls:", 20, 20, 10, black);
                drawText("- Move with keys: W, A, S, D", 40, 40, 10, darkGray);
                drawText("- Mouse move to look around", 40, 60, 10, darkGray);
                //----------------------------------------------------------------------------------
            }
        }
        // De-Initialization
        //--------------------------------------------------------------------------------------
        closeWindow() // Close window and OpenGL context
        //-------------------------------------------------------------------------------------- }
    }
}