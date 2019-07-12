package e.yoppie.newdartsx.model.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class SoundEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "bgmFlag")
    var bgmFlag: Boolean? = false

    @ColumnInfo(name = "othersFlag")
    var othersFlag: Boolean? = false

    @ColumnInfo(name = "bullSound")
    var bullSound: Int? = 0

    @ColumnInfo(name = "inBullSound")
    var inBullSound: Int? = 0

    companion object {
        fun create() = SoundEntity()
    }
}