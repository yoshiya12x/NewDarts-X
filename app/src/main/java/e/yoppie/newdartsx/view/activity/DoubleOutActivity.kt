package e.yoppie.newdartsx.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.ActivityDoubleOutBinding
import e.yoppie.newdartsx.util.ScoreManagement
import e.yoppie.newdartsx.view.fragment.ButtonDialogFragment
import e.yoppie.newdartsx.viewmodel.DoubleOutViewModel
import kotlinx.android.synthetic.main.activity_double_out.*


class DoubleOutActivity : AppCompatActivity() {
    private var isPreCode = false
    private var throwCount = 0
    private lateinit var viewModel: DoubleOutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDoubleOutBinding>(this, R.layout.activity_double_out)
        val fragment = ButtonDialogFragment()

        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(DoubleOutViewModel::class.java)
        binding.doubleOutViewModel = viewModel

        viewModel.initView(this)
//
//        fragment.onClickPositive = DialogInterface.OnClickListener { _, _ ->
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//        fragment.onClickNegative = DialogInterface.OnClickListener {_, _ ->
//            val intent = Intent(this, DoubleOutActivity::class.java)
//            startActivity(intent)
//        }
//
//        val isSuccess = false
//
//        if (isSuccess) fragment.title = "Success" else {
//            fragment.title = "Fail"
//        }
//
//
//        fragment.show(supportFragmentManager, "button")
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
