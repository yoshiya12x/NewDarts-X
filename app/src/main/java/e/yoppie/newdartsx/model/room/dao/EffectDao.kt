package e.yoppie.newdartsx.model.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import e.yoppie.newdartsx.model.room.entity.EffectEntity

@Dao
interface EffectDao {
    @Insert
    fun insertItem(entity: EffectEntity)

    @Query("SELECT * FROM EffectEntity LIMIT 1")
    fun findItem(): EffectEntity

    @Query("UPDATE EffectEntity SET bullEffect = :bullEffect")
    fun updateBullSound(bullEffect: Int)

    @Query("UPDATE EffectEntity SET inBullEffect = :inBullEffect")
    fun updateInBullSound(inBullEffect: Int)

    @Update
    fun updateItem(entity: EffectEntity)
}