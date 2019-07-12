package e.yoppie.newdartsx.model.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class SearchWordEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "text")
    var text: String? = null

    companion object {
        fun create(text: String): SearchWordEntity {
            val searchWordEntity = SearchWordEntity()
            searchWordEntity.text = text
            return searchWordEntity
        }
    }
}