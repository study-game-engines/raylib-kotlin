import kaylibkit.kCore.*
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kText.drawText
import kaylibkit.kTextures.drawTexture
import kaylibkit.kTextures.loadTexture
import kaylibkit.kTextures.unloadTexture
import kaylibc.*
import kotlinx.cinterop.memScoped

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 450

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    memScoped {
        setConfigFlags(ConfigFlag.VSYNC_HINT)  // Turn VSYNC On
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [textures] example - texture loading and drawing")
        //--------------------------------------------------------------------------------------

        // NOTE: Textures MUST be loaded after Window initialization (OpenGL context is required)
        val texture = loadTexture("$resourcePath/raylib_logo.png", allocator = this) // Texture loading

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

                drawTexture(
                    texture,
                    SCREEN_WIDTH / 2 - texture.width / 2,
                    SCREEN_HEIGHT / 2 - texture.height / 2,
                    white
                )

                drawText("this IS a texture!", 360, 370, 10, gray)
                //----------------------------------------------------------------------------------
            }
        }
        // De-Initialization
        //--------------------------------------------------------------------------------------
        unloadTexture(texture) // Texture unloading
        closeWindow() // Close window and OpenGL context
        //-------------------------------------------------------------------------------------- }
    }
}