package e.yoppie.newdartsx.repository

import android.content.Context
import e.yoppie.newdartsx.model.room.AppDatabase
import e.yoppie.newdartsx.model.room.entity.SearchWordEntity

class SearchWordRepository {

    fun getSavedSearchWordList(context: Context): List<SearchWordEntity> {
        val db = AppDatabase.getInstance(context)!!
        return db.searchWordDao().findAll()
    }

    fun deleteSavedSearchWord(context: Context, target: SearchWordEntity) {
        val db = AppDatabase.getInstance(context)!!
        return db.searchWordDao().deleteItem(target)
    }

    fun insertSearchWord(context: Context, target: SearchWordEntity){
        val db = AppDatabase.getInstance(context)!!
        db.searchWordDao().insertItem(target)
    }
}