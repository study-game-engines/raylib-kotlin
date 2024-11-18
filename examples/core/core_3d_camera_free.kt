import kaylibkit.kCamera.updateCamera
import kaylibkit.kCore.*
import kaylibkit.kEnums.CameraMode
import kaylibkit.kEnums.CameraProjection
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kEnums.KeyboardKey
import kaylibkit.kMath.kVector3
import kaylibkit.kMath.setZero
import kaylibkit.kModels.drawCube
import kaylibkit.kModels.drawCubeWires
import kaylibkit.kModels.drawGrid
import kaylibkit.kShapes.drawRectangle
import kaylibkit.kShapes.drawRectangleLines
import kaylibkit.kText.drawText
import kaylibkit.kTypes.kCamera3D
import kaylibkit.kUtils.fade
import kaylibc.*
import kotlinx.cinterop.memScoped

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 450

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    memScoped {
        setConfigFlags(ConfigFlag.VSYNC_HINT) // Turn VSYNC On
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - 3d camera free")

        val camera = kCamera3D(
            kVector3(10F, 10F, 10F, allocator = this),
            kVector3(allocator = this),
            kVector3(0F, 1F, 0F, allocator = this),
            45F,
            CameraProjection.PERSPECTIVE,
            allocator = this
        )
        val cubePosition = kVector3(allocator = this)

        disableCursor()

        //--------------------------------------------------------------------------------------

        // Main game loop
        while (!windowShouldClose) { // Detect window close button or ESC key
            // Update
            //----------------------------------------------------------------------------------
            updateCamera(camera, CameraMode.FREE)
            if (isKeyDown(KeyboardKey.Z)) {
                camera.target.setZero()
            }
            //----------------------------------------------------------------------------------

            drawing {
                // Draw
                //----------------------------------------------------------------------------------
                clearBackground(rayWhite)

                mode3D(camera) {
                    drawCube(cubePosition, 2F, 2F, 2F, red)
                    drawCubeWires(cubePosition, 2F, 2F, 2F, maroon)

                    drawGrid(10, 1F)
                }

                drawRectangle(10, 10, 320, 133, fade(skyBlue, .5F, allocator = this))
                drawRectangleLines(10, 10, 320, 133, blue)

                drawText("Free camera default controls:", 20, 20, 10, black)
                drawText("- W & S Key to Zoom in and Zoom out", 40, 40, 10, darkGray)
                drawText("- A & S to move around", 40, 60, 10, darkGray)
                drawText("- Q & E to rotate", 40, 80, 10, darkGray)
                drawText("- Z to zoom to (0, 0, 0)", 40, 120, 10, darkGray)
                //----------------------------------------------------------------------------------
            }
        }
        // De-Initialization
        //--------------------------------------------------------------------------------------
        closeWindow() // Close window and OpenGL context
        //-------------------------------------------------------------------------------------- }
    }
}