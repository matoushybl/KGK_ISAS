package com.mat.hyb.school.kgk.sas;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by matous on 4.12.13.
 */
public class MarksFragment extends Fragment {
    private UrlProvider urlProvider;

    public MarksFragment() {
        urlProvider = new UrlProvider();
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
        View layout = inflater.inflate(R.layout.fragment_marks, container, false);
        assert layout != null;
        PreconfiguredWebView webView = (PreconfiguredWebView) layout.findViewById(R.id.marks_webview);
        webView.initialiseWebViewClient();
        webView.loadUrl(urlProvider.getMarksUrl());
        return layout;
    }
}
