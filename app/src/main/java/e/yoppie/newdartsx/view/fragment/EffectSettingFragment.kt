package e.yoppie.newdartsx.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.EffectSettingFragmentBinding
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.viewmodel.EffectSettingViewModel

class EffectSettingFragment : Fragment() {

    private lateinit var effectSettingViewModel: EffectSettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        effectSettingViewModel = ViewModelProviders.of(this).get(EffectSettingViewModel::class.java)
        effectSettingViewModel.initView(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<EffectSettingFragmentBinding>(
            inflater,
            R.layout.effect_setting_fragment,
            container,
            false
        )

        effectSettingViewModel.bullAnimationHandler = {
            Animation.runLottieAnimation(binding.parent, R.raw.bull5, context)
        }
        effectSettingViewModel.inBullAnimationHandler = {
            Animation.runLottieAnimation(binding.parent, R.raw.bull5, context)
        }

        binding.effectSettingViewModel = effectSettingViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

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