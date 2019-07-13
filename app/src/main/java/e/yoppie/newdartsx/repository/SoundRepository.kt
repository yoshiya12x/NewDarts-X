package e.yoppie.newdartsx.repository

import android.content.Context
import e.yoppie.newdartsx.model.room.AppDatabase
import e.yoppie.newdartsx.model.room.entity.SoundEntity

class SoundRepository {

    fun getSavedSound(context: Context): SoundEntity {
        val db = AppDatabase.getInstance(context)!!
        return db.soundDao().findItem()
    }

    fun insertSound(context: Context, target: SoundEntity){
        val db = AppDatabase.getInstance(context)!!
        db.soundDao().insertItem(target)
    }

    fun updateBgmFlag(context: Context, bgmFlag: Boolean) {
        val db = AppDatabase.getInstance(context)!!
        db.soundDao().updateBgmFlag(bgmFlag)
    }

    fun updateOthersFlag(context: Context, othersFlag: Boolean) {
        val db = AppDatabase.getInstance(context)!!
        db.soundDao().updateOthersFlag(othersFlag)
    }

    fun updateBullSound(context: Context, bullSound: Int) {
        val db = AppDatabase.getInstance(context)!!
        db.soundDao().updateBullSound(bullSound)
    }

    fun updateInBullSound(context: Context, inBullSound: Int) {
        val db = AppDatabase.getInstance(context)!!
        db.soundDao().updateInBullSound(inBullSound)
    }

}