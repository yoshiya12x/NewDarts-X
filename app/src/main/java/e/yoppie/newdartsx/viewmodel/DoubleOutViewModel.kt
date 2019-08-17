package e.yoppie.newdartsx.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import e.yoppie.newdartsx.R
import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random

class DoubleOutViewModel : ViewModel() {
    companion object {
        const val DOUBLE_OUT_JSON = R.raw.double_out
        const val MAX_SCORE = 38
        const val OFFSET = 2
    }

    var targetScore: Int = 0
    val targetScoreLiveData: MutableLiveData<String> = MutableLiveData()
    val exFirstThrowLiveData: MutableLiveData<String> = MutableLiveData()
    val exSecondThrowLiveData: MutableLiveData<String> = MutableLiveData()
    val exThirdThrowLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        targetScoreLiveData.value = targetScore.toString()
        exFirstThrowLiveData.value = ""
        exSecondThrowLiveData.value = ""
        exThirdThrowLiveData.value = ""
    }

    fun initView(context: Context) {
        val assetManager = context.resources.openRawResource(DOUBLE_OUT_JSON)
        val buffer = ByteArray(assetManager.available())
        while (assetManager.read(buffer) == -1) {
        }
        val s = String(buffer)

        targetScore = Random.nextInt(MAX_SCORE) + OFFSET

        if (!isAbleFinishWithDouble(targetScore)) initView(context)

        targetScoreLiveData.postValue(targetScore.toString())

        val answer = JSONObject(s)
        val answerVal = answer.getJSONArray(targetScore.toString()) as JSONArray

        val exFirstThrow = if (answerVal.length() > 0) answerVal.getString(0) else ""
        val exSecondThrow = if (answerVal.length() > 1) answerVal.getString(1) else ""
        val exThirdThrow = if (answerVal.length() > 2) answerVal.getString(2) else ""

        exFirstThrowLiveData.postValue(exFirstThrow)
        exSecondThrowLiveData.postValue(exSecondThrow)
        exThirdThrowLiveData.postValue(exThirdThrow)

    }

    fun hit(score: Int) {
        targetScore -= score
        targetScoreLiveData.postValue(targetScore.toString())
    }

    fun isAbleFinishWithDouble(score: Int) =
        when (score) {
            163 -> false
            165 -> false
            else -> true
        }
}