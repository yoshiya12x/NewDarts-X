package e.yoppie.newdartsx.util

import android.support.v7.util.DiffUtil
import e.yoppie.newdartsx.model.SearchWordModel

class DiffSearchWordCallback(
    private val oldList: MutableList<SearchWordModel>,
    private val newList: MutableList<SearchWordModel>
) : DiffUtil.Callback() {
    override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == (newList[newPosition])

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int) =
        oldList[oldPosition].id == (newList[newPosition]).id

    override fun getNewListSize() = newList.size

    override fun getOldListSize() = oldList.size
}