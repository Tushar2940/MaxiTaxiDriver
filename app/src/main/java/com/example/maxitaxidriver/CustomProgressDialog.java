package com.example.maxitaxidriver;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

public class CustomProgressDialog extends ProgressDialog {

    Context context;

    public CustomProgressDialog(Context context1) {
        super(context1);
        this.context = context1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_progress_dialoge);
        getWindow().getDecorView().setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
    }

    public void show() {
        super.show();
    }

    public void dismiss() {
        if (context != null
                && isShowing()) {
            super.dismiss();
        }
    }
}
