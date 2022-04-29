package io.github.hoanv810.uicomponents.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebViewClient;

public class WebView extends android.webkit.WebView {
    static final String BLANK = "about:blank";
    static final String FILE = "file:///";
    private final HistoryWebViewClient mClient = new HistoryWebViewClient();
    String pendingUrl, pendingHtml;

    public WebView(Context context) {
        this(context, null);
    }

    public WebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setWebViewClient(mClient);
    }

    @Override
    public void setWebViewClient(WebViewClient client) {
        mClient.wrap(client);
    }

    @Override
    public boolean canGoBack() {
        return TextUtils.isEmpty(pendingUrl) && super.canGoBack();
    }

    public void reloadUrl(String url) {
        if (getProgress() < 100) {
            stopLoading(); // this will fire onPageFinished for current URL
        }
        pendingUrl = url;
        loadUrl(BLANK); // clear current web resources, load pending URL upon onPageFinished
    }

    public void reloadHtml(String html) {
        pendingHtml = html;
        reloadUrl(FILE);
    }

    static class HistoryWebViewClient extends WebViewClient {
        private WebViewClient mClient;

        @Override
        public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            view.pageUp(true);
            WebView webView = (WebView) view;
            if (/*AppUtils.urlEquals(url, webView.mPendingUrl)*/url.equals(webView.pendingUrl)) {
                view.setVisibility(VISIBLE);
            }
            if (mClient != null) {
                mClient.onPageStarted(view, url, favicon);
            }
        }

        @Override
        public void onPageFinished(android.webkit.WebView view, String url) {
            super.onPageFinished(view, url);
            WebView webView = (WebView) view;
            if (TextUtils.equals(url, BLANK)) { // has pending reload, open corresponding URL
                if (!TextUtils.isEmpty(webView.pendingHtml)) {
                    view.loadDataWithBaseURL(webView.pendingUrl, webView.pendingHtml,
                            "text/html", "UTF-8", webView.pendingUrl);
                } else {
                    view.loadUrl(webView.pendingUrl);
                }
            } else if (!TextUtils.isEmpty(webView.pendingUrl) &&
                    TextUtils.equals(url, webView.pendingUrl)) { // reload done, clear history
                webView.pendingUrl = null;
                webView.pendingHtml = null;
                view.clearHistory();
            }
            if (mClient != null) {
                mClient.onPageFinished(view, url);
            }
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @SuppressWarnings("deprecation")
        @Override
        public WebResourceResponse shouldInterceptRequest(android.webkit.WebView view, String url) {
            return mClient != null ? mClient.shouldInterceptRequest(view, url) :
                    super.shouldInterceptRequest(view, url);
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public WebResourceResponse shouldInterceptRequest(android.webkit.WebView view, WebResourceRequest request) {
            return mClient != null ? mClient.shouldInterceptRequest(view, request) :
                    super.shouldInterceptRequest(view, request);
        }

        void wrap(WebViewClient client) {
            mClient = client;
        }
    }
}
