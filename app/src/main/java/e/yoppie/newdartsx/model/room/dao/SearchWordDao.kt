package e.yoppie.newdartsx.model.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import e.yoppie.newdartsx.model.room.entity.SearchWordEntity

@Dao
interface SearchWordDao {
    @Insert
    fun insertItem(entity: SearchWordEntity)

    @Query("SELECT * FROM SearchWordEntity")
    fun findAll(): List<SearchWordEntity>

    @Delete
    fun deleteItem(entity: SearchWordEntity)
}