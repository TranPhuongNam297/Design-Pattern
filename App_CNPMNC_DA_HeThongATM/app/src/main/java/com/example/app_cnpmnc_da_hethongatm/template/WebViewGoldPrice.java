package com.example.app_cnpmnc_da_hethongatm.template;

public class WebViewGoldPrice extends WebViewTemplate {

    private String url;

    public WebViewGoldPrice(String url) {
        this.url = url;
    }

    @Override
    protected String generateIframeUrl() {
        return url;
    }
}