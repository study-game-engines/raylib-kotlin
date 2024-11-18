import kaylibkit.kCore.*
import kaylibkit.kEnums.CameraProjection
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kMath.kVector3
import kaylibkit.kModels.drawCube
import kaylibkit.kModels.drawCubeWires
import kaylibkit.kModels.drawGrid
import kaylibkit.kText.drawFPS
import kaylibkit.kText.drawText
import kaylibkit.kTypes.kCamera3D
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
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - 3d camera mode")

        val camera = kCamera3D(
            kVector3(0F, 10F, 10F, allocator = this),
            kVector3(0F, 0F, 0F, allocator = this),
            kVector3(0F, 1F, 0F, allocator = this),
            45F,
            CameraProjection.PERSPECTIVE,
            allocator = this
        )
        val cubePosition = kVector3(0F, 0F, 0F, allocator = this)
        //--------------------------------------------------------------------------------------

        // Main game loop
        while (!windowShouldClose) { // Detect window close button or ESC key
            // Update
            //----------------------------------------------------------------------------------
            // TODO: Update your variables here
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

                drawText("Welcome to the third dimension!", 10, 40, 20, darkGray)
                drawFPS(10, 10)
                //----------------------------------------------------------------------------------
            }
        }

        // De-Initialization
        //--------------------------------------------------------------------------------------
        closeWindow() // Close window and OpenGL context
        //--------------------------------------------------------------------------------------
    }
}