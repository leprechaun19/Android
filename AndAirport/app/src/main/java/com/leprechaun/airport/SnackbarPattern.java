package com.leprechaun.airport;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;

public class SnackbarPattern {

    static void configSnackbar(Context context, Snackbar snackbar, int bottomMargin){
        setParameters(context, snackbar, bottomMargin);
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.colorSnackbar));
    }

    private static void setParameters(Context context, final Snackbar snackbar, int bottomMargin){
        Snackbar.SnackbarLayout l = (Snackbar.SnackbarLayout) snackbar.getView();
        l.setBackground(context.getResources().getDrawable(R.drawable.snackbar));
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) l.getLayoutParams();
        params.setMargins(60, 60,60,bottomMargin);
        l.setLayoutParams(params);
        final View.OnClickListener snackbarClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                snackbar.dismiss();
            }
        };
        snackbar.setAction("OK", snackbarClickListener).show();
        snackbar.setActionTextColor(context.getResources().getColor(R.color.colorMilk));
        l.setPadding(5,0,15,0);
    }
}
