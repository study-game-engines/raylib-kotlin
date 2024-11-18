import kaylibkit.kCore.*
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kShapes.drawRectangle
import kaylibkit.kText.drawText
import kaylibc.*

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 450

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    setConfigFlags(ConfigFlag.VSYNC_HINT) // Turn VSYNC On
    initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - mouse wheel input")

    var boxPositionY = SCREEN_HEIGHT/2 - 40
    val scrollSpeed = 4
    //--------------------------------------------------------------------------------------
    // Main game loop
    while (!windowShouldClose) { // Detect window close button or ESC key

        // Update
        //----------------------------------------------------------------------------------
        boxPositionY -= (getMouseWheelMove() * scrollSpeed).toInt()
        //----------------------------------------------------------------------------------

        drawing {
            // Draw
            //----------------------------------------------------------------------------------
            clearBackground(rayWhite)

            drawRectangle(SCREEN_WIDTH/2 - 40, boxPositionY, 80, 80, maroon)

            drawText("Use mouse wheel to move the cube up and down!", 10, 10, 20, gray)
            drawText("Box Position Y: $boxPositionY", 10, 40, 20, lightGray)
            //----------------------------------------------------------------------------------
        }
    }
    // De-Initialization
    //--------------------------------------------------------------------------------------
    closeWindow() // Close window and OpenGL context
    //--------------------------------------------------------------------------------------
}

