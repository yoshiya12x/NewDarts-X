package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.ActivityDoubleOutBinding
import e.yoppie.newdartsx.util.ScoreManagement
import e.yoppie.newdartsx.view.fragment.ButtonDialogFragment
import e.yoppie.newdartsx.viewmodel.DoubleOutViewModel
import kotlinx.android.synthetic.main.activity_double_out.*
import kotlinx.android.synthetic.main.activity_explain.*
import kotlinx.android.synthetic.main.fragment_dialog.*


class DoubleOutActivity : AppCompatActivity() {
    private var isPreCode = false
    private var throwCount = 0
    private lateinit var viewModel: DoubleOutViewModel

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDoubleOutBinding>(this, R.layout.activity_double_out)

        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(DoubleOutViewModel::class.java)
        binding.doubleOutViewModel = viewModel

        viewModel.initView(this)
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

        if (scoreModel.score == -1) {
            viewModel.initView(this)
            return super.dispatchKeyEvent(event)
        }

        viewModel.hit(scoreModel.score)

        if (viewModel.isAbleFinishGame()) finishGame(scoreModel.isDouble)

        when (throwCount) {
            1 -> {
                first_throw.text = scoreModel.score.toString()
            }
            2 -> {
                second_throw.text = scoreModel.score.toString()
            }
            3 -> {
                third_throw.text = scoreModel.score.toString()
                finishGame(scoreModel.isDouble)
            }
        }

        return super.dispatchKeyEvent(event)
    }

    private fun finishGame(isDouble: Boolean) {
        val fragment = ButtonDialogFragment()
        val isSuccess = viewModel.isSuccess(isDouble)

        if(isSuccess) fragment.title = "Success" else fragment.title = "Fail"
        fragment.show(supportFragmentManager, "button")

    }
}
