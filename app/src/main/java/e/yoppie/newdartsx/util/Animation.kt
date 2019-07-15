package e.yoppie.newdartsx.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.databinding.BindingAdapter
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
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
    fun Button.customBackground(id: Int) {
        this.setBackgroundResource(id)
    }

    fun runLottieAnimation(parent: ConstraintLayout, id: Int, context: Context?) {
        val lottieAnimationView = LottieAnimationView(context)
        lottieAnimationView.setAnimation(id)
        parent.addView(lottieAnimationView)
        lottieAnimationView.playAnimation()
        lottieAnimationView.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                lottieAnimationView.visibility = View.GONE
            }
        })

    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {
        if (!url.isNullOrBlank()) {
            Glide.with(this.context)
                .load(url)
                .error(android.R.drawable.btn_star_big_on)
                .into(this)
        }
    }
}
