package com.mat.hyb.school.kgk.sas;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by matous on 5.12.13.
 */
public class BasicWebFragment extends Fragment {
    public static final String MODE_CANTEEN = "canteen";
    public static final String MODE_MOODLE = "moodle";
    public static final String MODE = "mode";
    public static final String MODE_WEBSITE = "website";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (getActivity() != null && getActivity().getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            getActivity().getActionBar().setDisplayShowHomeEnabled(true);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_baseweb, container, false);
        assert layout != null;
        final Button back = (Button) layout.findViewById(R.id.button_back);
        final Button forward = (Button) layout.findViewById(R.id.button_forward);
        final PreconfiguredWebView webView =
                (PreconfiguredWebView) layout.findViewById(R.id.moodle_webview);
        webView.initialiseWebViewClient(back, forward);
        if (getArguments() != null && getActivity() != null && getArguments().getString(MODE).equals(MODE_MOODLE)) {
            webView.loadUrl(new UrlProvider(getActivity().getApplicationContext())
                    .getMoodleUrl());
        } else if (getArguments() != null && getActivity() != null && getArguments().getString(MODE).equals(MODE_CANTEEN)) {
            webView.loadUrl(new UrlProvider(getActivity().getApplicationContext())
                    .getCanteenUrl());
        } else if (getArguments() != null && getActivity() !=null && getArguments().getString(MODE).equals(MODE_WEBSITE)) {
            webView.loadUrl(UrlProvider.WEBSITE);
        }
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (webView.canGoBack()) {
                        webView.goBack();
                    }
                }
            });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward()) {
                    webView.goForward();
                }
            }
        });
        return layout;
    }
}
