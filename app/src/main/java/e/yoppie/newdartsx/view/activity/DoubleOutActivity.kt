package e.yoppie.newdartsx.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.util.ScoreManagement
import kotlinx.android.synthetic.main.activity_double_out.*

class DoubleOutActivity : AppCompatActivity() {
    private var isPreCode = false
    private var throwCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_double_out)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action != KeyEvent.ACTION_DOWN) {
            return super.dispatchKeyEvent(event)
        }

        val scoreManagement = ScoreManagement(event)
        if (scoreManagement.isPreCode()) {
            isPreCode = true
            return super.dispatchKeyEvent(event)
        }

        val scoreModel = if (isPreCode) scoreManagement.convertPreCodeNob() else scoreManagement.convertNob()
        throwCount++
        isPreCode = false

        when (throwCount) {
            1 -> first_throw.text = scoreModel.score.toString()
            2 -> second_throw.text = scoreModel.score.toString()
            3 -> third_throw.text = scoreModel.score.toString()
        }


        return super.dispatchKeyEvent(event)
    }
}
