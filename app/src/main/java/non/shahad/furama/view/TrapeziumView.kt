package non.shahad.furama.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

/**
 * F** it, I didn't finish that.
 */
class TrapeziumView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AppCompatTextView(context, attrs) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val p = Paint()
        p.color = Color.BLACK
        canvas?.drawColor(Color.CYAN)

        val rect = Rect(0,0,3,3)
        val rectF = RectF(rect)

        canvas?.drawRoundRect(rectF,1f,1f,p)
    }

}