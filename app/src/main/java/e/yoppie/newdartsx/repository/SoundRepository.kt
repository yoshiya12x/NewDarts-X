package e.yoppie.newdartsx.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import e.yoppie.newdartsx.model.room.AppDatabase
import e.yoppie.newdartsx.model.room.entity.SoundEntity
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class SoundRepository(context: Context) {
    private val db: AppDatabase = AppDatabase.getInstance(context)!!

    fun getSavedSound(): SoundEntity {
        return db.soundDao().findItem()
    }

    fun insertSound(context: Context, target: SoundEntity){
        val db = AppDatabase.getInstance(context)!!
        db.soundDao().insertItem(target)
    }

    @SuppressLint("CheckResult")
    fun updateBgmFlag(bgmFlag: Boolean) {
        Completable
            .fromAction { db.soundDao().updateBgmFlag(bgmFlag) }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "bgm flag updated") }
    }

    @SuppressLint("CheckResult")
    fun updateOthersFlag(othersFlag: Boolean) {
        Completable
            .fromAction { db.soundDao().updateOthersFlag(othersFlag) }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "others flag updated") }
    }

    @SuppressLint("CheckResult")
    fun updateBullSound(bullSound: Int) {
        Completable
            .fromAction { db.soundDao().updateBullSound(bullSound) }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "bull sound updated") }
    }

    @SuppressLint("CheckResult")
    fun updateInBullSound(inBullSound: Int) {
        Completable
            .fromAction { db.soundDao().updateInBullSound(inBullSound) }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "inBull sound updated") }
    }

    @SuppressLint("CheckResult")
    fun updateAll(target: SoundEntity){
        Completable
            .fromAction { db.soundDao().updateItem(target) }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "all sound updated") }

    }
}