package e.yoppie.newdartsx.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.SoundSettingFragmentBinding
import e.yoppie.newdartsx.viewmodel.SoundSettingViewModel

class SoundSettingFragment : Fragment() {

    private lateinit var soundSettingViewModel: SoundSettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        soundSettingViewModel = ViewModelProviders.of(this).get(SoundSettingViewModel::class.java)
        soundSettingViewModel.initView(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<SoundSettingFragmentBinding>(
            inflater,
            R.layout.sound_setting_fragment,
            container,
            false
        )

        binding.soundSettingViewModel = soundSettingViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    companion object {
        fun newInstance(): SoundSettingFragment {
            return SoundSettingFragment().apply {
                // do nothing
            }
        }
    }
}