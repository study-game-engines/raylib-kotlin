<div align="center">
<p>

[Back to Main Page](./README.md)
</p>

# Installation Guide

</div>

## Table of Contents

- [Installation Guide](#installation-guide)
  - [Table of Contents](#table-of-contents)
  - [Compatibility](#compatibility)
  - [Dependencies](#dependencies)
  - [Pre-compiled klibs](#pre-compiled-klibs)
  - [Build It Yourself](#build-it-yourself)
  - [What is resourcePath?](#what-is-resourcePath?)

## Compatibility

**Supported Platforms:**

- [x] Linux
- [X] Windows
- [X] MacOS
- [ ] WASM *(Awaiting few WASM backend from Jetbrains)*

## Dependencies

You don't need to have Raylib installed on your system to use KaylibKit.

## Gradle Package

KaylibKit has its own Gradle package hosted on Codeberg as the previous usage of `klibs` is now broken.

To add KaylibKit as a dependency to your project, firstly you have to add Codeberg URL to Maven Repository:

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://codeberg.org/api/packages/Kenta/maven") }
}
```

Now that Maven is aware of KaylibKit, we can simply add it as a dependency:

```kotlin
//Windows
implementation("com.prism-architect:KaylibKit-mingwx64:1.0.5")
//Linux:
implementation("com.prism-architect:KaylibKit-linuxx64:1.0.5")
//macOS Intel:
implementation("com.prism-architect:KaylibKit-macosx64:1.0.5")
//macOS ARM:
implementation("com.prism-architect:KaylibKit-macosarm64:1.0.5")
```

You're now done and ready to play with KaylibKit!

## What is resourcePath?
`resourcePath` was added because, well, Kotlin/Native doesn't play as nice as Java when it comes to handling resources in your Raylib application/game. So, what's the deal? It's a necessary feature that simply hands you the path to a folder called 'resources' that hangs out in the same spot as your app's executable.

Let's say you're loading a texture; all you have to do is ensure that a folder named resources is in the same folder as your executable, alongside your asset added to the said folder 'resources'.

Now simply call 
```kotlin 
loadTexture($resourcePath/myTexture.png)
``` 
That is all. `resourcePath` simplifies it all for you.

In the future we will plan to add ability to change the folder name as currently it's hardcoded to be named 'resources'