package com.mat.hyb.school.kgk.sas;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by matous on 4.12.13.
 */
public class SubstitutionFragment extends Fragment {
    private UrlProvider urlProvider;

    public SubstitutionFragment(Context context) {
        urlProvider = new UrlProvider(context);
    }

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
        View layout = inflater.inflate(R.layout.fragment_substitution, container, false);
        assert layout != null;
        Button today = (Button) layout.findViewById(R.id.button_today);
        Button tomorrow = (Button) layout.findViewById(R.id.button_tomorrow);
        final PreconfiguredWebView webView =
                (PreconfiguredWebView) layout.findViewById(R.id.substitution_webview);
        webView.initialiseWebViewClient();
        webView.loadUrl(urlProvider.getSubstitutionTodayUrl());
        Log.d("url", urlProvider.getSubstitutionTodayUrl());
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(urlProvider.getSubstitutionTodayUrl());
            }
        });
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(urlProvider.getSubstitutionTomorrowUrl());
            }
        });
        return layout;
    }
}
