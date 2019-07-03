package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.service.BgmService
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.util.Sound
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BGM START!!
        val intentBgm = Intent(application, BgmService::class.java)
        intentBgm.putExtra("REQUEST_CODE", 1)
        startForegroundService(intentBgm)

        Animation.emphasize(this, start_button)

        val sound = Sound(this, R.raw.button_sound)

        start_button.clicks().subscribe {
            sound.play()
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }
}
