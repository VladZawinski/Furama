package non.shahad.furama.pager

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class CarouselPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ViewPager(context,attrs), ViewPager.PageTransformer {

    private var mPageMargin = 0
    private var maxScale = 0.0f
    private val fadeFactor = 0.5f

    init {
        clipChildren = false;
        clipToPadding = false;
        overScrollMode = 2
        setPageTransformer(false, this);
        offscreenPageLimit = 3
        mPageMargin = 24.dpToPx().toInt()
        setPadding(mPageMargin, mPageMargin, mPageMargin, mPageMargin);
    }

    override fun transformPage(page: View, position: Float) {
        page.setPadding(mPageMargin / 3,mPageMargin / 4,mPageMargin /3 ,mPageMargin/4)

        if (maxScale == 0.0f && position > 0.0f && position < 1.0f) {
            maxScale = position
        }

        val newPosition = position - maxScale;
        val absolutePosition = abs(newPosition)

        if (position <= -1.0f || position >= 1.0f) {
            page.alpha = fadeFactor
        } else if (position == 0.0f) {
            page.scaleX = (1 + maxScale)
            page.scaleY = (1 + maxScale)
            page.alpha = 1f;
        } else {
            page.scaleX = 1 + maxScale * (1 - absolutePosition);
            page.scaleY = 1 + maxScale * (1 - absolutePosition);
            page.alpha = fadeFactor.coerceAtLeast(1 - absolutePosition);
        }
    }

    private fun Int.dpToPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.getDisplayMetrics());

    override fun setPageMargin(marginPixels: Int) {
        super.setPageMargin(marginPixels)
        mPageMargin = marginPixels
        setPadding(mPageMargin,mPageMargin,mPageMargin,mPageMargin)
    }


}