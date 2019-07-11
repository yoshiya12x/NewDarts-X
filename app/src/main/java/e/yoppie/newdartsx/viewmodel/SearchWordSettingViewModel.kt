package e.yoppie.newdartsx.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newdartsx.model.SearchWordModel

class SearchWordSettingViewModel : ViewModel() {

    lateinit var searchWordListLiveData: MutableLiveData<MutableList<SearchWordModel>>
    lateinit var searchWordList: MutableList<SearchWordModel>

    fun set(searchWordList: MutableList<SearchWordModel>){
        this.searchWordListLiveData.value = searchWordList
        this.searchWordList = searchWordList
    }
}