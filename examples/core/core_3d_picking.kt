import kaylibkit.kCamera.updateCamera
import kaylibkit.kCore.*
import kaylibkit.kEnums.CameraMode
import kaylibkit.kEnums.CameraProjection
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kEnums.MouseButton
import kaylibkit.kMath.kVector3
import kaylibkit.kModels.*
import kaylibkit.kText.drawFPS
import kaylibkit.kText.drawText
import kaylibkit.kText.measureText
import kaylibkit.kTypes.kBoundingBox
import kaylibkit.kTypes.kCamera3D
import kaylibkit.kTypes.kRay
import kaylibkit.kTypes.kRayCollision
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
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - 3d picking")

        val camera = kCamera3D(position = kVector3(10F, 10F, 10F, allocator = this), target = kVector3(0F, 0F, 0F, allocator = this), up = kVector3(0F, 1F, 0F, allocator = this), fovy = 45F, projection = CameraProjection.PERSPECTIVE, allocator = this)

        val cubePosition = kVector3(0F, 1F, 0F, allocator = this)
        val cubeSize = kVector3(2F, 2F, 2F, allocator = this)

        var ray = kRay(allocator = this, position = kVector3(allocator = this), direction = kVector3(allocator = this)) // Picking line ray
        var collision = kRayCollision(allocator = this, point = kVector3(allocator = this), normal = kVector3(allocator = this))

        //--------------------------------------------------------------------------------------

        // Main game loop
        while (!windowShouldClose) { // Detect window close button or ESC key
            // Update
            //----------------------------------------------------------------------------------
            if (isCursorHidden()) {
                updateCamera(camera, CameraMode.FIRST_PERSON)
            }

            if (isMouseButtonPressed(MouseButton.LEFT)) {
                if (!collision.hit) {
                    ray = getMouseRay(getMousePosition(allocator = this), camera = camera, allocator = this)

                    // Check collision between ray and box
                    collision = getRayCollisionBox(
                        ray,
                        kBoundingBox(
                            kVector3(
                                cubePosition.x - cubeSize.x / 2,
                                cubePosition.y - cubeSize.y / 2,
                                cubePosition.z - cubeSize.z / 2,
                                allocator = this
                            ),
                            kVector3(
                                cubePosition.x + cubeSize.x / 2,
                                cubePosition.y + cubeSize.y / 2,
                                cubePosition.z + cubeSize.z / 2,
                                allocator = this
                            ),
                            allocator = this
                        ), allocator = this
                    )
                } else {
                    collision.hit = false
                }
            }
            //----------------------------------------------------------------------------------

            drawing {
                // Draw
                //----------------------------------------------------------------------------------
                clearBackground(rayWhite)

                mode3D(camera) {
                    if (collision.hit) {
                        drawCube(cubePosition, cubeSize.x, cubeSize.y, cubeSize.z, red)
                        drawCubeWires(cubePosition, cubeSize.x, cubeSize.y, cubeSize.z, maroon)

                        drawCubeWires(cubePosition, cubeSize.x + 0.2f, cubeSize.y + 0.2f, cubeSize.z + 0.2f, green)
                    } else {
                        drawCube(cubePosition, cubeSize.x, cubeSize.y, cubeSize.z, gray)
                        drawCubeWires(cubePosition, cubeSize.x, cubeSize.y, cubeSize.z, darkGray)
                    }

                    drawRay(ray, maroon)
                    drawGrid(10, 1F)
                }

                drawText("Try selecting the box with mouse!", 240, 10, 20, darkGray)

                if (collision.hit) drawText(
                    "BOX SELECTED",
                    (SCREEN_WIDTH - measureText("BOX SELECTED", 30)) / 2,
                    (SCREEN_HEIGHT * .1F).toInt(),
                    30,
                    green
                )

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