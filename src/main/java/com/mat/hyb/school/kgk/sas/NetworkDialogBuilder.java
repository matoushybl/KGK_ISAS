package com.mat.hyb.school.kgk.sas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by matous on 7.12.13.
 */
public class NetworkDialogBuilder {
    private AlertDialog.Builder builder;

    public NetworkDialogBuilder(Context context) {
        builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_title_net);
        builder.setMessage(R.string.dialog_net_text);
        builder.setNegativeButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
    public void show(){
        builder.show();
    }
}
