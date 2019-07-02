package e.yoppie.newdartsx.util

import android.media.AudioAttributes
import android.media.SoundPool

class Sound {
    companion object{
        fun makeSoundPool(): SoundPool {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()

            return SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(1)
                .build()
        }
    }
}