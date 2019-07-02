package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.util.Sound
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Animation.emphasize(this, start_button)

        val soundPool = Sound.makeSoundPool()
        val buttonSound = soundPool.load(this, R.raw.button_sound, 1)

        start_button.clicks().subscribe{
                soundPool.play(buttonSound, 1.0f, 1.0f, 0, 0, 1.0f)
                val intent = Intent(this, TargetActivity::class.java)
                startActivity(intent)
            }
    }
}
