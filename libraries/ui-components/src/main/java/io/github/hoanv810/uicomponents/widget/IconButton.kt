package io.github.hoanv810.uicomponents.widget

import android.R.attr
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView.ScaleType.CENTER
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import io.github.hoanv810.ui_components.R
import io.github.hoanv810.ui_components.R.dimen
import io.github.hoanv810.ui_components.R.styleable
import io.github.hoanv810.uicomponents.util.AppUtils.getThemedResId

class IconButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatImageButton(context, attrs, defStyleAttr) {

    private val mColorStateList: ColorStateList
    private val mTinted: Boolean
    override fun setImageResource(resId: Int) {
        setImageDrawable(ContextCompat.getDrawable(context, resId))
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(tint(drawable))
    }

    private fun tint(drawable: Drawable?): Drawable? {
        if (drawable == null) {
            return null
        }
        val tintDrawable = DrawableCompat.wrap(if (mTinted) drawable.mutate() else drawable)
        DrawableCompat.setTintList(tintDrawable, mColorStateList)
        return tintDrawable
    }

    init {
        setBackgroundResource(getThemedResId(context, R.attr.selectableItemBackgroundBorderless))
        val ta = context.theme.obtainStyledAttributes(attrs, styleable.IconButton, 0, 0)
        val colorDisabled = ContextCompat.getColor(
            context,
            getThemedResId(context, attr.textColorSecondary)
        )
        val colorDefault = ContextCompat.getColor(
            context,
            getThemedResId(context, attr.textColorPrimary)
        )
        val colorEnabled = ta.getColor(styleable.IconButton_tint, colorDefault)
        mColorStateList = ColorStateList(STATES, intArrayOf(colorEnabled, colorDisabled))
        mTinted = ta.hasValue(styleable.IconButton_tint)
        if (suggestedMinimumWidth == 0) {
            minimumWidth = context.resources.getDimensionPixelSize(dimen.icon_button_width)
        }
        scaleType = CENTER
        setImageDrawable(drawable)
        ta.recycle()
    }

    companion object {
        private val STATES = arrayOf(intArrayOf(attr.state_enabled), intArrayOf(-attr.state_enabled))
    }
}