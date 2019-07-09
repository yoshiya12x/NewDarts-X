package e.yoppie.newdartsx.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import e.yoppie.newdartsx.R

class SoundSettingViewModel : ViewModel() {
    val isAllSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isBgmSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isInBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isOthersSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    var bullButtonBackGrounds: MutableMap<Int, MutableLiveData<Int>> = mutableMapOf()
    var inBullButtonBackGrounds: MutableMap<Int, MutableLiveData<Int>> = mutableMapOf()
    var bullAnimationHandler = {}
    var inBullAnimationHandler = {}

    init {
        isAllSwitchChecked.value = false
        isBgmSwitchChecked.value = false
        isBullSwitchChecked.value = false
        isInBullSwitchChecked.value = false
        isOthersSwitchChecked.value = false
    }

    fun onClickSwitch(view: View) {
        when (view.id) {
            R.id.all_switch -> {
                isAllSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isBgmSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isBullSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isInBullSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isOthersSwitchChecked.postValue(!isAllSwitchChecked.value!!)
            }
            R.id.bgm_switch -> {
                if (!isBgmSwitchChecked.value!!) {
                    if (isBullSwitchChecked.value!!
                        && isInBullSwitchChecked.value!!
                        && isOthersSwitchChecked.value!!
                    ) isAllSwitchChecked.postValue(true)
                } else {
                    isAllSwitchChecked.postValue(false)
                }
                isBgmSwitchChecked.postValue(!isBgmSwitchChecked.value!!)
            }
            R.id.bull_switch -> {
                if (!isBullSwitchChecked.value!!) {
                    if (isBgmSwitchChecked.value!!
                        && isInBullSwitchChecked.value!!
                        && isOthersSwitchChecked.value!!
                    ) isAllSwitchChecked.postValue(true)
                } else {
                    isAllSwitchChecked.postValue(false)
                }
                isBullSwitchChecked.postValue(!isBullSwitchChecked.value!!)
            }
            R.id.in_bull_switch -> {
                if (!isInBullSwitchChecked.value!!) {
                    if (isBgmSwitchChecked.value!!
                        && isBullSwitchChecked.value!!
                        && isOthersSwitchChecked.value!!
                    ) isAllSwitchChecked.postValue(true)
                } else {
                    isAllSwitchChecked.postValue(false)
                }
                isInBullSwitchChecked.postValue(!isInBullSwitchChecked.value!!)
            }
            R.id.others_switch -> {
                if (!isOthersSwitchChecked.value!!) {
                    if (isBgmSwitchChecked.value!!
                        && isBullSwitchChecked.value!!
                        && isInBullSwitchChecked.value!!
                    ) isAllSwitchChecked.postValue(true)
                } else {
                    isAllSwitchChecked.postValue(false)
                }
                isOthersSwitchChecked.postValue(!isOthersSwitchChecked.value!!)
            }
        }
    }
}
