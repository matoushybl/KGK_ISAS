package com.mat.hyb.school.kgk.sas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by matous on 5.12.13.
 */
public class ClassDialogBuilder {
    private AlertDialog.Builder builder;
    private PreferencesProvider preferencesProvider;
    private Dialog dialog;
    private PreconfiguredWebView webView;
    private UrlProvider urlProvider;

    public ClassDialogBuilder(Context context) {
        preferencesProvider = new PreferencesProvider(context);
        builder = new AlertDialog.Builder(context);
        urlProvider = new UrlProvider(context);
        builder.setTitle(R.string.dialog_title_class);
        ListView listView = new ListView(context);
        listView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,
                new ClassIdProvider().getClassNames()));
        builder.setView(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (webView == null) {
                    preferencesProvider.setDefaultClass(position);
                    preferencesProvider.setFirstRun();
                } else {
                    webView.loadUrl(urlProvider.getTimetableUrl(new ClassIdProvider().getClassId(position)));
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    public void build() {
        dialog = builder.show();
    }

    public void build(PreconfiguredWebView webView) {
        this.webView = webView;
        dialog = builder.show();
    }
}
