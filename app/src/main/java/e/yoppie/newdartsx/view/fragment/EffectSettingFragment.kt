package e.yoppie.newdartsx.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.EffectSettingFragmentBinding
import e.yoppie.newdartsx.model.room.entity.EffectEntity
import e.yoppie.newdartsx.repository.EffectRepository
import e.yoppie.newdartsx.util.Animation
import e.yoppie.newdartsx.viewmodel.EffectSettingViewModel
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class EffectSettingFragment : Fragment() {

    private lateinit var effectSettingViewModel: EffectSettingViewModel
    private lateinit var effectRepository: EffectRepository
    private val mainThreadHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        effectSettingViewModel = ViewModelProviders.of(this).get(EffectSettingViewModel::class.java)
        effectSettingViewModel.initView(context!!)
        effectRepository = EffectRepository(context!!)
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
            var effectEntity: EffectEntity? = null
            Completable
                .fromAction { effectEntity = effectRepository.getSavedEffect() }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (effectEntity != null && effectEntity!!.bullEffect!! != 0) {
                        mainThreadHandler.post {
                            Animation.runLottieAnimation(binding.parent, effectEntity!!.bullEffect!!, context)
                        }
                    }
                }
        }
        effectSettingViewModel.inBullAnimationHandler = {
            var effectEntity: EffectEntity? = null
            Completable
                .fromAction { effectEntity = effectRepository.getSavedEffect() }
                .subscribeOn(Schedulers.io())
                .subscribe {
                    if (effectEntity != null && effectEntity!!.inBullEffect!! != 0) {
                        mainThreadHandler.post {
                            Animation.runLottieAnimation(binding.parent, effectEntity!!.inBullEffect!!, context)
                        }
                    }
                }
        }

        binding.effectSettingViewModel = effectSettingViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    companion object {
        fun newInstance(): EffectSettingFragment {
            return EffectSettingFragment().apply {
                // do nothing
            }
        }
    }
}