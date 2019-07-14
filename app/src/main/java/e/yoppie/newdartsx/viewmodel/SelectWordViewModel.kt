package e.yoppie.newdartsx.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import e.yoppie.newdartsx.model.SearchWordModel
import e.yoppie.newdartsx.model.room.entity.SearchWordEntity
import e.yoppie.newdartsx.repository.SearchWordRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class SelectWordViewModel : ViewModel() {
    var searchWordListLiveData = MutableLiveData<MutableList<SearchWordModel>>()
    private var searchWordList = mutableListOf(
        SearchWordModel(0, "SEARCH WORD")
    )
    private val searchWordRepository = SearchWordRepository()

    init {
        searchWordListLiveData.value = searchWordList
    }

    @SuppressLint("CheckResult")
    fun set(context: Context) {
        var localSearchWordList: List<SearchWordEntity> = mutableListOf()
        Completable
            .fromAction { localSearchWordList = searchWordRepository.getSavedSearchWordList(context) }
            .subscribeOn(Schedulers.io())
            .subscribe {
                val searchWordList: MutableList<SearchWordModel> = mutableListOf()
                if (localSearchWordList.isNotEmpty()) {
                    localSearchWordList.forEach { searchWordList.add(SearchWordModel(it.id, it.text!!)) }
                    this.searchWordList = searchWordList
                    this.searchWordListLiveData.postValue(searchWordList)
                }
            }
    }
}