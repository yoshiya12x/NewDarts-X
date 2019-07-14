package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.model.room.entity.SoundEntity
import e.yoppie.newdartsx.repository.SoundRepository
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.util.Sound
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    private lateinit var soundRepository: SoundRepository
    private var soundEntity: SoundEntity? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        soundRepository = SoundRepository(this)
        loadSound()

        val bullCount = intent.getIntExtra("bullCount", 0)
        val inBullCount = intent.getIntExtra("inBullCount", 0)
        val allCount = intent.getIntExtra("allCount", 0)
        bullScoreTextView.text = "$bullCount/$allCount"
        inBullScoreTextView.text = "$inBullCount/$allCount"
        Animation.emphasize(this, againButton)

        initButtons()
    }

    @SuppressLint("CheckResult")
    private fun initButtons(){
        val buttonSound = Sound(this, R.raw.button_sound)
        closeButton.clicks().subscribe {
            if(soundEntity != null && soundEntity!!.othersFlag!!) buttonSound.play()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        againButton.clicks().subscribe {
            if(soundEntity != null && soundEntity!!.othersFlag!!) buttonSound.play()
            val intent = Intent(this, SelectWordActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("CheckResult")
    private fun loadSound() {
        Completable
            .fromAction { soundEntity = soundRepository.getSavedSound() }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "sound loaded") }
    }
}
