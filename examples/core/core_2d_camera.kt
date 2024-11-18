import kaylibkit.kCore.*
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kEnums.KeyboardKey
import kaylibkit.kMath.kVector2
import kaylibkit.kMath.set
import kaylibkit.kShapes.drawLine
import kaylibkit.kShapes.drawRectangle
import kaylibkit.kShapes.drawRectangleLines
import kaylibkit.kShapes.kRectangle
import kaylibkit.kText.drawText
import kaylibkit.kTypes.kColor
import kaylibkit.kUtils.fade
import kaylibc.*
import kaylibkit.kTypes.kCamera2D
import kotlinx.cinterop.memScoped
import kotlin.random.Random
import kotlin.random.nextInt

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 450
const val MAX_BUILDINGS = 100

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    memScoped {
        setConfigFlags(ConfigFlag.VSYNC_HINT) // Turn VSYNC On
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - 2d camera")

        val player = kRectangle(400F, 280F, 40F, 40F, allocator = this)
        val buildings = Array(MAX_BUILDINGS) { kRectangle(allocator = this) }
        val buildColor = Array(MAX_BUILDINGS) {
            kColor(
                Random.nextInt(200..240).toUByte(),
                Random.nextInt(200..240).toUByte(),
                Random.nextInt(200..250).toUByte(),
                255U,
                allocator = this)
        }
        var spacing = 0

        buildings.forEach { building ->
            building.width = Random.nextInt(50..200).toFloat()
            building.height = Random.nextInt(100..800).toFloat()
            building.y = SCREEN_HEIGHT - 130F - building.height
            building.x = -6000F + spacing

            spacing += building.width.toInt()
        }

        val camera = kCamera2D(kVector2(SCREEN_WIDTH / 2F, SCREEN_HEIGHT / 2F, allocator = this), kVector2(player.x + 20F, player.y + 20F, allocator = this), 0F, 1F, allocator = this)
        //--------------------------------------------------------------------------------------

        // Main game loop
        while (!windowShouldClose) { // Detect window close button or ESC key
            // Update
            //----------------------------------------------------------------------------------
            // Player movement
            if (isKeyDown(KeyboardKey.RIGHT)) {
                player.x += 2
            } else if (isKeyDown(KeyboardKey.LEFT)) {
                player.x -= 2
            }

            // Camera target follows player
            // We're unable to modify .target directly due to how cinterop works with K/N as cinterop will translate any nested CStruct as a immutable (val)
            // if they're not pointing to anything (pointer to), but we can easily modify it by passing immutable CStruct value:
            camera.target.set(kVector2(player.x + 20F, player.y + 20F, this))

            // We can also do it a different way by simply modifying the CStruct members

            // Camera rotation controls
            if (isKeyDown(KeyboardKey.A)) {
                camera.rotation--
            } else if (isKeyDown(KeyboardKey.S)) {
                camera.rotation++
            }

            // Limit camera rotation to 80 degrees (-40 to 40)
            if (camera.rotation > 40) {
                camera.rotation = 40F
            } else if (camera.rotation < -40) {
                camera.rotation = -40F
            }

            // Camera zoom controls
            camera.zoom += getMouseWheelMove() * .05F

            if (camera.zoom > 3F) {
                camera.zoom = 3F
            } else if (camera.zoom < .1F) {
                camera.zoom = .1F
            }

            // Camera reset (zoom and rotation)
            if (isKeyPressed(KeyboardKey.R)) {
                camera.zoom = 1F
                camera.rotation = 0F
            }
            //----------------------------------------------------------------------------------

            drawing {
                // Draw
                //----------------------------------------------------------------------------------
                mode2D(camera) {
                    clearBackground(rayWhite)

                    drawRectangle(-6000, 320, 13000, 8000, darkGray)

                    buildings.forEachIndexed { index, rect -> drawRectangle(rect, buildColor[index]) }

                    drawRectangle(player, red)

                    drawLine(
                        camera.target.x.toInt(),
                        -SCREEN_HEIGHT * 10,
                        camera.target.x.toInt(),
                        SCREEN_HEIGHT * 10,
                        green
                    )
                    drawLine(
                        -SCREEN_WIDTH * 10,
                        camera.target.y.toInt(),
                        SCREEN_WIDTH * 10,
                        camera.target.y.toInt(),
                        green
                    )

                }

                drawText("SCREEN AREA", 640, 10, 20, red)
                drawRectangle(0, 0, SCREEN_WIDTH, 5, red)
                drawRectangle(0, 5, 5, SCREEN_HEIGHT - 10, red)
                drawRectangle(SCREEN_WIDTH - 5, 5, 5, SCREEN_HEIGHT - 10, red)
                drawRectangle(0, SCREEN_HEIGHT - 5, SCREEN_WIDTH, 5, red)

                drawRectangle(10, 10, 250, 113, fade(skyBlue, 0.5F, allocator = this))
                drawRectangleLines(10, 10, 250, 113, blue)

                drawText("Free 2D camera controls:", 20, 20, 10, black)
                drawText("- Right/Left to move Offset", 40, 40, 10, darkGreen)
                drawText("- Mouse Wheel to Zoom in-out", 40, 60, 10, darkGreen)
                drawText("- A / S to Rotate", 40, 80, 10, darkGreen)
                drawText("- R to reset Zoom and Rotation", 40, 100, 10, darkGreen)
                //----------------------------------------------------------------------------------
            }
        }
        // De-Initialization
        //--------------------------------------------------------------------------------------
        closeWindow() // Close window and OpenGL context
        //--------------------------------------------------------------------------------------
    }

}