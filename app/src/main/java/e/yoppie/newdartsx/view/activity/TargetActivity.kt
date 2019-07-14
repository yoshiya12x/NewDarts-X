package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.model.room.entity.EffectEntity
import e.yoppie.newdartsx.model.room.entity.SoundEntity
import e.yoppie.newdartsx.repository.EffectRepository
import e.yoppie.newdartsx.repository.SoundRepository
import e.yoppie.newdartsx.util.ScoreManagement
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class TargetActivity : AppCompatActivity() {

    private var isPreCode = false
    private lateinit var soundRepository: SoundRepository
    private lateinit var effectRepository: EffectRepository
    private var soundEntity: SoundEntity? = null
    private var effectEntity: EffectEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        soundRepository = SoundRepository(this)
        effectRepository = EffectRepository(this)
        loadSound()
        loadEffect()
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event!!.action != KeyEvent.ACTION_DOWN) {
            return super.dispatchKeyEvent(event)
        }

        val scoreManagement = ScoreManagement(event)
        if (scoreManagement.isPreCode()) {
            isPreCode = true
            return super.dispatchKeyEvent(event)
        }

        val score = if (isPreCode) scoreManagement.convertPreCode() else scoreManagement.convert()
        isPreCode = false

        return super.dispatchKeyEvent(event)
    }

    @SuppressLint("CheckResult")
    private fun loadSound(){
        Completable
            .fromAction { soundEntity = soundRepository.getSavedSound() }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "sound loaded") }
    }

    @SuppressLint("CheckResult")
    private fun loadEffect(){
        Completable
            .fromAction { effectEntity = effectRepository.getSavedEffect() }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "effect loaded") }
    }
}
