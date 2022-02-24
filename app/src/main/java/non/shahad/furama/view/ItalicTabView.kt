package non.shahad.furama.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import non.shahad.furama.R

class ItalicTabView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context,attrs, defStyleAttr){

    private var selectedItemId: Int = ROOM
    private val view: View = View.inflate(context, R.layout.view_italic_tab, this)

    fun setOnTabChangeListener(onChange: (Int) -> Unit) {
        val placeHolder = view.findViewById<TextView>(R.id.rectangleShape)
        val byRateView = view.findViewById<TextView>(R.id.byRateTab)

        view.findViewById<TextView>(R.id.byRoomTab).setOnClickListener {
            selectedItemId = ROOM
            onChange(selectedItemId)
            placeHolder.animate().x(0f).setDuration(100)
        }

        byRateView.setOnClickListener {
            selectedItemId = BY_RATES
            onChange(selectedItemId)
            placeHolder.animate().x(byRateView.width.toFloat()).setDuration(100)
        }
    }


    companion object {
        const val ROOM = 0
        const val BY_RATES = 1
    }


}