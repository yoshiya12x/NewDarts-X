package e.yoppie.newdartsx.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.ActivityDoubleOutBinding
import e.yoppie.newdartsx.util.ScoreManagement
import e.yoppie.newdartsx.view.fragment.DoubleOutResultDialogFragment
import e.yoppie.newdartsx.viewmodel.DoubleOutViewModel
import kotlinx.android.synthetic.main.activity_double_out.*

class DoubleOutActivity : AppCompatActivity() {
    private var isPreCode = false
    private var throwCount = 0
    private val viewModel: DoubleOutViewModel by lazy {
        ViewModelProviders.of(this).get(DoubleOutViewModel::class.java)
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDoubleOutBinding>(this, R.layout.activity_double_out)
        binding.apply {
            lifecycleOwner = this@DoubleOutActivity
            doubleOutViewModel = viewModel
        }

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

        val scoreModel = if (isPreCode) scoreManagement.convertPreCodeDoubleOutScore() else scoreManagement.convertDoubleOutScore()
        throwCount++
        isPreCode = false

        if (scoreModel.score == scoreManagement.changeButtonCode) {
            viewModel.initView(this)
            resetVal()
            return super.dispatchKeyEvent(event)
        }

        viewModel.countDownTarget(scoreModel.score)

        if (viewModel.isAbleFinishGame()) finishGame(scoreModel.isDouble)

        when (throwCount) {
            1 -> first_throw.text = getString(
                R.string.double_out_first_throw_text,
                scoreModel.score.toString()
            )
            2 -> second_throw.text = getString(
                R.string.double_out_second_throw_text,
                scoreModel.score.toString()
            )
            3 -> {
                third_throw.text = getString(
                    R.string.double_out_third_throw_text,
                    scoreModel.score.toString()
                )
                finishGame(scoreModel.isDouble)
            }
        }

        return super.dispatchKeyEvent(event)
    }

    private fun finishGame(isDouble: Boolean) {
        val fragment = DoubleOutResultDialogFragment()
        val isSuccess = viewModel.isSuccess(isDouble)
        fragment.title = if(isSuccess) getString(R.string.game_success) else getString(R.string.game_fail)
        fragment.show(supportFragmentManager, DoubleOutResultDialogFragment.TAG)
    }

    private fun resetVal() {
        val emptyText = ""
        first_throw.text = emptyText
        second_throw.text = emptyText
        third_throw.text = emptyText
        throwCount = 0
    }
}
