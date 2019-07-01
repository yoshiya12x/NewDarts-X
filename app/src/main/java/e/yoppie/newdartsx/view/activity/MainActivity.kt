package e.yoppie.newdartsx.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.util.Animation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Animation.emphasize(this, start_button)
    }
}
