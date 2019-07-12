package e.yoppie.newdartsx.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import e.yoppie.newdartsx.model.SearchWordModel

class SearchWordSettingViewModel : ViewModel() {

    var searchWordListLiveData = MutableLiveData<MutableList<SearchWordModel>>()
    private var searchWordList = mutableListOf(
        SearchWordModel(0, "SEARCH WORD")
    )

    init {
        searchWordListLiveData.value = searchWordList
    }

    fun set(searchWordList: MutableList<SearchWordModel>) {
        this.searchWordListLiveData.value = searchWordList
        this.searchWordList = searchWordList
    }

    fun remove(searchWordItemViewModel: SearchWordItemViewModel) {
        val target = SearchWordModel(searchWordItemViewModel.id.value!!, searchWordItemViewModel.text.value!!)
        searchWordList.remove(target)
        searchWordListLiveData.value = searchWordList
    }

    fun add(text: String) {
        val target = SearchWordModel(searchWordList.size, text)
        searchWordList.add(0, target)
        searchWordListLiveData.value = searchWordList
    }
}