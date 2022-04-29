package io.github.hoanv810.uicomponents.widget

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.annotation.CallSuper
import io.github.hoanv810.core.utils.CoreUtils.hasConnection
import java.io.File

class CacheableWebView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    private var archiveClient = ArchiveClient()

    override fun reloadUrl(url: String) {
        super.reloadUrl(getCacheableUrl(url))
    }

    override fun loadUrl(url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        archiveClient.lastProgress = 0
        super.loadUrl(getCacheableUrl(url))
    }

    override fun loadUrl(url: String, additionalHttpHeaders: Map<String, String>) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        archiveClient.lastProgress = 0
        super.loadUrl(getCacheableUrl(url), additionalHttpHeaders)
    }

    override fun setWebChromeClient(client: WebChromeClient) {
        require(client is ArchiveClient) {
            "client should be an instance of " + ArchiveClient::class.java.name
        }
        archiveClient = client
        super.setWebChromeClient(archiveClient)
    }

    private fun init() {
        enableCache()
        setLoadSettings()
        webViewClient = WebViewClient()
        setWebChromeClient(archiveClient)
    }

    private fun enableCache() {
        val webSettings = settings
        webSettings.setAppCacheEnabled(true)
        webSettings.allowFileAccess = true
        webSettings.setAppCachePath(
            context.applicationContext
                .cacheDir.absolutePath
        )
        setCacheModeInternal()
    }

    private fun setCacheModeInternal() {
        settings.cacheMode =
            if (hasConnection(context)) WebSettings.LOAD_CACHE_ELSE_NETWORK else WebSettings.LOAD_CACHE_ONLY
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setLoadSettings() {
        val webSettings = settings
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.javaScriptEnabled = true
    }

    private fun getCacheableUrl(url: String): String {
        archiveClient.cacheFileName = generateCacheFilename(url)
        setCacheModeInternal()
        if (settings.cacheMode != WebSettings.LOAD_CACHE_ONLY) {
            return url
        }
        val cacheFile = File(archiveClient.cacheFileName)
        return if (cacheFile.exists()) Uri.fromFile(cacheFile).toString() else url
    }

    private fun generateCacheFilename(url: String): String {
        return context.applicationContext.cacheDir.absolutePath +
                File.separator +
                CACHE_PREFIX +
                url.hashCode() +
                CACHE_EXTENSION
    }

    open class ArchiveClient : WebChromeClient() {
        var lastProgress = 0
        var cacheFileName: String? = null
        @CallSuper
        override fun onProgressChanged(view: android.webkit.WebView, newProgress: Int) {
            if (view.settings.cacheMode == WebSettings.LOAD_CACHE_ONLY) {
                return
            }
            if (cacheFileName != null && lastProgress != 100 && newProgress == 100) {
                lastProgress = newProgress
                view.saveWebArchive(cacheFileName)
            }
        }
    }

    companion object {
        private const val CACHE_PREFIX = "webarchive-"
        private const val CACHE_EXTENSION = ".mht"
    }

    init {
        init()
    }
}