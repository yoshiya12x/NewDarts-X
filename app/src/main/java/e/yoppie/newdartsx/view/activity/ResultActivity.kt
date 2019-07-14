package e.yoppie.newdartsx.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import e.yoppie.newdartsx.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val bullCount = intent.getIntExtra("bullCount", 0)
        val inBullCount = intent.getIntExtra("inBullCount", 0)
        val allCount = intent.getIntExtra("allCount", 0)
    }
}
