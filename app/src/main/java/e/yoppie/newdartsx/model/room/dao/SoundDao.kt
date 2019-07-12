package e.yoppie.newdartsx.model.room.dao

import android.arch.persistence.room.*
import e.yoppie.newdartsx.model.room.entity.SoundEntity

@Dao
interface SoundDao {
    @Insert
    fun insertItem(entity: SoundEntity)

    @Query("SELECT * FROM SoundEntity LIMIT 1")
    fun findItem(): SoundEntity

    @Update
    fun updateItem(entity: SoundEntity)
}