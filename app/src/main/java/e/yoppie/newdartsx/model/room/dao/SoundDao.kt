package e.yoppie.newdartsx.model.room.dao

import android.arch.persistence.room.*
import e.yoppie.newdartsx.model.room.entity.SoundEntity

@Dao
interface SoundDao {
    @Insert
    fun insertItem(entity: SoundEntity)

    @Query("SELECT * FROM SoundEntity LIMIT 1")
    fun findItem(): SoundEntity

    @Query("UPDATE SoundEntity SET bgmFlag = :bgmFlag")
    fun updateBgmFlag(bgmFlag: Boolean)

    @Query("UPDATE SoundEntity SET othersFlag = :othersFlag")
    fun updateOthersFlag(othersFlag: Boolean)

    @Query("UPDATE SoundEntity SET bullEffect = :bullSound")
    fun updateBullSound(bullSound: Int)

    @Query("UPDATE SoundEntity SET inBullSound = :inBullSound")
    fun updateInBullSound(inBullSound: Int)

    @Update
    fun updateItem(entity: SoundEntity)
}