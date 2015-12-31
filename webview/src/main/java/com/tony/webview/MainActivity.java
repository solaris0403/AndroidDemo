package com.tony.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.web_view)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mWebView.requestFocus();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.loadUrl("http://www.unileverfoodsolutions.tw/user/loyalty_2016#m3");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("http://www.unileverfoodsolutions.tw/user/loyalty_2016#m3");
        intent.setData(uri);

        startActivity(intent);
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.d("erik", "loadUrl ,url = " + url);
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                super.onReceivedError(view, errorCode, description, failingUrl);
//                if (failingUrl.contains("#")) {
//                    Log.d("erik", "failingurl = " + failingUrl);
//                    String[] temp;
//                    temp = failingUrl.split("#");
//                    view.loadUrl(temp[0]); // load page without internal
//                    try {
//                        Thread.sleep(400);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    view.goBack();
//                    view.goBack();
//                }
//            }
//        });
    }
}
