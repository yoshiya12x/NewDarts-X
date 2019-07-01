package e.yoppie.newdartsx.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.ActivityMainBinding
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        Animation.emphasize(this, binding.startButton)

        setContentView(R.layout.activity_main)
    }
}
