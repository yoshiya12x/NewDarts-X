package e.yoppie.newdartsx.view.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

class ButtonDialogFragment : DialogFragment() {

    lateinit var onClickPositive: DialogInterface.OnClickListener
    lateinit var onClickNegative: DialogInterface.OnClickListener
    lateinit var title: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
                .setMessage("Thank you !!!!")
                .setPositiveButton("TOPへ", onClickPositive)
                .setNegativeButton("もう一度", onClickNegative)


            builder.create()
        } ?: throw IllegalAccessError("Activity cannot be null")


    }
}