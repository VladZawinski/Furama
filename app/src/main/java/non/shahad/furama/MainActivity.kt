package non.shahad.furama

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import non.shahad.furama.databinding.ActivityMainBinding
import non.shahad.furama.pager.SliderPagerAdapter
import non.shahad.furama.pager.TabPagerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        makeStatusBarWhite()
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main).apply {
            pager.adapter = TabPagerAdapter(supportFragmentManager)
            val sliderPagerAdapter = SliderPagerAdapter(this@MainActivity)
            val sliderPager = carouselPager
            sliderPager?.adapter = sliderPagerAdapter

            tab.setOnTabChangeListener {
                pager.currentItem = it
            }
        }
    }


    private fun makeStatusBarWhite(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}