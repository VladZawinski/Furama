package non.shahad.furama.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager

class VerticalPager: ViewPager {

    constructor(context: Context) : super(context)
    constructor(context : Context, attrs : AttributeSet) : super(context, attrs)

    private var currentView : View? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int){

        currentView?.let {
            it.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val h = it.measuredHeight
            val height = if (h > 0) h else 0
            val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
            super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
            return
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    fun measureCurrentView(currentView: View?) {
        this.currentView = currentView
        requestLayout()
    }
}