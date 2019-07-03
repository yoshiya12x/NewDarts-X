package e.yoppie.newdartsx.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import e.yoppie.newdartsx.view.fragment.FragmentItem

class SettingFragmentPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    private val itemSize = 3

    override fun getItem(position: Int): Fragment = FragmentItem.forPosition(position).newFragment.invoke()

    override fun getCount(): Int = itemSize

    override fun getPageTitle(position: Int): CharSequence? = FragmentItem.forPosition(position).title
}