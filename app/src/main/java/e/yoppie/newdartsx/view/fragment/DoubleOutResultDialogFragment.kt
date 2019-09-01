package e.yoppie.newdartsx.view.fragment

import android.app.Dialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.databinding.FragmentDialogBinding
import e.yoppie.newdartsx.view.activity.DoubleOutActivity
import e.yoppie.newdartsx.view.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import android.support.v7.app.AppCompatDialogFragment
import android.view.KeyEvent
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.util.ScoreManagement


class DoubleOutResultDialogFragment : AppCompatDialogFragment() {

    var title = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.setOnKeyListener { _, _, event: KeyEvent? ->
            if (event == null) {
                return@setOnKeyListener false
            }

            val scoreManagement = ScoreManagement(event)
            var isPreCode = false

            if (scoreManagement.isPreCode()) {
                isPreCode = true
            }

            val scoreModel = if (isPreCode) scoreManagement.convertPreCodeDoubleOutScore() else scoreManagement.convertDoubleOutScore()

            if (scoreModel.score == scoreManagement.changeButtonCode) {
                val intent = Intent(requireActivity(), DoubleOutActivity::class.java)
                startActivity(intent)
            }

            return@setOnKeyListener false
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val binding = DataBindingUtil.inflate<FragmentDialogBinding>(
                LayoutInflater.from(it),
                R.layout.fragment_dialog,
                null,
                false
            )
            val view = binding.root
            view.apply {
                dialog_title.text = title
                game_again.clicks().subscribe {
                    val intent = Intent(requireActivity(), DoubleOutActivity::class.java)
                    startActivity(intent)
                }
                to_top.clicks().subscribe {
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                }
            }

            builder.setView(view)
            return builder.create()

        } ?: throw IllegalAccessError("Activity cannot be null")


    }

    companion object{
        const val TAG = "ButtonDialog"
    }
}
