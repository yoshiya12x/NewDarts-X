package e.yoppie.newdartsx.model.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class EffectEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "bullEffect")
    var bullEffect: Int? = 0

    @ColumnInfo(name = "inBullEffect")
    var inBullEffect: Int? = 0

    companion object {
        fun create() = EffectEntity()
    }
}