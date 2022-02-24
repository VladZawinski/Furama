package non.shahad.furama.pager

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import non.shahad.furama.fragments.RateFragment
import non.shahad.furama.fragments.RoomFragment
import non.shahad.furama.view.VerticalPager

class TabPagerAdapter(
    private val fragManager: FragmentManager
): FragmentStatePagerAdapter(fragManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 2

    private var currentPosition = -1

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> RoomFragment()
            1 -> RateFragment()
            else -> throw IllegalStateException("Go check your hardcoded positions, DONKEY! ")
        }
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        if (position != currentPosition) {
            val fragment = `object` as Fragment
            val pager = container as VerticalPager
            if (fragment.view != null) {
                currentPosition = position
                pager.measureCurrentView(fragment.view)
            }
        }
    }
}