package io.github.hoanv810.uicomponents.util

import android.content.Context
import androidx.annotation.AttrRes

/**
 * @author hoanv
 * @since 2/23/21
 */
object AppUtils {

    fun getThemedResId(context: Context, @AttrRes attr: Int): Int {
        val a = context.theme.obtainStyledAttributes(intArrayOf(attr))
        val resId = a.getResourceId(0, 0)
        a.recycle()
        return resId
    }
}