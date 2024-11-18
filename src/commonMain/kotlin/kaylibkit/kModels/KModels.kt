package kaylibkit.kModels

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kModels

//=======================================================//
// BASIC 3D GEOMETRIC DRAWING FUNCTIONS
//=======================================================//

/**
 * Draw a line in 3D world space
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawLine3D(startPos: Vector3, endPos: Vector3, color: Color) {
    DrawLine3D(startPos.readValue(), endPos.readValue(), color.readValue())
}

/**
 * Draw a point in 3D space, actually a small line
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawPoint3D(position: Vector3, color: Color) {
    DrawPoint3D(position.readValue(), color.readValue())
}

/**
 * Draw a circle in 3D world space
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCircle3D(center: Vector3, radius: Float, rotationAxis: Vector3, rotationAngle: Float, color: Color) {
    DrawCircle3D(center.readValue(), radius, rotationAxis.readValue(), rotationAngle, color.readValue())
}

/**
 * Draw a capsule with the center of its sphere caps at startPos and endPos
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCapsule(startPos: Vector3, endPos: Vector3, radius: Float, slices: Int, rings: Int, color: Color) {
    DrawCapsule(startPos.readValue(), endPos.readValue(), radius, slices, rings, color.readValue())
}

/**
 * Draw capsule wires with the center of its sphere caps at [startPos] and [endPos]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCapsuleWires(startPos: Vector3, endPos: Vector3, radius: Float, slices: Int, rings: Int, color: Color) {
    DrawCapsuleWires(startPos.readValue(), endPos.readValue(), radius, slices, rings, color.readValue())
}

/**
 * Draw a [color]-filled triangle (vertex in counter-clockwise order!)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawTriangle3D(v1: Vector3, v2: Vector3, v3: Vector3, color: Color) {
    DrawTriangle3D(v1.readValue(), v2.readValue(), v3.readValue(), color.readValue())
}

/**
 * Draw a triangle strip defined by [points]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawTriangleStrip3D(points: Vector3, pointCount: Int, color: Color) {
    DrawTriangleStrip3D(points.ptr, pointCount, color.readValue())
}

/**
 * Draw cube
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCube(position: Vector3, width: Float, height: Float, length: Float, color: Color) {
    DrawCube(position.readValue(), width, height, length, color.readValue())
}

/**
 * Draw cube ([Vector3] version)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCubeV(position: Vector3, size: Vector3, color: Color) {
    DrawCubeV(position.readValue(), size.readValue(), color.readValue())
}

/**
 * Draw cube wires
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCubeWires(position: Vector3, width: Float, height: Float, length: Float, color: Color) {
    DrawCubeWires(position.readValue(), width, height, length, color.readValue())
}

/**
 * Draw cube wires ([Vector3] version)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCubeWiresV(position: Vector3, size: Vector3, color: Color) {
    DrawCubeWiresV(position.readValue(), size.readValue(), color.readValue())
}

/**
 * Draw sphere
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawSphere(centerPos: Vector3, radius: Float, color: Color) {
    DrawSphere(centerPos.readValue(), radius, color.readValue())
}

/**
 * Draw sphere with extended parameters
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawSphereEx(centerPos: Vector3, radius: Float, rings: Int, slices: Int, color: Color) {
    DrawSphereEx(centerPos.readValue(), radius, rings, slices, color.readValue())
}

/**
 * Draw sphere wires
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawSphereWires(centerPos: Vector3, radius: Float, rings: Int, slices: Int, color: Color) {
    DrawSphereWires(centerPos.readValue(), radius, rings, slices, color.readValue())
}

/**
 * Draw a cylinder/cone
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCylinder(position: Vector3, radiusTop: Float, radiusBottom: Float, height: Float, slices: Int, color: Color) {
    DrawCylinder(position.readValue(), radiusTop, radiusBottom, height, slices, color.readValue())
}

/**
 * Draw a cylinder with base at [startPos] and top at [endPos]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCylinderEx(startPos: Vector3, endPos: Vector3, startRadius: Float, endRadius: Float, sides: Int, color: Color) {
    DrawCylinderEx(startPos.readValue(), endPos.readValue(), startRadius, endRadius, sides, color.readValue())
}

/**
 * Draw a cylinder/cone wires
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCylinderWires(position: Vector3, radiusTop: Float, radiusBottom: Float, height: Float, slices: Int, color: Color) {
    DrawCylinderWires(position.readValue(), radiusTop, radiusBottom, height, slices, color.readValue())
}

/**
 * Draw a cylinder wires with base at [startPos] and top at [endPos]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawCylinderWiresEx(startPos: Vector3, endPos: Vector3, startRadius: Float, endRadius: Float, sides: Int, color: Color) {
    DrawCylinderWiresEx(startPos.readValue(), endPos.readValue(), startRadius, endRadius, sides, color.readValue())
}

/**
 * Draw a plane XZ
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawPlane(centerPos: Vector3, size: Vector2, color: Color) {
    DrawPlane(centerPos.readValue(), size.readValue(), color.readValue())
}

/**
 * Draw a [ray] line
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawRay(ray: Ray, color: Color) {
    DrawRay(ray.readValue(), color.readValue())
}

/**
 * Draw a grid (centered at (0, 0, 0))
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawGrid(slices: Int, spacing: Float) {
    DrawGrid(slices, spacing)
}

//=======================================================//
// 3D MODEL LOADING FUNCTIONS
//=======================================================//

/**
 * Load model from files (meshes and materials)
 * @return [Model]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadModel(fileName: String, allocator: AutofreeScope) : Model {
    return LoadModel(fileName).getPointer(allocator).pointed
}

/**
 * Load model from generated mesh (default material)
 * @return [Model]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadModelFromMesh(mesh: Mesh, allocator: AutofreeScope) : Model {
    return LoadModelFromMesh(mesh.readValue()).getPointer(allocator).pointed
}

/**
 * Unload [model] (including meshes) from memory (RAM and/or VRAM)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadModel(model: Model) {
    UnloadModel(model.readValue())
}

/**
 * Compute [model] bounding box limits (considers all meshes)
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getModelBoundingBox(model: Model, allocator: AutofreeScope) : BoundingBox {
    return GetModelBoundingBox(model.readValue()).getPointer(allocator).pointed
}

//=======================================================//
// 3D MODEL DRAWING FUNCTIONS
//=======================================================//

/**
 * Draw a [model] (with texture if set)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawModel(model: Model , position: Vector3, scale: Float, tint: Color) {
    DrawModel(model.readValue(), position.readValue(), scale, tint.readValue())
}

/**
 * Draw a [model] with extended parameters
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawModelEx(model: Model, position: Vector3, rotationAxis: Vector3, rotationAngle: Float, scale: Vector3, tint: Color) {
    DrawModelEx(model.readValue(), position.readValue(), rotationAxis.readValue(), rotationAngle, scale.readValue(), tint.readValue())
}

/**
 * Draw a [model] wires (with texture if set)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawModelWires(model: Model, position: Vector3, scale: Float, tint: Color) {
    DrawModelWires(model.readValue(), position.readValue(), scale, tint.readValue())
}

/**
 * Draw a [model] wires (with texture if set) with extended parameters
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawModelWiresEx(model: Model, position: Vector3, rotationAxis: Vector3, rotationAngle: Float, scale: Vector3, tint: Color) {
    DrawModelWiresEx(model.readValue(), position.readValue(), rotationAxis.readValue(), rotationAngle, scale.readValue(), tint.readValue())
}

/**
 * Draw bounding [box] (wires)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawBoundingBox(box: BoundingBox, color: Color) {
    DrawBoundingBox(box.readValue(), color.readValue())
}

/**
 * Draw a billboard [texture]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawBillboard(camera: Camera, texture: Texture2D, position: Vector3, size: Float, tint: Color) {
    DrawBillboard(camera.readValue(), texture.readValue(), position.readValue(), size, tint.readValue())
}

/**
 * Draw a billboard [texture] defined by [source]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawBillboardRec(camera: Camera, texture: Texture2D, source: Rectangle, position: Vector3, size: Vector2, tint: Color) {
    DrawBillboardRec(camera.readValue(), texture.readValue(), source.readValue(), position.readValue(), size.readValue(), tint.readValue())
}

/**
 * Draw a billboard [texture] defined by [source] and [rotation]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawBillboardPro(camera: Camera, texture: Texture2D, source: Rectangle, position: Vector3, up: Vector3, size: Vector2, origin: Vector2, rotation: Float, tint: Color) {
    DrawBillboardPro(camera.readValue(), texture.readValue(), source.readValue(), position.readValue(), up.readValue(), size.readValue(), origin.readValue(), rotation, tint.readValue())
}

//=======================================================//
// MESH MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Upload [mesh] vertex data in GPU and provide VAO/VBO ids
 */
