package e.yoppie.newdartsx.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import e.yoppie.newdartsx.R

class EffectSettingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.effect_setting_fragment, container, false)
    }

    companion object{
        fun newInstance(): EffectSettingFragment{
            return EffectSettingFragment().apply {
                // do nothing
            }
        }
    }
}