package e.yoppie.newdartsx.model.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import e.yoppie.newdartsx.model.room.dao.SearchWordDao
import e.yoppie.newdartsx.model.room.dao.SoundDao
import e.yoppie.newdartsx.model.room.entity.SearchWordEntity
import e.yoppie.newdartsx.model.room.entity.SoundEntity

@Database(entities = [SearchWordEntity::class, SoundEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchWordDao(): SearchWordDao
    abstract fun soundDao(): SoundDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "AppDatabase.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}