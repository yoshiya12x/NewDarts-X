package e.yoppie.newdartsx.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import e.yoppie.newdartsx.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

class DoubleOutViewModel : ViewModel() {
    companion object {
        private const val DOUBLE_OUT_JSON = R.raw.double_out
        private const val MAX_SCORE = 168
        private const val OFFSET = 2
        private const val INIT_TEXT = ""
        private val invalidTarget = listOf<Int>(
            159,
            165,
            162,
            163,
            165,
            166,
            168,
            169
        )
    }

    var target: Int = 0
    val targetLiveData: MutableLiveData<String> = MutableLiveData()
    val exFirstThrowLiveData: MutableLiveData<String> = MutableLiveData()
    val exSecondThrowLiveData: MutableLiveData<String> = MutableLiveData()
    val exThirdThrowLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        targetLiveData.value = target.toString()
        exFirstThrowLiveData.value = INIT_TEXT
        exSecondThrowLiveData.value = INIT_TEXT
        exThirdThrowLiveData.value = INIT_TEXT
    }

    fun initView(context: Context) {
        val assetManager = context.resources.openRawResource(DOUBLE_OUT_JSON)
        val buffer = ByteArray(assetManager.available())
        assetManager.read(buffer)
        assetManager.close()

        target = Random.nextInt(MAX_SCORE) + OFFSET

        if (!isAbleFinishWithDouble(target)) initView(context)

        targetLiveData.postValue(target.toString())
        val jsonObject = JSONObject(String(buffer))
        val exThrowTarget = jsonObject.getJSONArray(target.toString()) as JSONArray

        val exFirstThrow = if (exThrowTarget.length() > 0) exThrowTarget.getString(0) else INIT_TEXT
        val exSecondThrow = if (exThrowTarget.length() > 1) exThrowTarget.getString(1) else INIT_TEXT
        val exThirdThrow = if (exThrowTarget.length() > 2) exThrowTarget.getString(2) else INIT_TEXT

        exFirstThrowLiveData.postValue(exFirstThrow)
        exSecondThrowLiveData.postValue(exSecondThrow)
        exThirdThrowLiveData.postValue(exThirdThrow)

    }

    fun countDownTarget(score: Int) {
        target -= score
        targetLiveData.postValue(target.toString())
    }

    fun isAbleFinishGame() = target <= 0

    private fun isAbleFinishWithDouble(target: Int) = !invalidTarget.contains(target)

    fun isSuccess(isDouble: Boolean) = target == 0 && isDouble
}
