package e.yoppie.newdartsx.util

import android.content.Context
import android.databinding.BindingAdapter
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import e.yoppie.newdartsx.R

object Animation {
    fun emphasize(context: Context, view: View) {
        val handler = Handler()
        val r = object : Runnable {
            override fun run() {
                val animation = AnimationUtils.loadAnimation(context, R.anim.emphasize)
                view.startAnimation(animation)
                handler.postDelayed(this, 1500)
            }
        }
        handler.post(r)
    }

    @JvmStatic
    @BindingAdapter("customBackground")
    fun Button.customBackground(id: Int){
        this.setBackgroundResource(id)
    }
}
