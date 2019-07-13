package e.yoppie.newdartsx.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import android.view.View
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.model.SoundModel
import e.yoppie.newdartsx.model.room.entity.SoundEntity
import e.yoppie.newdartsx.repository.SoundRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class SoundSettingViewModel : ViewModel() {

    private val soundRepository = SoundRepository()
    val isAllSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isBgmSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isInBullSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    val isOthersSwitchChecked: MutableLiveData<Boolean> = MutableLiveData()
    var bullButtonBackGrounds: MutableMap<Int, MutableLiveData<Int>> = mutableMapOf()
    var inBullButtonBackGrounds: MutableMap<Int, MutableLiveData<Int>> = mutableMapOf()
    var bullSoundHandler = {}
    var inBullSoundHandler = {}

    init {
        isAllSwitchChecked.value = false
        isBgmSwitchChecked.value = false
        isBullSwitchChecked.value = false
        isInBullSwitchChecked.value = false
        isOthersSwitchChecked.value = false
        SoundModel.getAll().forEach {
            val mutableLiveData1 = MutableLiveData<Int>()
            val mutableLiveData2 = MutableLiveData<Int>()
            mutableLiveData1.value = it.backGroundId
            mutableLiveData2.value = it.backGroundId
            bullButtonBackGrounds[it.id] = mutableLiveData1
            inBullButtonBackGrounds[it.id] = mutableLiveData2
        }
    }

    @SuppressLint("CheckResult")
    fun initView(context: Context) {
        var soundEntity = SoundEntity()
        Completable
            .fromAction { soundEntity = soundRepository.getSavedSound(context) }
            .subscribeOn(Schedulers.io())
            .subscribe {
                isBgmSwitchChecked.postValue(soundEntity.bgmFlag)
                isOthersSwitchChecked.postValue(soundEntity.othersFlag)
                if (soundEntity.bullSound != 0) {
                    isBullSwitchChecked.postValue(true)
                    val targetId = SoundModel.forSoundId(soundEntity.bullSound!!).id
                    bullButtonBackGrounds[targetId]!!.postValue(R.drawable.square_button2_selector)
                }
                if (soundEntity.inBullSound != 0) {
                    isInBullSwitchChecked.postValue(true)
                    val targetId = SoundModel.forSoundId(soundEntity.inBullSound!!).id
                    inBullButtonBackGrounds[targetId]!!.postValue(R.drawable.square_button2_selector)
                }
                if (soundEntity.bgmFlag!!
                    && soundEntity.othersFlag!!
                    && soundEntity.bullSound != 0
                    && soundEntity.inBullSound != 0
                ) { isAllSwitchChecked.postValue(true) }
            }
    }

    @SuppressLint("CheckResult")
    fun onClickBullButton(id: Int, context: Context) {
        Completable
            .fromAction {
                val bullSound = SoundModel.forId(id).soundId
                soundRepository.updateBullSound(context, bullSound)
            }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "bull sound updated") }

        bullSoundHandler()
        isBullSwitchChecked.postValue(true)
        bullButtonBackGrounds.forEach {
            if (id == it.key) it.value.postValue(R.drawable.square_button2_selector)
            else it.value.postValue(R.drawable.square_button_selector)
        }

        if (isBgmSwitchChecked.value!!
            && isInBullSwitchChecked.value!!
            && isOthersSwitchChecked.value!!
        ) isAllSwitchChecked.postValue(true)
    }

    @SuppressLint("CheckResult")
    fun onClickInBullButton(id: Int, context: Context) {
        Completable
            .fromAction {
                val bullSound = SoundModel.forId(id).soundId
                soundRepository.updateInBullSound(context, bullSound)
            }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "inBull sound updated") }

        inBullSoundHandler()
        isInBullSwitchChecked.postValue(true)
        inBullButtonBackGrounds.forEach {
            if (id == it.key) it.value.postValue(R.drawable.square_button2_selector)
            else it.value.postValue(R.drawable.square_button_selector)
        }
        if (isBgmSwitchChecked.value!!
            && isBullSwitchChecked.value!!
            && isOthersSwitchChecked.value!!
        ) isAllSwitchChecked.postValue(true)
    }

    fun onClickSwitch(view: View, context: Context) {
        when (view.id) {
            R.id.all_switch -> {
                if (isAllSwitchChecked.value!!) {
                    bullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                    inBullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                    Completable
                        .fromAction {
                            val updateEntity = SoundEntity()
                            updateEntity.id = 1
                            updateEntity.bgmFlag = false
                            updateEntity.bullSound = 0
                            updateEntity.inBullSound = 0
                            updateEntity.othersFlag = false
                            soundRepository.updateAll(context, updateEntity)
                        }
                        .subscribeOn(Schedulers.io())
                        .subscribe { Log.d("yoppie_debug", "all updated") }
                } else {
                    bullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                    inBullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                }
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
                    bullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                    if (isBgmSwitchChecked.value!!
                        && isInBullSwitchChecked.value!!
                        && isOthersSwitchChecked.value!!
                    ) isAllSwitchChecked.postValue(true)
                } else {
                    bullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
                    isAllSwitchChecked.postValue(false)
                }
                isBullSwitchChecked.postValue(!isBullSwitchChecked.value!!)
            }
            R.id.in_bull_switch -> {
                if (!isInBullSwitchChecked.value!!) {
                    inBullButtonBackGrounds[1]!!.postValue(R.drawable.square_button2_selector)
                    if (isBgmSwitchChecked.value!!
                        && isBullSwitchChecked.value!!
                        && isOthersSwitchChecked.value!!
                    ) isAllSwitchChecked.postValue(true)
                } else {
                    inBullButtonBackGrounds.forEach { it.value.postValue(R.drawable.square_button_selector) }
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
