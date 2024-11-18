package kaylibkit.kAudio

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kAudio

//=======================================================//
// AUDIO DEVICE MANAGEMENT
//=======================================================//

/**
 * Initialize audio device and context
 */
inline fun initAudioDevice() {
    InitAudioDevice()
}

/**
 * Close the audio device and context
 */
inline fun closeAudioDevice() {
    CloseAudioDevice()
}

/**
 * Check if audio device has been initialized successfully
 * @return [Boolean]
 */
inline val isAudioDeviceReady: Boolean
    get() { return IsAudioDeviceReady() }

/**
 * Set master volume (listener)
 */
inline fun setMasterVolume(volume: Float) {
    SetMasterVolume(volume)
}

//=======================================================//
// WAVE/SOUND LOADING & UNLOADING FUNCTIONS
//=======================================================//

/**
 * Load wave data from file
 * @return [Wave]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadWave(fileName: String, allocator: AutofreeScope) : Wave {
    return LoadWave(fileName).getPointer(allocator).pointed
}

/**
 * Load wave from memory buffer, [fileType] refers to extension: i.e. ".wav"
 * @return [Wave]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadWaveFromMemory(fileType: String, fileData: UByteVar, dataSize: Int, allocator: AutofreeScope) : Wave {
    return LoadWaveFromMemory(fileType, fileData.ptr, dataSize).getPointer(allocator).pointed
}

/**
 * Load [Sound] from file
 * @return [Sound]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadSound(fileName: String, allocator: AutofreeScope) : Sound {
    return LoadSound(fileName).getPointer(allocator).pointed
}

/**
 * Load [Sound] from [wave] data
 * @return [Sound]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadSoundFromWave(wave: Wave, allocator: AutofreeScope) : Sound {
    return LoadSoundFromWave(wave.readValue()).getPointer(allocator).pointed
}

/**
 * Update [sound] buffer with new [data]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun updateSound(sound: Sound, data: COpaquePointer, samplesCount: Int) {
    return UpdateSound(sound.readValue(), data, samplesCount)
}

/**
 * Unload [wave] data
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadWave(wave: Wave) {
    UnloadWave(wave.readValue())
}

/**
 * Unload [sound]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadSound(sound: Sound) {
    UnloadSound(sound.readValue())
}

/**
 * Export [wave] data to file, returns true on success
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun exportWave(wave: Wave, fileName: String) : Boolean {
    return ExportWave(wave.readValue(), fileName)
}

/**
 * Export wave sample data to code (.h), returns true on success
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun exportWaveAsCode(wave: Wave, fileName: String) : Boolean {
    return ExportWaveAsCode(wave.readValue(), fileName)
}

//=======================================================//
// WAVE/SOUND MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Play a [sound]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun playSound(sound: Sound) {
    PlaySound(sound.readValue())
}

/**
 * Stop playing a [sound]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun stopSound(sound: Sound) {
    StopSound(sound.readValue())
}

/**
 * Pause a [sound]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun pauseSound(sound: Sound) {
    PauseSound(sound.readValue())
}

/**
 * Resume a paused [sound]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun resumeSound(sound: Sound) {
    ResumeSound(sound.readValue())
}

/**
 * Check if a [sound] is currently playing
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isSoundPlaying(sound: Sound) : Boolean {
    return IsSoundPlaying(sound.readValue())
}

/**
 * Check if a [sound] is currently playing
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isSoundReady(sound: Sound) : Boolean {
    return IsSoundReady(sound.readValue())
}

/**
 * Set [volume] for a [sound] (1.0 is max level)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setSoundVolume(sound: Sound, volume: Float) {
    SetSoundVolume(sound.readValue(), volume)
}


/**
 * Set [pitch] for a [sound] (1.0 is base level)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setSoundPitch(sound: Sound, pitch: Float) {
    SetSoundPitch(sound.readValue(), pitch)
}


/**
 * Convert [wave] data to desired format
 */
@OptIn(ExperimentalForeignApi::class)
inline fun waveFormat(wave: Wave, sampleRate: Int, sampleSize: Int, channels: Int) {
    WaveFormat(wave.readValue(), sampleRate, sampleSize, channels)
}

/**
 * Copy a [wave] to a new [wave]
 * @return [Wave]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun waveCopy(wave: Wave, allocator: AutofreeScope) : Wave {
    return WaveCopy(wave.readValue()).getPointer(allocator).pointed
}

/**
 * Crop a [wave] to defined samples range
 */
@OptIn(ExperimentalForeignApi::class)
inline fun waveCrop(wave: Wave, initSample: Int, finalSample: Int) {
    WaveCrop(wave.readValue(), initSample, finalSample)
}

/**
 * Load samples data from wave as a floats array
 * @return [FloatVar]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadWaveSamples(wave: Wave, allocator: AutofreeScope) : FloatVar? {
    return LoadWaveSamples(wave.readValue())?.getPointer(allocator)?.pointed
}

/**
 * Unload samples data loaded with LoadWaveSamples()
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadWaveSamples(samples: FloatVar) {
    UnloadWaveSamples(samples.ptr)
}

/**
 * Checks if wave data is ready
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isWaveReady(wave: Wave) : Boolean {
    return IsWaveReady(wave.readValue())
}

//=======================================================//
// MUSIC MANAGEMENT FUNCTIONS
//=======================================================//


/**
 * Load music stream from file
 * @return [Music]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadMusicStream(fileName: String, allocator: AutofreeScope) : Music {
    return LoadMusicStream(fileName).getPointer(allocator).pointed
}

/**
 *Load music stream from data
 * @return [Music]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadMusicStreamFromMemory(fileType: String, data: CPointer<UByteVar>, dataSize: Int, allocator: AutofreeScope) : Music {
    return LoadMusicStreamFromMemory(fileType, data, dataSize).getPointer(allocator).pointed
}

/**
 * Unload [music] stream
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadMusicStream(music: Music) {
    UnloadMusicStream(music.readValue())
}

/**
 * Start [music] playing
 */
