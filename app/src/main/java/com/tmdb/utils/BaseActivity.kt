package com.tmdb.utils

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.WindowManager

/**
 * Created by ANDROID on 16/1/18.
 */

open class BaseActivity : AppCompatActivity() {

    /**
     * Make status bar  transparent and
     * It will work only for API level 19 and above.
     *
     * @param makeTranslucent
     */
    protected fun setStatusBarTranslucent(makeTranslucent: Boolean) {
        Log.e("", "setStatusBarTranslucent: " + makeTranslucent)
        if (makeTranslucent) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }


    /**
     * Make revel animation while opening screen
     *
     * @param view
     */
    protected fun revelAnimation(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {

                    if (v != null) {
                        v.removeOnLayoutChangeListener(this)
                        val cx = view.width / 2
                        val cy = view.height / 2
                        val finalRadius = Math.max(view.width, view.height).toFloat()
                        // create the animator for this view (the start radius is zero)
                        val circularReveal = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius)
                        circularReveal.duration = 800
                        view.visibility = View.VISIBLE
                        circularReveal.start()
                    }

                }
            })
        }
    }

}

