package com.airbnb.lottie.samples

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAParser.ParseCompletion
import com.opensource.svgaplayer.SVGAVideoEntity
import kotlinx.android.synthetic.main.activity_custom_view_pager.*
import kotlinx.android.synthetic.main.activity_svga.*


class ViewPagerDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view_pager)
//        viewpager.adapter
    }

    companion object {
        const val TAG = "SVGADemoActivity"

        fun intent(context: Context, args: String): Intent {
            return Intent(context, ViewPagerDemoActivity::class.java)
        }
    }

}
