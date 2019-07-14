package e.yoppie.newdartsx.util

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

class Sound {

    private var soundPool: SoundPool

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(1)
            .build()
    }

    fun play(context: Context, soundId: Int) {
        val sound = soundPool.load(context, soundId, 1)
        soundPool.setOnLoadCompleteListener { soundPool, _, _ ->
            soundPool.play(sound, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }
}