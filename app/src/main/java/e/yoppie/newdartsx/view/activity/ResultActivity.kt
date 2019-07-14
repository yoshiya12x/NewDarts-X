package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.model.room.entity.SoundEntity
import e.yoppie.newdartsx.repository.SoundRepository
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.util.Sound
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_result.*
import kotlin.collections.ArrayList

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
        if (allCount > 0) initPie(bullCount, inBullCount, allCount)
    }

    @SuppressLint("CheckResult")
    private fun initButtons() {
        val buttonSound = Sound()
        closeButton.clicks().subscribe {
            if (soundEntity != null && soundEntity!!.othersFlag!!) buttonSound.play(this, R.raw.button_sound)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        againButton.clicks().subscribe {
            if (soundEntity != null && soundEntity!!.othersFlag!!) buttonSound.play(this, R.raw.button_sound)
            val intent = Intent(this, SelectWordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initPie(bullCount: Int, inBullCount: Int, allCount: Int) {
        pie.setUsePercentValues(true)
        pie.centerText = "BULL RATE"
        pie.legend.textColor = Color.WHITE
        pie.legend.textSize = 16f

        val value = listOf(
            bullCount.toFloat(),
            inBullCount.toFloat(),
            (allCount - (bullCount + inBullCount)).toFloat()
        )
        val label = listOf("Bull", "InBull", "Others")
        val entry = ArrayList<PieEntry>()
        for (i in value.indices) {
            entry.add(PieEntry(value[i], label[i]))
        }

        val dataSet = PieDataSet(entry, "")
        dataSet.colors = listOf(
            Color.rgb(3, 135, 236),
            Color.rgb(17, 79, 128),
            Color.rgb(46, 46, 46)
        )
        dataSet.setDrawValues(true)

        val pieData = PieData(dataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(20f)
        pieData.setValueTextColor(Color.WHITE)

        pie.data = pieData
    }

    @SuppressLint("CheckResult")
    private fun loadSound() {
        Completable
            .fromAction { soundEntity = soundRepository.getSavedSound() }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "sound loaded") }
    }
}
