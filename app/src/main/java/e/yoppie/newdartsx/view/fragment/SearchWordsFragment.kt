package e.yoppie.newdartsx.view.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.SearchWordsSettingFragmentBinding
import e.yoppie.newdartsx.view.adapter.SearchWordRecyclerAdapter
import e.yoppie.newdartsx.viewmodel.SearchWordSettingViewModel

class SearchWordsFragment : Fragment() {

    private lateinit var searchWordSettingViewModel: SearchWordSettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchWordSettingViewModel = ViewModelProviders.of(this).get(SearchWordSettingViewModel::class.java)
        searchWordSettingViewModel.set(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var binding = DataBindingUtil.inflate<SearchWordsSettingFragmentBinding>(
            inflater,
            R.layout.search_words_setting_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding = initSearchWordRecyclerView(binding)
        binding = initAddWordButton(binding)
        return binding.root
    }

    private fun initSearchWordRecyclerView(binding: SearchWordsSettingFragmentBinding): SearchWordsSettingFragmentBinding? {
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.searchWordRecyclerView.layoutManager = linearLayoutManager
        binding.searchWordRecyclerView.adapter = SearchWordRecyclerAdapter(this, searchWordSettingViewModel)
        return binding
    }

    @SuppressLint("CheckResult")
    private fun initAddWordButton(binding: SearchWordsSettingFragmentBinding): SearchWordsSettingFragmentBinding? {
        binding.addWordButton
            .clicks()
            .filter { binding.userNameEditText.text.toString().isNotBlank() }
            .subscribe {
                searchWordSettingViewModel.add(binding.userNameEditText.text.toString(), context!!)
                binding.searchWordRecyclerView.scrollToPosition(0)
            }
        return binding
    }

    companion object {
        fun newInstance(): SearchWordsFragment {
            return SearchWordsFragment().apply {
                // do nothing
            }
        }
    }
}