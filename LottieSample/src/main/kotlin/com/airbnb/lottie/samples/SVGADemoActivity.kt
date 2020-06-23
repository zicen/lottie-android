package com.airbnb.lottie.samples

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAParser.ParseCompletion
import com.opensource.svgaplayer.SVGAVideoEntity
import kotlinx.android.synthetic.main.activity_svga.*
import java.io.File
import java.io.FileInputStream
import java.net.URL


class SVGADemoActivity : AppCompatActivity() {
    var currentIndex = 0

    var svgaImageView : SVGAImageView ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_svga)
        SVGAParser.shareParser().init(this);

        val assetsName = intent.extras?.get(EXTRA_ARGS)

        svgaImageView = SVGAImageView(this)
        svgaImageView?.setBackgroundColor(Color.BLACK)
        svgaImageView?.setOnClickListener { svgaImageView?.stepToFrame(currentIndex++, false) }
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        container.addView(svgaImageView, params)
        val parser = SVGAParser(this)
        assetsName?.let {
            if (it is Uri) {
                parser.decodeFromInputStream(contentResolver.openInputStream(it)!!, it.path
                        .toString(),
                        object : ParseCompletion{
                    override fun onComplete(videoItem: SVGAVideoEntity) {
                        Log.d(TAG, "decodeFromInputStream onComplete")
                        svgaImageView?.setVideoItem(videoItem)
                        svgaImageView?.stepToFrame(0, true)
                    }

                    override fun onError() {
                        Log.d(TAG, "decodeFromInputStream onError")
                    }
                }, true)
            } else if (it is String) {
                if (it.startsWith("http") || it.startsWith("https")) {
                    val url = URL(it)
                    parser.decodeFromURL(url, object : ParseCompletion{
                        override fun onComplete(videoItem: SVGAVideoEntity) {
                            Log.d(TAG, "decodeFromURL onComplete")
                            svgaImageView?.setVideoItem(videoItem)
                            svgaImageView?.stepToFrame(0, true)
                        }

                        override fun onError() {
                            Log.d(TAG, "decodeFromURL onError")
                        }
                    })
                } else {
                    parser.decodeFromAssets(it, object : ParseCompletion{
                        override fun onComplete(videoItem: SVGAVideoEntity) {
                            Log.d(TAG, "decodeFromAssets onComplete")
                            svgaImageView?.setVideoItem(videoItem)
                            svgaImageView?.stepToFrame(0, true)
                        }

                        override fun onError() {
                            Log.d(TAG, "decodeFromAssets onError")
                        }
                    })
                }

            }
        }


    }

    companion object {
        const val EXTRA_ARGS = "extra_args";
        const val TAG = "SVGADemoActivity"

        fun intent(context: Context, args: String): Intent {
            return Intent(context, SVGADemoActivity::class.java).apply {
                putExtra(EXTRA_ARGS, args)
            }
        }

        fun intent(context: Context, args: Uri): Intent {
            return Intent(context, SVGADemoActivity::class.java).apply {
                putExtra(EXTRA_ARGS, args)
            }
        }
    }

}
