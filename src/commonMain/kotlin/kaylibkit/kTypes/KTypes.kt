package kaylibkit.kTypes

import kaylibkit.kMath.kVector2
import kaylibkit.kMath.set
import kaylibkit.kShapes.set
import kaylibkit.kImage.set
import kaylibkit.kTextures.set
import kaylibc.*
import kaylibkit.kEnums.CameraProjection
import kaylibkit.kMath.kVector3
import kotlinx.cinterop.*

// -- Module: kTypes

//=======================================================//
// Type Constructors
//=======================================================//


/**
 * Constructor function for [Color].
 * @return [Color]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kColor(r: UByte = 0U, g: UByte = 0U, b: UByte = 0U, a: UByte = 0U, allocator: AutofreeScope) : Color {
    return allocator.alloc<Color> {
        this.r = r
        this.g = g
        this.b = b
        this.a = a
    }
}

/**
 * Set value of a [Color] with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Color.set(other: Color) {
    this.r = other.r
    this.g = other.g
    this.b = other.b
    this.a = other.a
}

/**
 * Constructor function for [Texture].
 * @return [Texture]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kTexture(id: UInt, width:Int, height: Int, mipmaps: Int, format: Int, allocator: AutofreeScope) : Texture {
    return allocator.alloc<Texture> {
        this.id = id
        this.width = width
        this.height = height
        this.mipmaps = mipmaps
        this.format = format
    }
}

/**
 * Constructor function for RenderTexture.
 * @return [RenderTexture]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kRenderTexture(id: UInt, texture: Texture, depth: Texture, allocator: AutofreeScope) : RenderTexture {
    return allocator.alloc<RenderTexture> {
        this.id = id
        this.texture.set(texture)
        this.depth.set(depth)
    }
}

/**
 * Constructor function for [NPatchInfo].
 * @return [NPatchInfo]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kNPatchInfo(source: Rectangle, left: Int, top: Int, right: Int, bottom: Int, layout: Int, allocator: AutofreeScope) : NPatchInfo {
    return allocator.alloc<NPatchInfo> {
        this.source.set(source)
        this.left = left
        this.top = top
        this.right = right
        this.bottom = bottom
        this.layout = layout
    }
}

/**
 * Constructor function for [GlyphInfo].
 * @return [GlyphInfo]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kGlyphInfo(value: Int, offsetX: Int, offsetY: Int, advanceX: Int, image: Image, allocator: AutofreeScope) : GlyphInfo {
    return allocator.alloc<GlyphInfo> {
        this.value = value
        this.offsetX = offsetX
        this.offsetY = offsetY
        this.advanceX = advanceX
        this.image.set(image)

    }
}

/**
 * Constructor function for [Font].
 * @return [Font]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kFont(baseSize: Int, glyphCount: Int, glyphPadding: Int, texture: Texture, recs: Rectangle, glyphs: GlyphInfo, allocator: AutofreeScope) : Font {
    return allocator.alloc<Font> {
        this.baseSize = baseSize
        this.glyphCount = glyphCount
        this.glyphPadding = glyphPadding
        this.texture.set(texture)
        this.recs = recs.ptr
        this.glyphs = glyphs.ptr
    }
}

/**
 * Constructor function for [Camera3D].
 * @return [Camera3D]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kCamera3D(position: Vector3 = kVector3(allocator = null), target: Vector3 = kVector3(allocator = null), up: Vector3 = kVector3(allocator = null), fovy: Float = 0F, projection: CameraProjection, allocator: AutofreeScope) : Camera3D {
    return allocator.alloc<Camera3D> {
        this.position.set(position)
        this.target.set(target)
        this.up.set(up)
        this.fovy = fovy
        this.projection = projection.value
    }
}

/**
 * Constructor function for [Camera2D].
 * @return [Camera2D]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kCamera2D(offset: Vector2 = kVector2(allocator = null), target: Vector2 = kVector2(allocator = null), rotation: Float = 0F, zoom: Float = 0F, allocator: AutofreeScope) : Camera2D {
    return allocator.alloc<Camera2D> {
        this.offset.set(offset)
        this.target.set(target)
        this.rotation = rotation
        this.zoom = zoom
    }
}

/**
 * Constructor function for [Mesh].
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kMesh(vertexCount: Int, triangleCount: Int, vertices: FloatVar, texcoords: FloatVar, texcoords2: FloatVar, normals: FloatVar, tangents: FloatVar, colors: UByteVar, indices: UShortVar, animVertices: FloatVar, animNormals: FloatVar, boneIds: UByteVar, boneWeights: FloatVar, vaoId: UInt, vboId: UIntVar , allocator: AutofreeScope) : Mesh {
    return allocator.alloc<Mesh> {
        this.vertexCount = vertexCount
        this.triangleCount = triangleCount
        this.vertices = vertices.ptr
        this.texcoords = texcoords.ptr
        this.texcoords2 = texcoords2.ptr
        this.normals = normals.ptr
        this.tangents = tangents.ptr
        this.colors = colors.ptr
        this.indices = indices.ptr
        this.animVertices = animVertices.ptr
        this.animNormals = animNormals.ptr
        this.boneIds = boneIds.ptr
        this.boneWeights = boneWeights.ptr
        this.vaoId = vaoId
        this.vboId = vboId.ptr
    }
}

/**
 * Set value of a Color with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
@OptIn(ExperimentalForeignApi::class)
inline fun Mesh.set(other: Mesh) {
    this.vertexCount = other.vertexCount
    this.triangleCount = other.triangleCount
    this.vertices = other.vertices
    this.texcoords = other.texcoords
    this.texcoords2 = other.texcoords2
    this.normals = other.normals
    this.tangents = other.tangents
    this.colors = other.colors
    this.indices = other.indices
    this.animNormals = other.animNormals
    this.animVertices = other.animVertices
    this.boneIds = other.boneIds
    this.boneWeights = other.boneWeights
    this.vaoId = other.vaoId
    this.vboId = other.vboId
}

/**
 * Constructor function for [Shader].
 * @return [Shader]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kShader(id: UInt, locs: IntVar, allocator: AutofreeScope = MemScope()) : Shader {
    return allocator.alloc<Shader> {
        this.id = id
        this.locs = locs.ptr
    }
}

/**
 * Set value of a Shader with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
@OptIn(ExperimentalForeignApi::class)
inline fun Shader.set(other: Shader) {
    this.id = other.id
    this.locs = other.locs
}

/**
 * Constructor function for [MaterialMap].
 * @return [MaterialMap]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kMaterialMap(texture: Texture2D, color: Color, value: Float, allocator: AutofreeScope) : MaterialMap {
    return allocator.alloc<MaterialMap> {
        this.texture.set(texture)
        this.color.set(color)
        this.value = value
    }
}

/**
 * Constructor function for [Material].
 * @return [Material]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kMaterial(shader: Shader, maps: MaterialMap, params: CArrayPointer<FloatVar>, allocator: AutofreeScope) : Material {
    return MaterialConstructor(shader.readValue(), maps.ptr, params).getPointer(allocator).pointed
}

/**
 * Constructor function for [Transform].
 * @return [Transform]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kTransform(translation: Vector3, rotation: Quaternion, scale: Vector3, allocator: AutofreeScope) : Transform {
    return allocator.alloc<Transform> {
        this.translation.set(translation)
        this.rotation.set(rotation)
        this.scale.set(scale)
    }
}

/**
 * Constructor function for [BoneInfo].
 * @return [BoneInfo]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kBoneInfo(name: CArrayPointer<ByteVar>, parent: Int, allocator: AutofreeScope) : BoneInfo {
    return BoneInfoConstructor(name, parent).getPointer(allocator).pointed
}

/**
 * Constructor function for [Model].
 * @return [Model]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kModel(transform: Matrix, meshCount: Int, materialCount: Int, meshes: Mesh, materials: Material, meshMaterial: IntVar, boneCount: Int, bones: BoneInfo, bindPose: Transform, allocator: AutofreeScope) : Model {
    return allocator.alloc<Model> {
        this.transform.set(transform)
        this.meshCount = meshCount
        this.materialCount = materialCount
        this.meshes = meshes.ptr
        this.materials = materials.ptr
        this.meshMaterial = meshMaterial.ptr
        this.boneCount = boneCount
        this.bones = bones.ptr
        this.bindPose = bindPose.ptr
    }
}

/**
 * Constructor function for [ModelAnimation].
 * @return [ModelAnimation]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kModelAnimation(boneCount: Int, frameCount: Int, bones: BoneInfo, framePoses: CPointer<CPointerVar<Transform>>, allocator: AutofreeScope) : ModelAnimation {
    return allocator.alloc<ModelAnimation> {
        this.boneCount = boneCount
        this.frameCount = frameCount
        this.bones = bones.ptr
        this.framePoses = framePoses
    }
}

/**
 * Constructor function for [Ray].
 * @return [Ray]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kRay(position: Vector3 = kVector3(allocator = null), direction: Vector3 = kVector3(allocator = null), allocator: AutofreeScope) : Ray {
    return allocator.alloc<Ray> {
        this.position.set(position)
        this.direction.set(direction)
    }
}

/**
 * Constructor function for [RayCollision].
 * Important to note that this uses `MemScope()` by default.
 * @return [RayCollision]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kRayCollision(hit: Boolean = false, distance: Float = 0F, point: Vector3 = kVector3(allocator = null), normal: Vector3 = kVector3(allocator = null), allocator: AutofreeScope) : RayCollision {
    return allocator.alloc<RayCollision> {
        this.hit = hit
        this.distance = distance
        this.point.set(point)
        this.normal.set(normal)
    }
}

/**
 * Constructor function for [BoundingBox].
 * Important to note that this uses `MemScope()` by default.
 * @return [BoundingBox]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kBoundingBox(min: Vector3 = kVector3(allocator = null), max: Vector3 = kVector3(allocator = null), allocator: AutofreeScope) : BoundingBox {
    return allocator.alloc<BoundingBox> {
        this.min.set(min)
        this.max.set(max)
    }
}

/**
 * Constructor function for [Wave].
 * @return [Wave]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kWave(frameCount: UInt, sampleRate: UInt, sampleSize: UInt, channels: UInt, data: COpaquePointer?, allocator: AutofreeScope) : Wave {
    return allocator.alloc<Wave> {
        this.frameCount = frameCount
        this.sampleRate = sampleRate
        this.sampleSize = sampleSize
        this.channels = channels
        this.data = data
    }
}

/**
 * Constructor function for [AudioStream].
 * @return [AudioStream]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kAudioStream(buffer: rAudioBuffer, processor: rAudioProcessor, sampleRate: UInt, sampleSize: UInt, channels: UInt, allocator: AutofreeScope) : AudioStream {
    return allocator.alloc<AudioStream> {
        this.buffer = buffer.ptr
        this.processor = processor.ptr
        this.sampleRate = sampleRate
        this.sampleSize = sampleSize
        this.channels = channels
    }
}

/**
 * Constructor function for [Sound].
 * @return [Sound]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kSound(stream: AudioStream, frameCount: UInt, allocator: AutofreeScope) : Sound {
    return SoundConstructor(stream.readValue(), frameCount).getPointer(allocator).pointed
}

/**
 * Constructor function for [Music].
 * @return [Music]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kMusic(stream: AudioStream, frameCount: UInt, looping: Boolean, ctxType: Int, data: COpaquePointer?, allocator: AutofreeScope) : Music {
    return MusicConstructor(stream.readValue(), frameCount, looping, ctxType, data).getPointer(allocator).pointed
}

/**
 * Constructor function for [VrDeviceInfo].
 * @return [VrDeviceInfo]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kVrDeviceInfo(hResolution: Int, vResolution: Int, hScreenSize: Float, vScreenSize: Float, vScreenCenter: Float, eyeToScreenDistance: Float, lensSeparationDistance: Float, interpupillaryDistance: Float, lensDistortionValues: CArrayPointer<FloatVar>, chromaAbCorrection: CArrayPointer<FloatVar>, allocator: AutofreeScope) : VrDeviceInfo {
    return VrDeviceInfoConstructor(hResolution, vResolution, hScreenSize, vScreenSize, vScreenCenter, eyeToScreenDistance, lensSeparationDistance, interpupillaryDistance, lensDistortionValues, chromaAbCorrection).getPointer(allocator).pointed
}

/**
 * Constructor function for [VrStereoConfig].
 * @return [VrStereoConfig]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kVrStereoConfig(projection: CArrayPointer<Matrix>, viewOffset: CArrayPointer<Matrix>, leftLensCenter: CArrayPointer<FloatVar>, rightLensCenter: CArrayPointer<FloatVar>, leftScreenCenter: CArrayPointer<FloatVar>, rightScreenCenter: CArrayPointer<FloatVar>, scale: CArrayPointer<FloatVar>, scaleIn: CArrayPointer<FloatVar>, allocator: AutofreeScope ) : VrStereoConfig {
    return VrStereoConfigConstructor(projection, viewOffset, leftLensCenter, rightLensCenter, leftScreenCenter, rightScreenCenter, scale, scaleIn).getPointer(allocator).pointed
}

/**
 * Constructor function for [FilePathList].
 * @return [FilePathList]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun kFilePathList(capacity: UInt, count: UInt, paths: CPointer<CPointerVar<ByteVar>>, allocator: AutofreeScope) : FilePathList {
    return allocator.alloc<FilePathList> {
        this.capacity = capacity
        this.count = count
        this.paths = paths
    }
}
