package e.yoppie.newdartsx.view.fragment

import android.app.Dialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.FragmentDialogBinding
import e.yoppie.newdartsx.view.activity.DoubleOutActivity
import e.yoppie.newdartsx.view.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_dialog.view.*

class ButtonDialogFragment : DialogFragment() {

    lateinit var title: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(activity!!)
            val binding = DataBindingUtil.inflate<FragmentDialogBinding>(LayoutInflater.from(activity), R.layout.fragment_dialog, null, false)
            val view = binding.root
            view.dialog_title.text = title
            view.game_again.clicks().subscribe{
                val intent = Intent(requireActivity(), DoubleOutActivity::class.java)
                startActivity(intent)
            }

            view.to_top.clicks().subscribe{
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }

            builder.setView(view)
            return builder.create()

        } ?: throw IllegalAccessError("Activity cannot be null")


    }
}