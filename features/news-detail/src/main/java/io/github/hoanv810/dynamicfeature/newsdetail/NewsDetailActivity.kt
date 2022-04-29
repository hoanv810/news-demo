package io.github.hoanv810.dynamicfeature.newsdetail

import android.graphics.Color
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.ActionBar
import dagger.hilt.android.AndroidEntryPoint
import io.github.hoanv810.core.constant.Extras
import io.github.hoanv810.core.network.response.Article
import io.github.hoanv810.dynamicfeature.newsdetail.databinding.ActivityNewsDetailBinding
import io.github.hoanv810.uicomponents.base.BaseActivity
import io.github.hoanv810.uicomponents.widget.CacheableWebView

/**
 * @author hoanv
 * @since 2/24/21
 */
@AndroidEntryPoint
class NewsDetailActivity : BaseActivity<ActivityNewsDetailBinding>() {

    private lateinit var article: Article

    override fun layoutResourceId(): Int = R.layout.activity_news_detail

    override fun initContentView() {
        intent?.getParcelableExtra<Article>(Extras.EXTRA_ARTICLE)?.apply {
            article = this
        }

        binding.article = article
        setSupportActionBar(binding.toolbar)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_HOME or ActionBar.DISPLAY_HOME_AS_UP
        binding.toolbar.setNavigationOnClickListener { finish() }

        setUpWebView()
    }

    override fun initViewModel() {
    }

    private fun setUpWebView() {
        binding.articleWebview.setBackgroundColor(Color.TRANSPARENT)
        binding.articleWebview.setWebChromeClient(object : CacheableWebView.ArchiveClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                setProgressBar(newProgress)
            }
        })

        binding.articleWebview.loadUrl(article.url)
    }

    private fun setProgressBar(progress: Int) {
        binding.articleProgress.progress = progress
        binding.articleProgress.visibility = if (progress == 100) View.GONE else View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_web, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_forward -> binding.articleWebview.goForward()
            R.id.menu_refresh -> binding.articleWebview.reload()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.articleWebview.canGoBack()) {
            binding.articleWebview.goBack()
            return
        }

        super.onBackPressed()
    }
}