package e.yoppie.newdartsx.view.fragment

import android.support.v4.app.Fragment

enum class FragmentItem(
    val position: Int,
    val title: String,
    val newFragment: () -> Fragment
) {
    SEARCH(
        position = 0,
        title = "SEARCH WORDS",
        newFragment = { SearchWordsFragment.newInstance() }
    ),
    SOUND(
        position = 1,
        title = "SOUND",
        newFragment = { SoundSettingFragment.newInstance() }
    ),
    EFFECT(
        position = 2,
        title = "EFFECT",
        newFragment = { EffectSettingFragment.newInstance() }
    );

    companion object {
        fun forPosition(position: Int): FragmentItem {
            return values().first { it.position == position }
        }
    }
}