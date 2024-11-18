<div align="center">
<p>

[Back to Main Page](./README.md)
</p>

# Development

</div>

## Table of Contents

- [Development](#development)
  - [Table of Contents](#table-of-contents)
  - [Summary](#summary)
  - [Roadmap](#roadmap)
  - [Covered API](#covered-api)
  - [Known Issues](#known-issues)

## Summary

**KaylibKit** is still in early stages of development.

Kotlin/Native is known for its terrible performance on the desktop as it's currently not the focus for JetBrains so please don't expect amazing performance coming out of this just yet!
We can only hope and dream that JetBrains will one day decide to prioritise performance over portability at some point, and so this is why this library exists, for that one day to come.

For most cases, while KaylibKit is still in early stages of development, all parts of Raylib have been wrapped apart from raygui which is planned for future.

## Roadmap

The current roadmap is:

- [x] Add support for Window MinGW.
- [x] Add support for MacOS.
- [x] Better approach towards CStruct constructors.
- [ ] WASM Export. *(Needs new WASI/WASM backend from Jetbrains)*
- [ ] Cleanup the code and remove unnecessary mess.
- [ ] Optimise example codes.

## Covered API

- [x] raylib.h
- [x] raymath.h
- [x] easings.h
- [ ] raygui.h

## Known Issues

1. Performance on Windows is abysmal due to the `-femulated-tls` flag passed by Kotlin/Native that can't be removed. Linux and macOS is unaffected.
2. You're unable to pass path to resources module when using IntelliJ IDEA, all resources have to be located in the directory as your main entry code. Hopefully to be fixed by Jetbrains at some point.
