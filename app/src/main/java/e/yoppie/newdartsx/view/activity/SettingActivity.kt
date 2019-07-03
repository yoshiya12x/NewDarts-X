package e.yoppie.newdartsx.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.MenuItem
import e.yoppie.newdartsx.R
import e.yoppie.newdartsx.view.adapter.SettingFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SettingTheme)
        setContentView(R.layout.activity_setting)

        supportActionBar?.title = "SETTING"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pagerAdapter = SettingFragmentPagerAdapter(supportFragmentManager)
        setting_view_pager.adapter = pagerAdapter
        setting_tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
        setting_tab_layout.setupWithViewPager(setting_view_pager)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
