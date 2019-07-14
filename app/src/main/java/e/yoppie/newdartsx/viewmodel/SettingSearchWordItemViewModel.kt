package e.yoppie.newdartsx.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newdartsx.model.SearchWordModel

class SettingSearchWordItemViewModel : ViewModel() {
    val id = MutableLiveData<Int>()
    val text = MutableLiveData<String>()

    private val item = MutableLiveData<SearchWordModel>().apply {
        this.observeForever {
            it?.apply {
                this@SettingSearchWordItemViewModel.id.postValue(this.id)
                this@SettingSearchWordItemViewModel.text.postValue(this.text)
            }
        }
    }

    fun setSearchWord(searchWordModel: SearchWordModel) {
        item.postValue(searchWordModel)
    }
}