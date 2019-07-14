package e.yoppie.newdartsx.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.view.View
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.model.EffectModel
import e.yoppie.newdartsx.model.room.entity.EffectEntity
import e.yoppie.newdartsx.repository.EffectRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class EffectSettingViewModel : ViewModel() {

    val isAllSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isInBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    var bullButtonBackGrounds: MutableMap<Int, MutableLiveData<Int>> = mutableMapOf()
    var inBullButtonBackGrounds: MutableMap<Int, MutableLiveData<Int>> = mutableMapOf()
    var bullAnimationHandler = {}
    var inBullAnimationHandler = {}

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

    @SuppressLint("CheckResult")
    fun initView(context: Context){
        var effectEntity = EffectEntity()
        val effectRepository = EffectRepository(context)
        Completable
            .fromAction { effectEntity = effectRepository.getSavedEffect() }
            .subscribeOn(Schedulers.io())
            .subscribe {
                if(effectEntity.bullEffect != 0){
                    isBullSwitchChecked.postValue(true)
                    val targetId = EffectModel.forEffectId(effectEntity.bullEffect!!).id
                    bullButtonBackGrounds[targetId]!!.postValue(R.drawable.square_button2_selector)
                }
                if(effectEntity.inBullEffect != 0){
                    isInBullSwitchChecked.postValue(true)
                    val targetId = EffectModel.forEffectId(effectEntity.inBullEffect!!).id
                    inBullButtonBackGrounds[targetId]!!.postValue(R.drawable.square_button2_selector)
                }
                if(effectEntity.bullEffect != 0 && effectEntity.inBullEffect != 0){
                    isAllSwitchChecked.postValue(true)
                }
            }
    }

    fun onClickBullButton(id: Int, context: Context) {
        val effectRepository = EffectRepository(context)
        effectRepository.updateBullEffect(EffectModel.forId(id).effectId, bullAnimationHandler)
        isBullSwitchChecked.postValue(true)
        bullButtonBackGrounds.forEach {
            if (id == it.key) it.value.postValue(R.drawable.square_button2_selector)
            else it.value.postValue(R.drawable.square_button_selector)
        }
        if (isInBullSwitchChecked.value!!) isAllSwitchChecked.postValue(true)
    }

    fun onClickInBullButton(id: Int, context: Context) {
        val effectRepository = EffectRepository(context)
        effectRepository.updateInBullEffect(EffectModel.forId(id).effectId, inBullAnimationHandler)
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
                if(isAllSwitchChecked.value!!){
                    bullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                    inBullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                }else{
                    bullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                    inBullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                }
                isAllSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isBullSwitchChecked.postValue(!isAllSwitchChecked.value!!)
                isInBullSwitchChecked.postValue(!isAllSwitchChecked.value!!)
            }
            R.id.bull_effect_switch -> {
                if (!isBullSwitchChecked.value!!) {
                    bullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                    if(isInBullSwitchChecked.value!!) isAllSwitchChecked.postValue(true)
                }else{
                    bullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                    isAllSwitchChecked.postValue(false)
                }
                isBullSwitchChecked.postValue(!isBullSwitchChecked.value!!)
            }
            R.id.in_bull_effect_switch -> {
                if (!isInBullSwitchChecked.value!!) {
                    inBullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                    if(isBullSwitchChecked.value!!) isAllSwitchChecked.postValue(true)
                }else{
                    inBullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                    isAllSwitchChecked.postValue(false)
                }
                isInBullSwitchChecked.postValue(!isInBullSwitchChecked.value!!)
            }
        }
    }
}