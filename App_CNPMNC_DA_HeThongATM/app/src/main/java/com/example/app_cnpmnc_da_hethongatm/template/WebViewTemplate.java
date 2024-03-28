package com.example.app_cnpmnc_da_hethongatm.template;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;

public abstract  class WebViewTemplate {

    protected abstract String generateIframeUrl();

    public WebView createWebView(Context context) {
        WebView webView = new WebView(context);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String iframeUrl = generateIframeUrl();
        webView.loadData("<iframe frameborder=\"0\" width=\"100%\" height=\"750px\" src=\"" + iframeUrl + "\"></iframe>", "text/html", "utf-8");
        return webView;
    }
}
