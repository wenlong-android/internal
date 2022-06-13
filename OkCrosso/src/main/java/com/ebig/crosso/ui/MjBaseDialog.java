package com.ebig.crosso.ui;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.ebig.crosso.R;


public abstract class MjBaseDialog extends Dialog {
    public MjBaseDialog(@NonNull Context context) {
        super(context, R.style.alert_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(false);

    }

}
