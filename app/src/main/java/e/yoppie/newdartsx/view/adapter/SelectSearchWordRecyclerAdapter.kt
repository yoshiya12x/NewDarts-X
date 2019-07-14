package e.yoppie.newdartsx.view.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.BR
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.SettingSearchWordItemBinding
import e.yoppie.newdartsx.model.SearchWordModel
import e.yoppie.newdartsx.util.DiffSearchWordCallback
import e.yoppie.newdartsx.view.viewHolder.SearchWordViewHolder
import e.yoppie.newdartsx.viewmodel.SelectWordViewModel
import e.yoppie.newdartsx.viewmodel.SettingSearchWordItemViewModel

class SelectSearchWordRecyclerAdapter(
    private val context: AppCompatActivity,
    viewModel: SelectWordViewModel,
    private val intent: Intent
) : RecyclerView.Adapter<SearchWordViewHolder>() {

    private var items: MutableList<SearchWordModel> = mutableListOf()

    init {
        viewModel.searchWordListLiveData.observe({ context.lifecycle }, { it?.apply { update(this) } })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchWordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<SettingSearchWordItemBinding>(
                layoutInflater,
                R.layout.setting_search_word_item,
                parent,
                false
            )
        binding.lifecycleOwner = context
        return SearchWordViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: SearchWordViewHolder, position: Int) {
        val settingSearchWordItemViewModel = SettingSearchWordItemViewModel()
        settingSearchWordItemViewModel.setSearchWord(items[position])
        holder.binding.apply {
            setVariable(BR.settingSearchWordItemViewModel, settingSearchWordItemViewModel)
            executePendingBindings()
        }
        holder.itemView.clicks().subscribe {
            intent.putExtra("searchWord", items[position].text)
            context.startActivity(intent)
        }
    }

    private fun update(searchWordList: MutableList<SearchWordModel>) {
        val diff = DiffUtil.calculateDiff(DiffSearchWordCallback(items, searchWordList))
        diff.dispatchUpdatesTo(this)
        this.items.clear()
        this.items.addAll(searchWordList)
    }
}