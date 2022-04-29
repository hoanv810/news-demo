package io.github.hoanv810.uicomponents.util

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * @author hoanv
 * @since 2/24/21
 */

fun View.isRtl() = layoutDirection == View.LAYOUT_DIRECTION_RTL

fun Fragment.showSnackMessage(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
}