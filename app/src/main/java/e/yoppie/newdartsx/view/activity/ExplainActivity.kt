package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import kotlinx.android.synthetic.main.activity_explain.*

class ExplainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explain)

        game_start.clicks().subscribe {
            val intent = Intent(this, DoubleOutActivity::class.java)
            startActivity(intent)
        }
    }
}
