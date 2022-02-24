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
    private var MAX_SCALE = 0.0f
    private val fadeFactor = 0.5f

    init {
        clipChildren = false;
        clipToPadding = false;
        // to avoid fade effect at the end of the page
        overScrollMode = 2;
        setPageTransformer(false, this);
        offscreenPageLimit = 3;
        mPageMargin = 24.dpToPx().toInt()
        setPadding(mPageMargin, mPageMargin, mPageMargin, mPageMargin);
    }

    override fun transformPage(page: View, position: Float) {
        page.setPadding(mPageMargin / 3,mPageMargin / 3,mPageMargin /3 ,mPageMargin/3)

        if (MAX_SCALE == 0.0f && position > 0.0f && position < 1.0f) {
            MAX_SCALE = position;
        }

        val newPosition = position - MAX_SCALE;
        val absolutePosition = abs(newPosition)

        if (position <= -1.0f || position >= 1.0f) {
            page.setAlpha(fadeFactor);

            // Page is not visible -- stop any running animations

        } else if (position == 0.0f) {

            // Page is selected -- reset any views if necessary
            page.setScaleX((1 + MAX_SCALE));
            page.setScaleY((1 + MAX_SCALE));
            page.setAlpha(1f);
        } else {
            page.setScaleX(1 + MAX_SCALE * (1 - absolutePosition));
            page.setScaleY(1 + MAX_SCALE * (1 - absolutePosition));
//            if(fadeEnabled)
                page.setAlpha( Math.max(fadeFactor, 1 - absolutePosition));
        }
    }

    private fun Int.dpToPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.getDisplayMetrics());

    override fun setPageMargin(marginPixels: Int) {
        super.setPageMargin(marginPixels)
        mPageMargin = marginPixels
        setPadding(mPageMargin,mPageMargin,mPageMargin,mPageMargin)
    }


}