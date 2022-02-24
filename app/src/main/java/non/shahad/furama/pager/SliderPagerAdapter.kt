package non.shahad.furama.pager

import android.widget.LinearLayout

import android.view.ViewGroup

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.viewpager.widget.PagerAdapter

class SliderPagerAdapter(private val context: Context) : PagerAdapter() {

    override fun getCount(): Int {
        return 6
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = LayoutInflater.from(context).inflate(non.shahad.furama.R.layout.viewholder_banner, container, false)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}