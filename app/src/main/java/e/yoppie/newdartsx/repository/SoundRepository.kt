package e.yoppie.newdartsx.repository

import android.content.Context
import e.yoppie.newdartsx.model.room.AppDatabase
import e.yoppie.newdartsx.model.room.entity.SoundEntity

class SoundRepository {

    fun getSavedSound(context: Context): SoundEntity {
        val db = AppDatabase.getInstance(context)!!
        return db.soundDao().findItem()
    }

    fun updateSavedSound(context: Context, target: SoundEntity) {
        val db = AppDatabase.getInstance(context)!!
        return db.soundDao().updateItem(target)
    }

    fun insertSearchWord(context: Context, target: SoundEntity){
        val db = AppDatabase.getInstance(context)!!
        db.soundDao().insertItem(target)
    }
}