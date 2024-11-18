<div align="center">
<p>

# KaylibKit - Raylib on Kotlin/Native

</p>

 [Documentation](https://Soutaisei.codeberg.page/kaylib/@pages/) • [Installation](./INSTALL.md) • [Development](./DEVELOPMENT.md) • [Contributing](./CONTRIBUTING.md) • [License](./LICENSE) 

</div>

**KaylibKit** is a [Kotlin/Native](https://kotlinlang.org/docs/native-overview.html) binding for [Raylib 4.5](http://www.raylib.com/) a simple and easy-to-use library to learn videogames programming. 
_A successor to Kaylib (Now more user friendly!)_

Its purpose is to hide away the pain and horrid syntax of K/N cinterop and joy in using Kotlin with an amazing game development framework.

This library is heavily influenced by [Raylib on Swift by STREGAsGate](https://github.com/STREGAsGate/Raylib).

### Code Example

<details>
<summary><b>Basic Window</b></summary>

```kotlin
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
```

</details>

<details>
<summary><b>Basic Keyboard Input</b></summary>

```kotlin
import kaylibkit.kCore.*
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kEnums.KeyboardKey
import kaylibkit.kMath.kVector2
import kaylibkit.kShapes.drawCircle
import kaylibkit.kText.drawText
import kaylibc.*

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 450

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    setConfigFlags(ConfigFlag.VSYNC_HINT) // Turn VSYNC On
    initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - keyboard input")

    val ballPosition = kVector2(SCREEN_WIDTH/2F, SCREEN_HEIGHT/2F)
    //--------------------------------------------------------------------------------------

    // Main game loop
    while (!windowShouldClose) { // Detect window close button or ESC key

        // Update
        //----------------------------------------------------------------------------------
        if (isKeyDown(KeyboardKey.RIGHT)) { ballPosition.x += 2 }
        if (isKeyDown(KeyboardKey.LEFT)) { ballPosition.x -= 2 }
        if (isKeyDown(KeyboardKey.UP)) { ballPosition.y -= 2 }
        if (isKeyDown(KeyboardKey.DOWN)) { ballPosition.y += 2 }
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
```

</details>

<details>
<summary><b>Texture Loading and Drawing</b></summary>

```kotlin
import kaylibkit.kCore.*
import kaylibkit.kEnums.ConfigFlag
import kaylibkit.kText.drawText
import kaylibkit.kTextures.drawTexture
import kaylibkit.kTextures.loadTexture
import kaylibkit.kTextures.unloadTexture
import kaylibc.*

const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 450

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    setConfigFlags(ConfigFlag.VSYNC_HINT) // Turn VSYNC On
    initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [textures] example - texture loading and drawing")
    //--------------------------------------------------------------------------------------

    // NOTE: Textures MUST be loaded after Window initialization (OpenGL context is required)
    val texture = loadTexture("resources/raylib_logo.png") // Texture loading

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

            drawTexture(texture, SCREEN_WIDTH/2 - texture.width/2, SCREEN_HEIGHT/2 - texture.height/2, white)

            drawText("this IS a texture!", 360, 370, 10, gray)
            //----------------------------------------------------------------------------------
        }
    }
    // De-Initialization
    //--------------------------------------------------------------------------------------
    unloadTexture(texture) // Texture unloading

    closeWindow() // Close window and OpenGL context
    //--------------------------------------------------------------------------------------
}
```

</details>

<details>
<summary><b>3D Free Camera</b></summary>

```kotlin
iimport kaylibkit.kCamera.setCameraMode
        import kaylibkit.kCamera.updateCamera
        import kaylibkit.kCore.*
        import kaylibkit.kEnums.CameraMode
        import kaylibkit.kEnums.CameraProjection
        import kaylibkit.kEnums.ConfigFlag
        import kaylibkit.kEnums.KeyboardKey
        import kaylibkit.kMath.kVector3
        import kaylibkit.kMath.setZero
        import kaylibkit.kModels.drawCube
        import kaylibkit.kModels.drawCubeWires
        import kaylibkit.kModels.drawGrid
        import kaylibkit.kShapes.drawRectangle
        import kaylibkit.kShapes.drawRectangleLines
        import kaylibkit.kText.drawText
        import kaylibkit.kTypes.kCamera3D
        import kaylibkit.kUtils.fade
        import kaylibc.*

        const val SCREEN_WIDTH = 800
const val SCREEN_HEIGHT = 450

//------------------------------------------------------------------------------------
// Program main entry point
//------------------------------------------------------------------------------------
fun main() {
    setConfigFlags(ConfigFlag.VSYNC_HINT) // Turn VSYNC On
    initWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "raylib [core] example - 3d camera free")

    val camera = kCamera3D(kVector3(10F, 10F, 10F), kVector3(), kVector3(0F, 1F, 0F), 45F, CameraProjection.PERSPECTIVE)
    val cubePosition = kVector3()

    setCameraMode(camera, CameraMode.FREE)
    //--------------------------------------------------------------------------------------

    // Main game loop
    while (!windowShouldClose) { // Detect window close button or ESC key

        // Update
        //----------------------------------------------------------------------------------
        updateCamera(camera)
        if (isKeyDown(KeyboardKey.Z)) { camera.target.setZero() }
        //----------------------------------------------------------------------------------

        drawing {
            // Draw
            //----------------------------------------------------------------------------------
            clearBackground(rayWhite)

            mode3D(camera) {
                drawCube(cubePosition, 2F, 2F, 2F, red)
                drawCubeWires(cubePosition, 2F, 2F, 2F, maroon)

                drawGrid(10, 1F)

            }

            drawRectangle(10, 10, 320, 133, fade(skyBlue, .5F))
            drawRectangleLines(10, 10, 320, 133, blue)

            drawText("Free camera default controls:", 20, 20, 10, black)
            drawText("- Mouse Wheel to Zoom in-out", 40, 40, 10, darkGray)
            drawText("- Mouse Wheel Pressed to Pan", 40, 60, 10, darkGray)
            drawText("- Alt + Mouse Wheel Pressed to Rotate", 40, 80, 10, darkGray)
            drawText("- Alt + Ctrl + Mouse Wheel Pressed for Smooth Zoom", 40, 100, 10, darkGray)
            drawText("- Z to zoom to (0, 0, 0)", 40, 120, 10, darkGray)
            //----------------------------------------------------------------------------------
        }
    }
    // De-Initialization
    //--------------------------------------------------------------------------------------
    closeWindow() // Close window and OpenGL context
    //--------------------------------------------------------------------------------------
}
```

</details>

More examples can be found [here.](https://codeberg.org/Kenta/KaylibKit/src/branch/main/examples)

## Contributors

- [Kenta](https://codeberg.org/Kenta) - Creator and maintainer
- [fredthedeadhead](https://codeberg.org/fredthedeadhead) - Contributor
- [dallas-hyde](https://codeberg.org/dallas-hyde) - Contributor

## Credits & Mentions

- [STREGAsGate](https://github.com/STREGAsGate/Raylib) - An amazing person who drove me into learning how to do bindings, and now I can't stop. Creator of Raylib on Swift that drove this project to exist.