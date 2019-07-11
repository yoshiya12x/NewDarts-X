package e.yoppie.newdartsx.view.adapter

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.SearchWordItemBinding
import e.yoppie.newdartsx.model.SearchWordModel
import e.yoppie.newdartsx.util.DiffSearchWordCallback
import e.yoppie.newdartsx.view.viewHolder.SearchWordViewHolder
import e.yoppie.newdartsx.viewmodel.SearchWordSettingViewModel

class SearchWordRecyclerAdapter(private val context: Fragment, viewModel: SearchWordSettingViewModel) :
    RecyclerView.Adapter<SearchWordViewHolder>() {

    private var items: MutableList<SearchWordModel> = mutableListOf()

    init {
        viewModel.searchWordListLiveData.observe({ context.lifecycle }, { it?.apply { update(this) } })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchWordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<SearchWordItemBinding>(layoutInflater, R.layout.search_word_item, parent, false)
        binding.lifecycleOwner = context
        return SearchWordViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(p0: SearchWordViewHolder, p1: Int) {

    }

    private fun update(searchWordList: MutableList<SearchWordModel>) {
        val diff = DiffUtil.calculateDiff(DiffSearchWordCallback(items, searchWordList))
        diff.dispatchUpdatesTo(this)
        this.items.clear()
        this.items.addAll(searchWordList)
    }
}