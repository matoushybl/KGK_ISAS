package com.mat.hyb.school.kgk.sas;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by matous on 5.12.13.
 */
public class PreconfiguredWebView extends WebView {
    private final PreconfiguredWebView view = this;

    public PreconfiguredWebView(Context context) {
        super(context);
        preconfigure();
    }

    public PreconfiguredWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        preconfigure();
    }

    public PreconfiguredWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        preconfigure();
    }

    private void preconfigure() {
        this.getSettings().setBuiltInZoomControls(true);
        this.getSettings().setDisplayZoomControls(false);
    }

    public void initialiseWebViewClient() {
        this.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

    }

    public void initialiseWebViewClient(final Button back, final Button forward) {
        this.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (view.canGoBack()) {
                    back.setEnabled(true);
                } else {
                    back.setEnabled(false);
                }
                if (view.canGoForward()) {
                    forward.setEnabled(true);
                } else {
                    forward.setEnabled(false);
                }
                super.onPageFinished(view, url);
            }
        });
    }
}