@OptIn(ExperimentalForeignApi::class)
inline fun uploadMesh(mesh: Mesh, dynamic: Boolean) {
    UploadMesh(mesh.ptr, dynamic)
}

/**
 * Update [mesh] vertex data in GPU for a specific buffer index
 */
@OptIn(ExperimentalForeignApi::class)
inline fun updateMeshBuffer(mesh: Mesh, index: Int, data: COpaquePointer, dataSize: Int, offset: Int) {
    UpdateMeshBuffer(mesh.readValue(), index, data, dataSize, offset)
}

/**
 * Unload [mesh] data from CPU and GPU
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadMesh(mesh: Mesh) {
    UnloadMesh(mesh.readValue())
}

/**
 * Draw a 3d [mesh] with [material] and [transform]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawMesh(mesh: Mesh, material: Material, transform: Matrix) {
    DrawMesh(mesh.readValue(), material.readValue(), transform.readValue())
}

/**
 * Draw multiple [mesh] instances with [material] and different [transforms]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun drawMeshInstanced(mesh: Mesh, material: Material, transforms: Matrix, instances: Int) {
    DrawMeshInstanced(mesh.readValue(), material.readValue(), transforms.ptr, instances)
}

/**
 * Export mesh data to file, returns true on success
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun exportMesh(mesh: Mesh, fileName: String) : Boolean {
    return ExportMesh(mesh.readValue(), fileName)
}

/**
 * Compute mesh bounding box limits
 * @return [BoundingBox]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun  getMeshBoundingBox(mesh: Mesh, allocator: AutofreeScope) : BoundingBox {
    return GetMeshBoundingBox(mesh.readValue()).getPointer(allocator).pointed
}

/**
 * Compute mesh tangents
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshTangents(mesh: Mesh) {
    GenMeshTangents(mesh.readValue())
}

//=======================================================//
// MESH GENERATION FUNCTIONS
//=======================================================//

/**
 * Generate polygonal mesh
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshPoly(sides: Int, radius: Float, allocator: AutofreeScope) : Mesh {
    return GenMeshPoly(sides, radius).getPointer(allocator).pointed
}

/**
 * Generate plane mesh (with subdivisions)
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshPlane(width: Float, length: Float, resX: Int, resZ: Int, allocator: AutofreeScope) : Mesh {
    return GenMeshPlane(width, length, resX, resZ).getPointer(allocator).pointed
}

/**
 * Generate cuboid mesh
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshCube(width: Float, height: Float, length: Float, allocator: AutofreeScope) : Mesh {
    return GenMeshCube(width, height, length).getPointer(allocator).pointed
}

/**
 * Generate sphere mesh (standard sphere)
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshSphere(radius: Float, rings: Int, slices: Int, allocator: AutofreeScope) : Mesh {
    return GenMeshSphere(radius, rings, slices).getPointer(allocator).pointed
}

/**
 * Generate half-sphere mesh (no bottom cap)
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshHemiSphere(radius: Float, rings: Int, slices: Int, allocator: AutofreeScope) : Mesh {
    return GenMeshHemiSphere(radius, rings, slices).getPointer(allocator).pointed
}

/**
 * Generate cylinder mesh
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshCylinder(radius: Float, height: Float, slices: Int, allocator: AutofreeScope) : Mesh {
    return GenMeshCylinder(radius, height, slices).getPointer(allocator).pointed
}

/**
 * Generate cone/pyramid mesh
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshCone(radius: Float, height: Float, slices: Int, allocator: AutofreeScope) : Mesh {
    return GenMeshCone(radius, height, slices).getPointer(allocator).pointed
}

/**
 * Generate torus mesh
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshTorus(radius: Float, size: Float, radSeg: Int, sides: Int, allocator: AutofreeScope) : Mesh {
    return GenMeshTorus(radius, size, radSeg, sides).getPointer(allocator).pointed
}

/**
 * Generate trefoil knot mesh
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshKnot(radius: Float, size: Float, radSeg: Int, sides: Int, allocator: AutofreeScope) : Mesh {
    return GenMeshKnot(radius, size, radSeg, sides).getPointer(allocator).pointed
}

/**
 * Generate heightmap mesh from image data
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshHeightmap(heightmap: Image, size: Vector3, allocator: AutofreeScope) : Mesh {
    return GenMeshHeightmap(heightmap.readValue(), size.readValue()).getPointer(allocator).pointed
}

/**
 * Generate cubes-based map mesh from image data
 * @return [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun genMeshCubicmap(cubicmap: Image, cubeSize: Vector3, allocator: AutofreeScope) : Mesh {
    return GenMeshCubicmap(cubicmap.readValue(), cubeSize.readValue()).getPointer(allocator).pointed
}

//=======================================================//
// MATERIAL LOADING/UNLOADING FUNCTIONS
//=======================================================//

/**
 * Load materials from model file
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadMaterials(fileName: String, materialCount: IntVar, allocator: AutofreeScope) : Material? {
    return LoadMaterials(fileName, materialCount.ptr)?.getPointer(allocator)?.pointed
}

/**
 * Load default [Material] (Supports: DIFFUSE, SPECULAR, NORMAL maps)
 * @return [Material]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadMaterialDefault(allocator: AutofreeScope) : Material {
    return LoadMaterialDefault().getPointer(allocator).pointed
}

/**
 * Unload [material] from GPU memory (VRAM)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadMaterial(material: Material) {
    UnloadMaterial(material.readValue())
}

/**
 * Set [texture] for a material map type (MATERIAL_MAP_DIFFUSE, MATERIAL_MAP_SPECULAR...)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setMaterialTexture(material: Material, mapType: kaylibkit.kEnums.MaterialMapIndex, texture: Texture2D) {
    SetMaterialTexture(material.ptr, mapType.value, texture.readValue())
}

/**
 * Set [Material] for a [Mesh]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setModelMeshMaterial(model: Model, meshId: Int, materialId: Int) {
    SetModelMeshMaterial(model.ptr, meshId, materialId)
}

//=======================================================//
// MODEL ANIMATION LOADING/UNLOADING FUNCTIONS
//=======================================================//

/**
 * Load model animations from file
 * @return [ModelAnimation]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadModelAnimations(fileName: String, animCount: UIntVar, allocator: AutofreeScope) : ModelAnimation? {
    return LoadModelAnimations(fileName, animCount.ptr)?.getPointer(allocator)?.pointed
}

/**
 * Update model animation pose
 */
