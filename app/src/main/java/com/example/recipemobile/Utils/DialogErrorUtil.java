package com.example.recipemobile.Utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.recipemobile.R;

public class DialogErrorUtil {

    public static void dialogStandardError(Context context, int idErrorString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.warning);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(idErrorString);

        builder.setNeutralButton(R.string.back,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
