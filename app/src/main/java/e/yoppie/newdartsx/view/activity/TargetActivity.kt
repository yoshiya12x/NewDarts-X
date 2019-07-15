package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.ActivityTargetBinding
import e.yoppie.newdartsx.model.room.entity.EffectEntity
import e.yoppie.newdartsx.model.room.entity.SoundEntity
import e.yoppie.newdartsx.repository.EffectRepository
import e.yoppie.newdartsx.repository.SoundRepository
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.util.ScoreManagement
import e.yoppie.newdartsx.util.Sound
import e.yoppie.newdartsx.viewmodel.TargetViewModel
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_target.*

class TargetActivity : AppCompatActivity() {

    private var isPreCode = false
    private lateinit var soundRepository: SoundRepository
    private lateinit var effectRepository: EffectRepository
    private lateinit var viewModel: TargetViewModel
    private val sound = Sound()
    private var soundEntity: SoundEntity? = null
    private var effectEntity: EffectEntity? = null
    private var bullCount = 0
    private var inBullCount = 0
    private var allCount = 0

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val searchWord = intent.getStringExtra("searchWord")

        val binding = DataBindingUtil.setContentView<ActivityTargetBinding>(this, R.layout.activity_target)
        viewModel = ViewModelProviders.of(this).get(TargetViewModel::class.java)
        binding.targetViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.setImages(searchWord)

        arrowFloatingActionButton.clicks().subscribe {
            if (soundEntity != null && soundEntity!!.othersFlag!!) sound.play(this, R.raw.button_sound)
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("bullCount", bullCount)
            intent.putExtra("inBullCount", inBullCount)
            intent.putExtra("allCount", allCount)
            startActivity(intent)
        }

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
        playAward(score)
        allCount++
        isPreCode = false

        return super.dispatchKeyEvent(event)
    }

    private fun playAward(score: Int) {
        when (score) {
            50 -> {
                viewModel.changeImage()
                if (soundEntity!!.bullSound != 0) sound.play(this, soundEntity!!.bullSound!!)
                if (effectEntity!!.bullEffect != 0) Animation.runLottieAnimation(
                    target_parent,
                    effectEntity!!.bullEffect!!,
                    this
                )
                bullCount++
            }
            100 -> {
                viewModel.changeImage()
                if (soundEntity!!.inBullSound != 0) sound.play(this, soundEntity!!.inBullSound!!)
                if (effectEntity!!.inBullEffect != 0) Animation.runLottieAnimation(
                    target_parent,
                    effectEntity!!.inBullEffect!!,
                    this
                )
                inBullCount++
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun loadSound() {
        Completable
            .fromAction { soundEntity = soundRepository.getSavedSound() }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "sound loaded") }
    }

    @SuppressLint("CheckResult")
    private fun loadEffect() {
        Completable
            .fromAction { effectEntity = effectRepository.getSavedEffect() }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "effect loaded") }
    }
}
