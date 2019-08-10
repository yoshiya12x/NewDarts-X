package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.stetho.Stetho
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.model.room.entity.EffectEntity
import e.yoppie.newdartsx.model.room.entity.SoundEntity
import e.yoppie.newdartsx.repository.EffectRepository
import e.yoppie.newdartsx.repository.SoundRepository
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.util.Bgm
import e.yoppie.newdartsx.util.Sound
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var soundRepository: SoundRepository
    private lateinit var effectRepository: EffectRepository
    private var soundEntity: SoundEntity? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Animation.emphasize(this, double_out)
        Animation.emphasize(this, bull_game)

        val buttonSound = Sound()
        double_out.clicks().subscribe {
            if (soundEntity != null && soundEntity!!.othersFlag!!) buttonSound.play(this, R.raw.button_sound)
            val intent = Intent(this, ExplainActivity::class.java)
            startActivity(intent)
        }
        bull_game.clicks().subscribe {
            if (soundEntity != null && soundEntity!!.othersFlag!!) buttonSound.play(this, R.raw.button_sound)
            val intent = Intent(this, SelectWordActivity::class.java)
            startActivity(intent)
        }
        setting_button.clicks().subscribe {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        soundRepository = SoundRepository(this)
        effectRepository = EffectRepository(this)

        initEffect()
        initStetho()
    }

    override fun onResume() {
        super.onResume()
        initSound()
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
                soundRepository.insertSound(soundEntity!!)
            }
            .subscribeOn(Schedulers.io())
            .subscribe { playBgm() }
    }

    private fun playBgm() {
        if (!soundEntity!!.bgmFlag!!) return
        Bgm.start(applicationContext)
    }

    @SuppressLint("CheckResult")
    private fun initEffect() {
        var effectEntity: EffectEntity? = null
        Completable
            .fromAction { effectEntity = effectRepository.getSavedEffect() }
            .subscribeOn(Schedulers.io())
            .subscribe { if (effectEntity == null) initialEffect() }
    }

    @SuppressLint("CheckResult")
    private fun initialEffect() {
        Completable
            .fromAction { effectRepository.insertEffect(EffectEntity.create()) }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "new effect inserted") }
    }

    private fun initStetho() {
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()

        )

    }
}
