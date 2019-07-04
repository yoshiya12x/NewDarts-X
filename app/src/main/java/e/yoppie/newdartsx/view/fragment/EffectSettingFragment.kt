package e.yoppie.newdartsx.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import kotlinx.android.synthetic.main.effect_setting_fragment.*

class EffectSettingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.effect_setting_fragment, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bull_button_1.clicks().subscribe {
            val lottieAnimationView = LottieAnimationView(context)
            lottieAnimationView.setAnimation(R.raw.bull5)
            parent.addView(lottieAnimationView)
            lottieAnimationView.playAnimation()
        }
    }

    companion object{
        fun newInstance(): EffectSettingFragment{
            return EffectSettingFragment().apply {
                // do nothing
            }
        }
    }
}