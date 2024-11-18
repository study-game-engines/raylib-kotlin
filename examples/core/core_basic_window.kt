import kaylibkit.kCore.*
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kText.drawText
import kaylibc.*

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 450

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    setConfigFlags(ConfigFlag.VSYNC_HINT) // Turn VSYNC On
    initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - basic window")

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

            drawText("Hello from Kotlin/Native", 190, 200, 20, lightGray)
            //----------------------------------------------------------------------------------
        }

    }
    // De-Initialization
    //--------------------------------------------------------------------------------------
    closeWindow() // Close window and OpenGL context
    //--------------------------------------------------------------------------------------
}