@OptIn(ExperimentalForeignApi::class)
inline fun playMusicStream(music: Music) {
    PlayMusicStream(music.readValue())
}

/**
 * Check if [music] is playing
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isMusicStreamPlaying(music: Music) : Boolean {
    return IsMusicStreamPlaying(music.readValue())
}

/**
 * Check if [music] is playing
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isMusicReady(music: Music) : Boolean {
    return IsMusicReady(music.readValue())
}

/**
 * Updates buffers for [music] streaming
 */
@OptIn(ExperimentalForeignApi::class)
inline fun updateMusicStream(music: Music) {
    UpdateMusicStream(music.readValue())
}

/**
 * Stop [music] playing
 */
@OptIn(ExperimentalForeignApi::class)
inline fun stopMusicStream(music: Music) {
    StopMusicStream(music.readValue())
}

/**
 * Pause [music] playing
 */
@OptIn(ExperimentalForeignApi::class)
inline fun pauseMusicStream(music: Music) {
    PauseMusicStream(music.readValue())
}

/**
 * Resume playing paused [music]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun resumeMusicStream(music: Music) {
    ResumeMusicStream(music.readValue())
}

/**
 * Seek [music] to a [position] (in seconds)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun seekMusicStream(music: Music, position: Float) {
    SeekMusicStream(music.readValue(), position)
}

/**
 * Set [volume] for [music] (1.0 is max level)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setMusicVolume(music: Music, volume: Float) {
    SetMusicVolume(music.readValue(), volume)
}

/**
 * Set [pitch] for a [music] (1.0 is base level)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setMusicPitch(music: Music, pitch: Float) {
    SetMusicPitch(music.readValue(), pitch)
}

/**
 * Get [music] time length (in seconds)
 * @return [Float]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getMusicTimeLength(music: Music) : Float {
    return GetMusicTimeLength(music.readValue())
}

/**
 * Get current [music] time played (in seconds)
 * @return [Float]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun getMusicTimePlayed(music: Music) : Float {
    return GetMusicTimeLength(music.readValue())
}

//=======================================================//
// AUDIOSTREAM MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Init audio stream (to stream raw audio pcm data)
 * @return [AudioStream]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun loadAudioStream(sampleRate: UInt, sampleSize: UInt, channels: UInt, allocator: AutofreeScope) : AudioStream {
    return LoadAudioStream(sampleRate, sampleSize, channels).getPointer(allocator).pointed
}

/**
 * Unload audio [stream] and free memory
 */
@OptIn(ExperimentalForeignApi::class)
inline fun unloadAudioStream(stream: AudioStream) {
    UnloadAudioStream(stream.readValue())
}

/**
 * Update audio [stream] buffers with data
 */
@OptIn(ExperimentalForeignApi::class)
inline fun updateAudioStream(stream: AudioStream, data: COpaquePointer, samplesCount: Int) {
    UpdateAudioStream(stream.readValue(), data, samplesCount)
}

/**
 * Check if any audio [stream] buffers requires refill
 * @return [Boolean]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isAudioStreamProcessed(stream: AudioStream) : Boolean {
    return IsAudioStreamProcessed(stream.readValue())
}

/**
 * Play audio [stream]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun playAudioStream(stream: AudioStream) {
    PlayAudioStream(stream.readValue())
}

/**
 * Pause audio [stream]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun pauseAudioStream(stream: AudioStream) {
    PauseAudioStream(stream.readValue())
}

/**
 * Resume audio [stream]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun resumeAudioStream(stream: AudioStream) {
    ResumeAudioStream(stream.readValue())
}

/**
 * Check if audio [stream] is playing
 */
@OptIn(ExperimentalForeignApi::class)
inline fun isAudioStreamPlaying(stream: AudioStream) : Boolean {
    return IsAudioStreamPlaying(stream.readValue())
}

/**
 * Stop audio [stream]
 */
@OptIn(ExperimentalForeignApi::class)
inline fun stopAudioStream(stream: AudioStream) {
    StopAudioStream(stream.readValue())
}

/**
 * Set [volume] for audio [stream] (1.0 is max level)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setAudioStreamVolume(stream: AudioStream, volume: Float) {
    SetAudioStreamVolume(stream.readValue(), volume)
}

/**
 * Set [pitch] for [audio] stream (1.0 is base level)
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setAudioStreamPitch(stream: AudioStream, pitch: Float) {
    SetAudioStreamPitch(stream.readValue(), pitch)
}

/**
 * Default [size] for new audio streams
 */
@OptIn(ExperimentalForeignApi::class)
inline fun setAudioStreamBufferSizeDefault(size: Int) {
    SetAudioStreamBufferSizeDefault(size)
}