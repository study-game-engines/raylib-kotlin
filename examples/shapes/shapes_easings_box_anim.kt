import kaylibkit.kCore.*
import kaylibkit.kEasing.*
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kEnums.KeyboardKey
import kaylibkit.kMath.kVector2
import kaylibkit.kShapes.drawRectangle
import kaylibkit.kShapes.kRectangle
import kaylibkit.kText.drawText
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
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [shapes] example - easings box anim")

        var rec: Rectangle = kRectangle(allocator = this, kVector2(getScreenWidth() / 2F, -100F, allocator = this), 100F, 100F)
        var rotation: Float = .0F
        var alpha: Float = 1.0F

        var state: Int = 0
        var framesCounter: Int = 0
        //--------------------------------------------------------------------------------------
        // Main game loop
        while (!windowShouldClose) { // Detect window close button or ESC key

            // Update
            //----------------------------------------------------------------------------------
            when (state) {
                0 -> {
                    framesCounter++

                    // NOTE: Remember that 3rd parameter of easing function refers to
                    // desired value variation, do not confuse it with expected final value!
                    rec.y = elasticOut(framesCounter.toFloat(), -100F, getScreenHeight() / 2.0F + 100, 120F)
                    if (framesCounter >= 120) {
                        framesCounter = 0
                        state = 1
                    }
                }

                1 -> {
                    framesCounter++
                    rec.height = bounceOut(framesCounter.toFloat(), 100F, -90F, 120F)
                    rec.width = bounceOut(framesCounter.toFloat(), 100F, getScreenWidth().toFloat(), 120F)
                    if (framesCounter >= 120) {
                        framesCounter = 0
                        state = 2
                    }
                }

                2 -> {
                    framesCounter++
                    rotation = quadOut(framesCounter.toFloat(), .0F, 270.0F, 240F)
                    if (framesCounter >= 240) {
                        framesCounter = 0
                        state = 3
                    }
                }

                3 -> {
                    framesCounter++
                    rec.height = circOut(framesCounter.toFloat(), 10F, GetScreenWidth().toFloat(), 120F)
                    if (framesCounter >= 120) {
                        framesCounter = 0
                        state = 4
                    }
                }

                4 -> {
                    framesCounter++
                    alpha = sineOut(framesCounter.toFloat(), 1.0F, -1.0F, 160F)
                    if (framesCounter >= 160) {
                        framesCounter = 0
                        state = 5
                    }
                }

                else -> {}
            }

            if (isKeyPressed(KeyboardKey.SPACE)) {
                rec = kRectangle(allocator = this, kVector2(getScreenWidth() / 2F, -100F, allocator = this), 100F, 100F)
                rotation = .0F
                alpha = 1.0F
                state = 0
                framesCounter = 0
            }
            //----------------------------------------------------------------------------------

            drawing {
                // Draw
                //----------------------------------------------------------------------------------
                clearBackground(rayWhite)

                drawRectangle(rec, kVector2(rec.width / 2, rec.height / 2, allocator = this), rotation, fade(black, alpha, allocator = this))

                drawText("PRESS [SPACE] TO RESET BOX ANIMATION!", 10, getScreenHeight() - 25, 20, lightGray)
                //----------------------------------------------------------------------------------
            }
        }
        // De-Initialization
        //--------------------------------------------------------------------------------------

        closeWindow() // Close window and OpenGL context
        //--------------------------------------------------------------------------------------
    }
}
