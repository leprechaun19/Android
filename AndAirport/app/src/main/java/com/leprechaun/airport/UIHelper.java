package com.leprechaun.airport;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public class UIHelper {

    public static void showShortToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showSnackbar(View view, String message, int bottomMargin) {

        final Snackbar bar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        SnackbarPattern.configSnackbar(view.getContext(), bar, bottomMargin);
    }
}
