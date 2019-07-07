package e.yoppie.newdartsx.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.model.EffectModel

class EffectSettingViewModel : ViewModel() {

    val isAllSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isInBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    var bullButtonBackGrounds: MutableMap<Int, MutableLiveData<Int>> = mutableMapOf()
    var inBullButtonBackGrounds: MutableMap<Int, MutableLiveData<Int>> = mutableMapOf()

    init {
        isAllSwitchChecked.value = false
        isBullSwitchChecked.value = false
        isInBullSwitchChecked.value = false
        EffectModel.getAll().forEach {
            val mutableLiveData1 = MutableLiveData<Int>()
            mutableLiveData1.value = it.backGroundId
            val mutableLiveData2 = MutableLiveData<Int>()
            mutableLiveData2.value = it.backGroundId
            bullButtonBackGrounds[it.id] = mutableLiveData1
            inBullButtonBackGrounds[it.id] = mutableLiveData2
        }
    }

    fun onClickBullButton(id: Int) {
        isBullSwitchChecked.postValue(true)
        bullButtonBackGrounds.forEach {
            if (id == it.key) it.value.postValue(R.drawable.square_button2_selector)
            else it.value.postValue(R.drawable.square_button_selector)
        }
        if (isInBullSwitchChecked.value!!) isAllSwitchChecked.postValue(true)
    }

    fun onClickInBullButton(id: Int) {
        isInBullSwitchChecked.postValue(true)
        inBullButtonBackGrounds.forEach {
            if (id == it.key) it.value.postValue(R.drawable.square_button2_selector)
            else it.value.postValue(R.drawable.square_button_selector)
        }
        if (isBullSwitchChecked.value!!) isAllSwitchChecked.postValue(true)
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