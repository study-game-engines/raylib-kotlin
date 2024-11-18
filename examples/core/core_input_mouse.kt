import kaylibkit.kCore.*
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kEnums.MouseButton
import kaylibkit.kShapes.drawCircle
import kaylibkit.kText.drawText
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
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - mouse input")

        var ballPosition: Vector2
        var ballColor = darkBlue
        //--------------------------------------------------------------------------------------

        // Main game loop
        while (!windowShouldClose) { // Detect window close button or ESC key
            // Update
            //----------------------------------------------------------------------------------
            ballPosition = getMousePosition(allocator = this)

            if (isMouseButtonPressed(MouseButton.LEFT)) ballColor = maroon
            else if (isMouseButtonPressed(MouseButton.MIDDLE)) ballColor = lime
            else if (isMouseButtonPressed(MouseButton.RIGHT)) ballColor = darkBlue
            else if (isMouseButtonPressed(MouseButton.SIDE)) ballColor = purple
            else if (isMouseButtonPressed(MouseButton.EXTRA)) ballColor = yellow
            else if (isMouseButtonPressed(MouseButton.FORWARD)) ballColor = orange
            else if (isMouseButtonPressed(MouseButton.BACK)) ballColor = beige
            //----------------------------------------------------------------------------------

            drawing {
                // Draw
                //----------------------------------------------------------------------------------
                clearBackground(rayWhite)

                drawText("move ball with mouse and click mouse button to change color", 10, 10, 20, darkGray)

                drawCircle(ballPosition, 50F, ballColor)
                //----------------------------------------------------------------------------------
            }
        }
        // De-Initialization
        //--------------------------------------------------------------------------------------
        closeWindow() // Close window and OpenGL context
        //-------------------------------------------------------------------------------------- }
    }
}

