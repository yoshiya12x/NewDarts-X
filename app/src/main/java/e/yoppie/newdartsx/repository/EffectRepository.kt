package e.yoppie.newdartsx.repository

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.Log
import e.yoppie.newdartsx.model.room.AppDatabase
import e.yoppie.newdartsx.model.room.entity.EffectEntity
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class EffectRepository(context: Context) {
    private val db: AppDatabase = AppDatabase.getInstance(context)!!
    private val mainThreadHandler = Handler()

    fun getSavedEffect(): EffectEntity = db.effectDao().findItem()

    fun insertEffect(target: EffectEntity) = db.effectDao().insertItem(target)

    @SuppressLint("CheckResult")
    fun updateBullEffect(bullEffect: Int, handler: () -> Unit) {
        Completable
            .fromAction { db.effectDao().updateBullSound(bullEffect) }
            .subscribeOn(Schedulers.io())
            .subscribe { mainThreadHandler.post { handler() } }
    }

    @SuppressLint("CheckResult")
    fun updateInBullEffect(inBullEffect: Int, handler: () -> Unit) {
        Completable
            .fromAction { db.effectDao().updateInBullSound(inBullEffect) }
            .subscribeOn(Schedulers.io())
            .subscribe { mainThreadHandler.post { handler() } }
    }

    @SuppressLint("CheckResult")
    fun updateAll(target: EffectEntity) {
        Completable
            .fromAction { db.effectDao().updateItem(target) }
            .subscribeOn(Schedulers.io())
            .subscribe { Log.d("yoppie_debug", "all effect updated") }
    }
}
