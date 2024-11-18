import kaylibkit.kCore.*
import kaylibkit.kEnums.MouseButton
import kaylibkit.kMath.kVector2
import kaylibkit.kShapes.drawRectangle
import kaylibkit.kText.drawFPS
import kaylibkit.kText.drawText
import kaylibkit.kTextures.drawTexture
import kaylibkit.kTextures.loadTexture
import kaylibkit.kTextures.unloadTexture
import kaylibkit.kTypes.kColor
import kaylibc.*
import kotlinx.cinterop.memScoped
import kotlin.random.Random

const val SCREEN_WIDTH: Int = 800
const val SCREEN_HEIGHT: Int = 450

data class Bunny(var position: Vector2, var speed: Vector2, var color: Color)

var MAX_BUNNIES = 500000
var MAX_BATCH_ELEMENTS = 8192

fun main() {
    memScoped {     // Initialization
        //--------------------------------------------------------------------------------------
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [textures] example - bunnymark")
        setTargetFPS(60)

        // Load bunny texture
        val texBunny = loadTexture("$resourcePath/wabbit_alpha.png", allocator = this)
        val bunnies = Array(MAX_BUNNIES) { Bunny(kVector2(allocator = this), kVector2(allocator = this), kColor(allocator = this)) }
        var bunniesCount = 0 // Bunnies counter
        //--------------------------------------------------------------------------------------

        // Main game loop
        while (!windowShouldClose) { // Detect window close button or ESC key
            // Update
            //----------------------------------------------------------------------------------
            if (isMouseButtonDown(MouseButton.LEFT)) {
                // Create more bunnies
                repeat(100) {
                    if (bunniesCount < MAX_BUNNIES) {
                        bunnies[bunniesCount].position = getMousePosition(allocator = this)
                        bunnies[bunniesCount].speed.x = (Random.nextInt(-250, 250) / 60.0f)
                        bunnies[bunniesCount].speed.y = (Random.nextInt(-250, 250) / 60.0f)
                        bunnies[bunniesCount].color.apply {
                            this.r = getRandomValue(50, 240).toUByte()
                            this.g = getRandomValue(80, 240).toUByte()
                            this.b = getRandomValue(100, 200).toUByte()
                            this.a = 255U
                        }
                        bunniesCount++
                    }
                }
            }

            // Update bunnies
            for (i in 0..<bunniesCount) {
                bunnies[i].position.x += bunnies[i].speed.x
                bunnies[i].position.y += bunnies[i].speed.y
                if (bunnies[i].position.x + texBunny.width / 2.0 > SCREEN_WIDTH ||
                    bunnies[i].position.x + texBunny.width / 2.0 < 0
                ) {
                    bunnies[i].speed.x *= -1f
                }
                if (bunnies[i].position.y + texBunny.height / 2.0 > SCREEN_HEIGHT ||
                    bunnies[i].position.y + texBunny.height / 2.0 - 40 < 0
                ) {
                    bunnies[i].speed.y *= -1f
                }
            }
            //----------------------------------------------------------------------------------

            drawing {
                // Draw
                //----------------------------------------------------------------------------------
                clearBackground(rayWhite)
                for (i in 0..<bunniesCount) {
                    // NOTE: When internal batch buffer limit is reached (MAX_BATCH_ELEMENTS),
                    // a draw call is launched and buffer starts being filled again;
                    // before issuing a draw call, updated vertex data from internal CPU buffer is send to GPU...
                    // Process of sending data is costly, and it could happen that GPU data has not been completely
                    // processed for drawing while new data is tried to be sent (updating current in-use buffers)
                    // it could generate a stall and consequently a frame drop, limiting the number of drawn bunnies
                    drawTexture(
                        texBunny,
                        bunnies[i].position.x.toInt(),
                        bunnies[i].position.y.toInt(),
                        bunnies[i].color
                    )
                }
                drawRectangle(0, 0, SCREEN_WIDTH, 40, black)
                drawText("bunnies: $bunniesCount", 120, 10, 20, green)
                drawText("batched draw calls: " + bunniesCount / MAX_BATCH_ELEMENTS, 320, 10, 20, maroon)
                drawFPS(10, 10)
                //----------------------------------------------------------------------------------
            }
        }

        // De-Initialization
        //--------------------------------------------------------------------------------------
        unloadTexture(texBunny) // Unload bunny texture
        closeWindow() // Close window and OpenGL context
        //-------------------------------------------------------------------------------------- }
    }
}