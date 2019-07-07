package e.yoppie.newdartsx.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import e.yoppie.newdartsx.R

class EffectSettingViewModel : ViewModel() {

    val isAllSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isInBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()

    init {
        isAllSwitchChecked.value = false
        isBullSwitchChecked.value = false
        isInBullSwitchChecked.value = false
    }

    fun onClickBullButton(view: View) {
        isBullSwitchChecked.postValue(true)
        if (isInBullSwitchChecked.value!!) isAllSwitchChecked.postValue(true)
        Log.d("yoppie_debug", view.id.toString())
    }

    fun onClickInBullButton(view: View) {
        isInBullSwitchChecked.postValue(true)
        if (isBullSwitchChecked.value!!) isAllSwitchChecked.postValue(true)
        Log.d("yoppie_debug", view.id.toString())
    }

    fun onClickSwitch(view: View) {
        when (view.id) {
            R.id.all_switch -> {
                isAllSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isBullSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isInBullSwitchChecked.postValue(!isAllSwitchChecked.value!!)
            }
            R.id.bull_effect_switch -> {
                if (!isBullSwitchChecked.value!! && isInBullSwitchChecked.value!!) {
                    isAllSwitchChecked.postValue(true)
                }
                if (isBullSwitchChecked.value!!) {
                    isAllSwitchChecked.postValue(false)
                }
                isBullSwitchChecked.postValue(!isBullSwitchChecked.value!!)
            }
            R.id.in_bull_effect_switch -> {
                if (!isInBullSwitchChecked.value!! && isBullSwitchChecked.value!!) {
                    isAllSwitchChecked.postValue(true)
                }
                if (isInBullSwitchChecked.value!!) {
                    isAllSwitchChecked.postValue(false)
                }
                isInBullSwitchChecked.postValue(!isInBullSwitchChecked.value!!)
            }
        }
    }
}