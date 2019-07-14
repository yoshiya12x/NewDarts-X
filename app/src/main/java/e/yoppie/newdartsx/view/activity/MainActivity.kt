package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.stetho.Stetho
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.model.room.entity.SoundEntity
import e.yoppie.newdartsx.repository.SoundRepository
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.util.Bgm
import e.yoppie.newdartsx.util.Sound
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var soundRepository: SoundRepository
    private var soundEntity: SoundEntity? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Animation.emphasize(this, nob_game)
        Animation.emphasize(this, bull_game)

        val buttonSound = Sound(this, R.raw.button_sound)
        nob_game.clicks().subscribe {
            if(soundEntity != null && soundEntity!!.othersFlag!!) buttonSound.play()
        }
        bull_game.clicks().subscribe {
            if(soundEntity != null && soundEntity!!.othersFlag!!) buttonSound.play()
        }
        setting_button.clicks().subscribe {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        soundRepository = SoundRepository(this)
        initStetho()
    }

    override fun onResume() {
        super.onResume()
        initSound()
    }

    private fun initStetho() {
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        )
    }

    @SuppressLint("CheckResult")
    private fun initSound() {
        Completable
            .fromAction { soundEntity = soundRepository.getSavedSound() }
            .subscribeOn(Schedulers.io())
            .subscribe {
                if (soundEntity == null) initialSound()
                else playBgm()
            }
    }

    @SuppressLint("CheckResult")
    private fun initialSound() {
        Completable
            .fromAction {
                soundEntity = SoundEntity.create()
                soundRepository.insertSound(this, soundEntity!!)
            }
            .subscribeOn(Schedulers.io())
            .subscribe { playBgm() }
    }

    private fun playBgm() {
        if (!soundEntity!!.bgmFlag!!) return
        Bgm.start(applicationContext)
    }
}
