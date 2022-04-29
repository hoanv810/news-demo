package io.github.hoanv810.uicomponents.binding

import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.google.gson.internal.bind.util.ISO8601Utils
import java.text.ParseException
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @author hoanv
 * @since 2/24/21
 */

@BindingAdapter("articleImage")
fun SimpleDraweeView.loadArticleImage(urlToImage: String?) {
    visibility = if (!urlToImage.isNullOrEmpty()) {
        setImageURI(urlToImage)
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("articleDate")
fun TextView.formatArticleDate(date: String) {
    try {
        val d = ISO8601Utils.parse(date, ParsePosition(0))
        text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(d)
    } catch (e: ParseException) {
    }
}

@BindingAdapter("articleDescription")
fun TextView.setArticleDescription(description: String?) {
    if (!description.isNullOrEmpty()) {
        visibility = View.VISIBLE
        text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)
    } else {
        visibility = View.GONE
    }
}