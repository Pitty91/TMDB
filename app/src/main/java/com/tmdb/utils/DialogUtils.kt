package com.tmdb.utils

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ProgressBar
import com.tmdb.R

/**
 * Created by ANDROID on 15/1/18.
 */

class DialogUtils {

    companion object {
        private lateinit var progressDial: Dialog

        /**
         * Progress Dialog for network call
         *
         * @param context
         */
        fun showProgressDialog(context: Context) {
            progressDial = ProgressDialog(context)
            progressDial.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            progressDial.show()
            progressDial.setCancelable(false)
            progressDial.setContentView(R.layout.loading_box)
            val progressBar = progressDial.findViewById<View>(R.id.progressBar1) as ProgressBar
            progressBar.progressDrawable = context.getDrawable(R.drawable.dialog_drawable)
            progressBar.indeterminateDrawable.setColorFilter(-0xf78352, android.graphics.PorterDuff.Mode.MULTIPLY)
        }

        /**
         * Stop Progress dialog
         */
        fun stopProgressDialog() {
            when {
                progressDial.isShowing -> {
                    progressDial.cancel()
                    progressDial.dismiss()
                }
            }
        }
    }
}