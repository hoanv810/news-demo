package io.github.hoanv810.actions

import android.content.Context
import android.content.Intent
import io.github.hoanv810.core.constant.Extras
import io.github.hoanv810.core.data.common.FetchMode
import io.github.hoanv810.core.network.response.Article

/**
 * @author hoanv
 * @since 2/23/21
 */
object Actions {

    fun openTopHeadlinesIntent(context: Context, fetchMode: FetchMode) =
        internalIntent(
            context,
            "io.github.hoanv810.demo.topheadlines.open"
        ).apply {
            putExtra(Extras.EXTRA_FILTER, fetchMode)
        }

    fun openEverythingIntent(context: Context) = internalIntent(
        context,
        "io.github.hoanv810.demo.everything.open"
    )

    fun openNewsDetailIntent(context: Context, article: Article) =
        internalIntent(context, "io.github.hoanv810.demo.newsdetail.open").apply {
            putExtra(Extras.EXTRA_ARTICLE, article)
        }

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
}