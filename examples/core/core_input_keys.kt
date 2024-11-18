import kaylibkit.kCore.*
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kEnums.KeyboardKey
import kaylibkit.kMath.kVector2
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
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - keyboard input")

        val ballPosition = kVector2(SCREEN_WIDTH / 2F, SCREEN_HEIGHT / 2F, allocator = this)
        //--------------------------------------------------------------------------------------

        // Main game loop
        while (!windowShouldClose) { // Detect window close button or ESC key
            // Update
            //----------------------------------------------------------------------------------
            if (isKeyDown(KeyboardKey.RIGHT)) {
                ballPosition.x += 2
            }
            if (isKeyDown(KeyboardKey.LEFT)) {
                ballPosition.x -= 2
            }
            if (isKeyDown(KeyboardKey.UP)) {
                ballPosition.y -= 2
            }
            if (isKeyDown(KeyboardKey.DOWN)) {
                ballPosition.y += 2
            }
            //----------------------------------------------------------------------------------

            drawing {
                // Draw
                //----------------------------------------------------------------------------------
                clearBackground(rayWhite)

                drawText("move the ball with arrow keys", 10, 10, 20, darkGray)

                drawCircle(ballPosition, 50F, maroon)
                //----------------------------------------------------------------------------------
            }

        }
        // De-Initialization
        //--------------------------------------------------------------------------------------
        closeWindow() // Close window and OpenGL context
        //--------------------------------------------------------------------------------------
    }

}

