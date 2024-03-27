package com.example.app_cnpmnc_da_hethongatm.template;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_cnpmnc_da_hethongatm.R;

public class ViewGold extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goldapi);
        String goldPriceUrl = "https://webtygia.com/api/vang?bgheader=c4cc5c&colorheader=745f25&padding=5&fontsize=13&undefined";
        WebViewTemplate webViewTemplate = new WebViewGoldPrice(goldPriceUrl);
        WebView webView = webViewTemplate.createWebView(this);
        setContentView(webView);
    }
}
