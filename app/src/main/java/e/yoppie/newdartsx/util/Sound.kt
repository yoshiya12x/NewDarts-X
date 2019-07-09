package e.yoppie.newdartsx.util

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

class Sound(context: Context?, soundId: Int) {

    private var soundPool: SoundPool
    private var sound: Int

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(1)
            .build()
        sound = soundPool.load(context, soundId, 1)
    }

    fun play() {
        soundPool.play(sound, 1.0f, 1.0f, 0, 0, 1.0f)
    }
}