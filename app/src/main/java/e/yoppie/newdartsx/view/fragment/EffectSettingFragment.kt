package e.yoppie.newdartsx.view.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.EffectSettingFragmentBinding
import kotlinx.android.synthetic.main.effect_setting_fragment.*

class EffectSettingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<EffectSettingFragmentBinding>(
            inflater,
            R.layout.effect_setting_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.bullButton1.clicks().subscribe {
            val lottieAnimationView = LottieAnimationView(context)
            lottieAnimationView.setAnimation(R.raw.bull5)
            parent.addView(lottieAnimationView)
            lottieAnimationView.playAnimation()
            lottieAnimationView.addAnimatorListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    lottieAnimationView.visibility = View.GONE
                }
            })
        }
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance(): EffectSettingFragment {
            return EffectSettingFragment().apply {
                // do nothing
            }
        }
    }
}