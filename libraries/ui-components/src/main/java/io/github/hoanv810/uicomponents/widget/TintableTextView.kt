package io.github.hoanv810.uicomponents.widget

import android.R.attr
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import io.github.hoanv810.ui_components.R
import io.github.hoanv810.uicomponents.util.AppUtils.getThemedResId

/**
 * @author hoanv
 * @since 2/23/21
 */
class TintableTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatTextView(context, attrs, defStyleAttr) {

    private var mTextColor: Int = 0

    init {
        mTextColor = getTextColor(context, attrs)
        val ta = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TintableTextView, 0, 0
        )
        setCompoundDrawablesWithIntrinsicBounds(
            ta.getDrawable(R.styleable.TintableTextView_iconStart),
            ta.getDrawable(R.styleable.TintableTextView_iconTop),
            ta.getDrawable(R.styleable.TintableTextView_iconEnd),
            ta.getDrawable(R.styleable.TintableTextView_iconBottom)
        )
        ta.recycle()
    }

    override fun setCompoundDrawablesWithIntrinsicBounds(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawablesWithIntrinsicBounds(tint(left), tint(top), tint(right), tint(bottom))
    }

    override fun setTextColor(color: Int) {
        mTextColor = color
        super.setTextColor(color)
        val drawables = compoundDrawables
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3])
    }

    private fun getTextColor(context: Context, attrs: AttributeSet?): Int {
        val defaultTextColor = ContextCompat.getColor(
            getContext(),
            getThemedResId(getContext(), attr.textColorTertiary)
        )
        val ta = context.obtainStyledAttributes(attrs, intArrayOf(attr.textAppearance, attr.textColor))
        val ap = ta.getResourceId(0, 0)
        val textColor: Int
        if (ap == 0) {
            textColor = ta.getColor(1, defaultTextColor)
        } else {
            val tap = context.obtainStyledAttributes(ap, intArrayOf(attr.textColor))
            textColor = tap.getColor(0, defaultTextColor)
            tap.recycle()
        }
        ta.recycle()
        return textColor
    }

    private fun tint(drawable: Drawable?): Drawable? {
        if (drawable == null) return null
        val d = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(d, mTextColor)

        return d
    }
}