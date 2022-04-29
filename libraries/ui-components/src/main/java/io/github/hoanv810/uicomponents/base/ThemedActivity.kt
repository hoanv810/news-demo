package io.github.hoanv810.uicomponents.base

import android.app.ActivityManager.TaskDescription
import android.graphics.BitmapFactory
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.github.hoanv810.ui_components.R
import io.github.hoanv810.uicomponents.util.AppUtils

/**
 * @author hoanv
 * @since 2/23/21
 */
abstract class ThemedActivity : AppCompatActivity() {

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        setTaskTitle(title)
    }

    open fun setTaskTitle(title: CharSequence?) {
        if (!title.isNullOrEmpty()) {
            if (VERSION.SDK_INT >= VERSION_CODES.P) {
                setTaskDescription(
                    TaskDescription(
                        title.toString(), R.drawable.ic_app,
                        ContextCompat.getColor(this, AppUtils.getThemedResId(this, R.attr.colorPrimary))))
            } else {
                setTaskDescription(
                    TaskDescription(
                        title.toString(),
                        BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher),
                        ContextCompat.getColor(this, AppUtils.getThemedResId(this, R.attr.colorPrimary))
                    ))
            }
        }
    }
}