@OptIn(ExperimentalForeignApi::class)
inline fun updateModelAnimation(model: Model, anim: ModelAnimation, frame: Int) {
    UpdateModelAnimation(model.readValue(), anim.readValue(), frame)
}

/**
 * Unload animation data
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadModelAnimation(anim: ModelAnimation) {
    UnloadModelAnimation(anim.readValue())
}

/**
 * Unload animation array data
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadModelAnimations(animations: ModelAnimation) {
    UnloadModelAnimation(animations.readValue())
}

/**
 * Check model animation skeleton match
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isModelAnimationValid(model: Model, anim: ModelAnimation) : Boolean {
    return IsModelAnimationValid(model.readValue(), anim.readValue())
}

//=======================================================//
// COLLISION DETECTION FUNCTIONS
//=======================================================//

/**
 * Check collision between two spheres
 * @return [RayCollision]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun checkCollisionSpheres(center1: Vector3, radius1: Float, center2: Vector3, radius2: Float) : Boolean {
    return CheckCollisionSpheres(center1.readValue(), radius1, center2.readValue(), radius2)
}

/**
 * Check collision between two bounding boxes
 * @return [RayCollision]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun checkCollisionBoxes(box1: BoundingBox, box2: BoundingBox) : Boolean {
    return CheckCollisionBoxes(box1.readValue(), box2.readValue())
}

/**
 * Check collision between [box] and sphere
 * @return [RayCollision]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun checkCollisionBoxSphere(box: BoundingBox, center: Vector3, radius: Float) : Boolean {
    return CheckCollisionBoxSphere(box.readValue(), center.readValue(), radius)
}

/**
 * Get collision info between [ray] and sphere
 * @return [RayCollision]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getRayCollisionSphere(ray: Ray, center: Vector3, radius: Float, allocator: AutofreeScope) : RayCollision {
    return GetRayCollisionSphere(ray.readValue(), center.readValue(), radius).getPointer(allocator).pointed
}

/**
 * Get collision info between [ray] and box ([BoundingBox])
 * @return [RayCollision]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getRayCollisionBox(ray: Ray, box: BoundingBox, allocator: AutofreeScope) : RayCollision {
    return GetRayCollisionBox(ray.readValue(), box.readValue()).getPointer(allocator).pointed
}

/**
 * Get collision info between [ray] and [mesh]
 * @return [RayCollision]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getRayCollisionMesh(ray: Ray, mesh: Mesh, transform: Matrix, allocator: AutofreeScope) : RayCollision {
    return GetRayCollisionMesh(ray.readValue(), mesh.readValue(), transform.readValue()).getPointer(allocator).pointed
}

/**
 * Get collision info between [ray] and triangle
 * @return [RayCollision]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getRayCollisionTriangle(ray: Ray, p1: Vector3, p2: Vector3, p3: Vector3, allocator: AutofreeScope) : RayCollision {
    return GetRayCollisionTriangle(ray.readValue(), p1.readValue(), p2.readValue(), p3.readValue()).getPointer(allocator).pointed
}

/**
 * Get collision info between [ray] and quad
 * @return [RayCollision]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getRayCollisionQuad(ray: Ray, p1: Vector3, p2: Vector3, p3: Vector3, p4: Vector3, allocator: AutofreeScope) : RayCollision {
    return GetRayCollisionQuad(ray.readValue(), p1.readValue(), p2.readValue(), p3.readValue(), p4.readValue()).getPointer(allocator).pointed
}

/**
 * Check if a model is ready
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isModelReady(model: Model) : Boolean {
    return IsModelReady(model.readValue())
}