package e.yoppie.newdartsx.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.databinding.ActivitySelectWordBinding
import e.yoppie.newdartsx.view.adapter.SelectSearchWordRecyclerAdapter
import e.yoppie.newdartsx.viewmodel.SelectWordViewModel

class SelectWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivitySelectWordBinding>(this, R.layout.activity_select_word)
        val viewModel = ViewModelProviders.of(this).get(SelectWordViewModel::class.java)
        binding.selectWordViewModel = viewModel

        val linearLayoutManager = LinearLayoutManager(this)
        binding.selectSearchWordRecyclerView.layoutManager = linearLayoutManager
        binding.selectSearchWordRecyclerView.adapter =
            SelectSearchWordRecyclerAdapter(this, viewModel, Intent(this, TargetActivity::class.java))

        viewModel.set(this)
    }
}
