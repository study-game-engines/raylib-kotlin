import kaylibkit.kCamera.updateCamera
import kaylibkit.kCore.*
import kaylibkit.kCore.closeWindow
import kaylibkit.kEnums.CameraMode
import kaylibkit.kEnums.CameraProjection
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kImage.loadImage
import kaylibkit.kImage.unloadImage
import kaylibkit.kMath.kVector3
import kaylibkit.kModels.*
import kaylibkit.kShapes.drawRectangleLines
import kaylibkit.kText.drawFPS
import kaylibkit.kTextures.*
import kaylibkit.kTypes.kCamera3D
import kaylibc.*
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 450

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    memScoped {
        setConfigFlags(ConfigFlag.VSYNC_HINT) // Turn VSYNC On
        initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [models] example - heightmap loading and drawing")
        //--------------------------------------------------------------------------------------

        val camera = kCamera3D(
            kVector3(18F, 18F, 18F, allocator = this),
            kVector3(0F, 0F, 0F, allocator = this),
            kVector3(0F, 1F, 0F, allocator = this),
            45F,
            CameraProjection.PERSPECTIVE,
            allocator = this
        )


        val image = loadImage("$resourcePath/heightmap.png", allocator = this) // Load heightmap image (RAM)
        val texture = loadTextureFromImage(image, allocator = this) // Convert image to texture (VRAM)

        val mesh = genMeshHeightmap(image, kVector3(16F, 8F, 16F, allocator = this), allocator = this) // Generate heightmap mesh (RAM and VRAM)
        val model = loadModelFromMesh(mesh, allocator = this) // Load model from generated mesh

        // Set map diffuse texture. NOTE that we have to use .get and check for nullability as its possible materials is null
        model.materials?.get(0)?.maps?.get(MATERIAL_MAP_DIFFUSE)?.texture?.set(texture) // Also note that we have to use K/N function of .get to retrieve the data from the material
        val mapPosition = kVector3(-8F, 0F, -8F, allocator = this) // Define model position

        unloadImage(image) // Unload heightmap image from RAM, already uploaded to VRAM

        // Main game loop
        while (!windowShouldClose) { // Detect window close button or ESC key
            // Update
            //----------------------------------------------------------------------------------
            updateCamera(camera, CameraMode.ORBITAL)
            //----------------------------------------------------------------------------------

            drawing {
                // Draw
                //----------------------------------------------------------------------------------
                clearBackground(rayWhite)

                mode3D(camera) {
                    drawModel(model, mapPosition, 1F, red)
                    drawGrid(20, 1F)
                }

                drawTexture(texture, SCREEN_WIDTH - texture.width - 20, 20, white)
                drawRectangleLines(SCREEN_WIDTH - texture.width - 20, 20, texture.width, texture.height, green)

                drawFPS(10, 10)
                //----------------------------------------------------------------------------------
            }
        }
        // De-Initialization
        //--------------------------------------------------------------------------------------
        unloadTexture(texture) // Texture unloading
        unloadModel(model) // Unload model

        closeWindow() // Close window and OpenGL context
        //--------------------------------------------------------------------------------------
    }
}

